package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.Endereco;
import br.com.adaptaconsultoria.amr.model.Franqueado;
import br.com.adaptaconsultoria.amr.model.PreCompra;
import br.com.adaptaconsultoria.amr.model.PreCompraItem;
import br.com.adaptaconsultoria.amr.model.RedeConfig;
import br.com.adaptaconsultoria.amr.model.RedeParcelas;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.EnderecoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.FranqueadoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.rede.CodVer;
import br.com.adaptaconsultoria.amr.service.PreCompraService;
import br.com.adaptaconsultoria.amr.springmvc.form.Address;
import br.com.adaptaconsultoria.amr.springmvc.form.AutorizacaoCartaoCieloForm;
import br.com.adaptaconsultoria.amr.springmvc.form.AutorizacaoCartaoForm;
import br.com.adaptaconsultoria.amr.springmvc.form.Cart;
import br.com.adaptaconsultoria.amr.springmvc.form.Customer;
import br.com.adaptaconsultoria.amr.springmvc.form.Item;
import br.com.adaptaconsultoria.amr.springmvc.form.Services;
import br.com.adaptaconsultoria.amr.springmvc.form.Shipping;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("request")
public class CheckoutCartaoController implements Serializable {

  private PreCompraService preCompraService = new PreCompraService();
  private PreCompraDao preCompraDao = DaoFactory.getInstance().getPreCompraDao();
  private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
  private FranqueadoDao franqueadoDao = DaoFactory.getInstance().getFranqueadoDao();
  private EnderecoDao enderecoDao = DaoFactory.getInstance().getEnderecoDao();

  @RequestMapping(value = "/checkoutcartao", method = GET)
  public ModelAndView checkOutCartao(String id, HttpSession session) throws Exception {
    PreCompra preCompra = preCompraDao.carregaPorId(id);

    AutorizacaoCartaoForm form = new AutorizacaoCartaoForm();
    form.setId(preCompra.getId());

    preCompra.setAcrescimo(BigDecimal.ZERO);
    preCompra.calculaTotal();
    preCompra = preCompraDao.salva(preCompra);

    form.setTotal(preCompra.getTotal().toString());

    ModelAndView model = new ModelAndView("checkoutcartao", "form", form);
    return model;
  }

  @RequestMapping(value = "/lista_redeparcelas", method = RequestMethod.GET)
  public @ResponseBody List<RedeParcelas> listaParcelas(@RequestParam("bandeira") String bandeira,
      @RequestParam("total") String total, @RequestParam("amrPreCompraId") String amrPreCompraId,
      RedirectAttributes redirectAttributes) {

    List<RedeParcelas> parcelas = new ArrayList<RedeParcelas>();
    try {

      PreCompra pedido = DaoFactory.getInstance().getPreCompraDao().carregaPorId(amrPreCompraId);
      pedido.setBandeira(bandeira);
      DaoFactory.getInstance().getPreCompraDao().atualiza(pedido);

      DecimalFormat decimalFormat = new DecimalFormat("#.##");
      parcelas = DaoFactory.getInstance().getRedeParcelasDao()
          .listaPorBandeira(bandeira, amrPreCompraId);
      for (int i = 0; i < parcelas.size(); i++) {
        BigDecimal valorTotal = new BigDecimal(total);
        BigDecimal valor = parcelas.get(i).getCoeficienteParcelas().multiply(valorTotal);
        valorTotal = valorTotal.add(parcelas.get(i).getCoeficienteAcrescimo().multiply(valorTotal));
        String label = "<b>" + parcelas.get(i).getNumero() + " x R$ " + decimalFormat.format(valor)
            + "</b> (R$ " + decimalFormat.format(valorTotal) + ")";
        parcelas.get(i).setNome(label);
      }
    } catch (Exception e) {
      MessageUtil.erro(redirectAttributes, "ERRO", e);
    }
    return parcelas;
  }

  @RequestMapping(value = "checkoutrede", method = POST)
  public ModelAndView checkOutRede(@Valid
  final AutorizacaoCartaoForm form, BindingResult result, HttpServletResponse response,
      RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

    try {
      if (result.hasErrors()) {
        throw new Exception("ERRO: dados inválidos");
      }

      else {

        PreCompra preCompra = preCompraDao.carregaPorId(form.getId());
        if (preCompra == null)
          throw new Exception("Pré compra não encontrada no banco de dados");

        form.setNumPedido(preCompra.getNumero());

        RedeConfig config = DaoFactory.getInstance().getRedeConfigDao()
            .carregaPorId(preCompra.getAdOrgId());
        if (config == null)
          throw new Exception(
              "Nenhuma configuração para autorização de cartões com a rede card encontrada para a organização da compra prévia número: "
                  + preCompra.getNumero());

        form.setFiliacao(config.getFiliacao());
        // RedeParcelas parcelas = DaoFactory.getInstance().getRedeParcelasDao()
        // .carregaPorId(form.getParcelas());
        List<RedeParcelas> listaPorBandeira = DaoFactory.getInstance().getRedeParcelasDao()
            .listaPorBandeira(form.getBandeira(), preCompra.getId());

        RedeParcelas parcelas = listaPorBandeira.get(0);
        if (parcelas == null)
          throw new Exception(
              "Nenhum registro de parcela com esse ID: "
                  + form.getParcelas()
                  + " foi encontrado no sistema. Contate o administrador do sistema e informe o ocorrido.");

        if (parcelas.getNumero().equalsIgnoreCase("0")
            || parcelas.getNumero().equalsIgnoreCase("1")) {
          form.setTransacao(config.getTipoTransacaoAVista());
          form.setParcelas("00");
          preCompra.setAcPaymentMethodId(parcelas.getAcPaymentMethodId());
          preCompra.setcPaymentTermId(parcelas.getcPaymentTermId());
        }

        else {
          form.setTransacao(config.getTipoTransacaoParcelada());
          BigDecimal acrescimo = preCompra.getTotal().multiply(parcelas.getCoeficienteAcrescimo());
          preCompra.setAcrescimo(acrescimo);
          preCompra.setAcPaymentMethodId(parcelas.getAcPaymentMethodId());
          preCompra.setcPaymentTermId(parcelas.getcPaymentTermId());
          preCompra.calculaTotal();
          form.setParcelas(parcelas.getNumero());
        }
        preCompra.setBandeira(parcelas.getBandeira());
        preCompra.setTransacao(form.getTransacao());
        preCompra.setParcelas(form.getParcelas());
        preCompra.setAmrFormaPagamentoId("CARTAO");
        preCompra = preCompraDao.atualiza(preCompra);

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        form.setTotal(decimalFormat.format(preCompra.getTotal()).replaceAll(",", "."));
        form.setDistribuidor(config.getDistribuidor());
        form.setCodVer(config.getCodVer());
        form.setUrlBack(config.getUrlBack());
        form.setUrlCima(config.getUrlCima());
        form.setTarget(config.getTarget());

        /*****************************************************************************************************************************
         ******** TESTE
         * ************************************************************************************
         * **********************
         *****************************************************************************************************************************/
        // form.setTotal("1108.00");
        // form.setParcelas("10");
        // form.setTransacao(config.getTipoTransacaoParcelada());
        /****************************************************************************************************************************
				****************************************************************************************************************************/

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
          ipAddress = request.getRemoteAddr();
        }

        CodVer codVer = new CodVer();
        String codigo = codVer.codigoVerificacao(form.getFiliacao(), form.getTotal(), ipAddress);
        form.setCodVer(codigo);

        ModelAndView model = new ModelAndView("checkoutcartaorede", "form", form);
        return model;
      }
    } catch (Exception e) {
      ModelAndView model = new ModelAndView("messages");
      model.addObject("messageType", "0");
      model.addObject("messageTitle", "ERRO");
      model.addObject("messageDetail", ErrorUtil.getLastMessage(e));
      return model;
    }
  }

  @RequestMapping(value = "checkoutcielo", method = POST)
  public ModelAndView checkOutCielo(@Valid
  final AutorizacaoCartaoCieloForm form, BindingResult result, HttpServletResponse response,
      RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

    try {
      if (result.hasErrors()) {
        throw new Exception("ERRO: dados inválidos");
      }

      PreCompra preCompra = preCompraDao.carregaPorId(form.getId());
      if (preCompra == null)
        throw new Exception("Pré compra não encontrada no banco de dados");

      // Endereco endereco = enderecoDao.carregaPorId(preCompra.getcLocationId());
      Endereco endereco = enderecoDao.carregaPorId("A8C7728FCD174D898B53FB11F89FE334");

      form.setMerchantId("43d8053b-ab6a-4d1b-b87e-9cee1144d26e");
      form.setOrderNumber(preCompra.getNumero());

      // TODO Danilo Texto para ser exibido na fatura do portador, após o nome do estabelecimento
      // comercial. Ver com o Kelvi como ficará
      form.setSoftDescriptor("asdasd");

      // Cart Discount

      // Tipo do desconto a ser aplicado: "Amount”, “Percent”. Obrigatório caso Value for maior ou
      // igual a zero.
      // form.getCart().getDiscount().setType("");
      // Valor do desconto a ser aplicado (pode ser valor absoluto ou percentual). Obrigatório caso
      // Type for “Amount” ou “Percent”.
      // form.getCart().getDiscount().setValue("");

      // Cart Itens

      form.setCart(new Cart());

      for (PreCompraItem compraItem : preCompra.getItens()) {
        Item item = new Item();

        item.setName(compraItem.getNome());
        item.setDescription(compraItem.getDescricao());
        String preco = compraItem.getPrecoUnitario().toString().replace(".", "");
        item.setUnitPrice(preco);
        item.setQuantity(compraItem.getQuantidade().toString());

        // Tipo do item no carrinho.
        item.setType("Asset");

        // Sku do item no carrinho.
        item.setSku("Sku do item no carrinho");
        String peso = compraItem.getPeso().toString().replace(".", "");
        item.setWeight(peso);

        form.getCart().getItems().add(item);

      }

      // Shipping TODO Danilo ver como pegas essas informaçoes
      form.setShipping(new Shipping());
      form.getShipping().setType("2");
      form.getShipping().setSourceZipCode("11000000");
      form.getShipping().setTargetZipCode(endereco.getCep());

      form.getShipping().setAddress(new Address());
      form.getShipping().getAddress().setStreet(endereco.getLogradouro());
      form.getShipping().getAddress().setNumber(endereco.getNumero());
      form.getShipping().getAddress().setComplement(endereco.getComplemento());
      form.getShipping().getAddress().setDistrict(endereco.getBairro());
      form.getShipping().getAddress().setCity(endereco.getCidade());
      form.getShipping().getAddress().setState(endereco.getEstado());

      Services services = new Services();
      services.setName("Serviço de frete");
      String valorEntrega = preCompra.getValorEntrega().toString().replace(".", "");
      services.setPrice(valorEntrega);
      services.setDeadline("15");
      form.getShipping().getServices().add(services);

      /*
       * form.getShipping().getMeasures().setPackag("");
       * form.getShipping().getMeasures().setLenght("");
       * form.getShipping().getMeasures().setHeight("");
       * form.getShipping().getMeasures().setWidth("");
       * form.getShipping().getMeasures().setDiameter("");
       */

      // Payment

      // Desconto, em porcentagem, para pagamentos a serem realizados com boleto.
      // form.getPayment().setBoletoDiscount("0");

      // Desconto, em porcentagem, para pagamentos a serem realizados com débito online.
      // form.getPayment().setDebitDiscount("10");

      // Tipo de intervalo de recorrência; Veja a tabela Intervalo de Recorrência *tabela na
      // documentação
      // form.getPayment().getRecurrentPayment().setInterval("");

      // Data final da recorrência no formado YYYY-MM-DD
      // form.getPayment().getRecurrentPayment().setEndDate(new Date());

      // Customer TODO Danilo ver como pegas essas informaçoes
      form.setCustomer(new Customer());
      String documento = preCompra.getCliente().getCnpj().replace(".", "").replace("-", "");
      form.getCustomer().setIdentity(documento);
      form.getCustomer().setFullName(preCompra.getCliente().getNome());
      form.getCustomer().setEmail(preCompra.getCliente().getEmail());
      form.getCustomer().setPhone(
          preCompra.getCliente().getCelularDDD() + preCompra.getCliente().getCelularNumero());

      // Options TODO terá que fazer a logica.
      // form.getOptions().setAntifraudEnabled(true);

      ModelAndView model = new ModelAndView("checkoutcartaocielo", "form", form);
      return model;

    } catch (Exception e) {
      ModelAndView model = new ModelAndView("messages");
      model.addObject("messageType", "0");
      model.addObject("messageTitle", "ERRO");
      model.addObject("messageDetail", ErrorUtil.getLastMessage(e));
      return model;
    }
  }

  @RequestMapping(value = "/ktaut", method = POST)
  public String autorizaCompraProduto(RedirectAttributes redirectAttributes, HttpSession session,
      HttpServletRequest request) {

    String numero = null;
    try {

      Enumeration en = request.getParameterNames();

      while (en.hasMoreElements()) {
        String paramName = (String) en.nextElement();
        System.out.println(paramName);
      }

      String codret = request.getParameter("CODRET");
      String msgret = request.getParameter("MSGRET");
      numero = request.getParameter("NUMPEDIDO");
      String pax1 = request.getParameter("PAX1");

      System.out.println(codret);
      System.out.println(msgret);
      System.out.println(numero);
      System.out.println(pax1);

      return "redirect:erro?titulo=ERRO " + codret + "&mensagem=" + msgret;

    } catch (Exception e) {
      return "redirect:erro?titulo=ERRO&mensagem=" + e.getMessage();
    }
  }

  @RequestMapping(value = "/ktaut", method = GET)
  public String autorizaCompraProdutoRede(RedirectAttributes redirectAttributes,
      HttpSession session, HttpServletRequest request) {

    String redirectSucesso = "redirect:compraconcluida?id=";
    String redirectErro = "redirect:checkoutcompra?precompraid=";
    String id = null;
    try {

      Enumeration en = request.getParameterNames();

      while (en.hasMoreElements()) {
        String paramName = (String) en.nextElement();
        System.out.println(paramName);
      }

      String codret = request.getParameter("CODRET");
      String msgret = request.getParameter("MSGRET");
      String numero = request.getParameter("NUMPEDIDO");
      String pax1 = request.getParameter("PAX1");
      String numautor = request.getParameter("NUMAUTOR");
      String numsqn = request.getParameter("NUMSQN");
      String numcv = request.getParameter("NUMCV");
      String numautent = request.getParameter("NUMAUTENT");
      String numpedido = request.getParameter("NUMPEDIDO");
      String data = request.getParameter("DATA");
      String nr_cartao = request.getParameter("NR_CARTAO");
      String origem_bin = request.getParameter("ORIGEM_BIN");
      String numprg = request.getParameter("NUMPRG");
      String nr_hash_cartao = request.getParameter("NR_HASH_CARTAO");
      String cod_banco = request.getParameter("COD_BANCO");

      System.out.println("codret: " + codret);
      System.out.println("msgret: " + msgret);
      System.out.println("numero: " + numero);
      System.out.println("pax1: " + pax1);
      System.out.println("numautor: " + numautor);
      System.out.println("numsqn: " + numsqn);
      System.out.println("numcv: " + numcv);
      System.out.println("numautent: " + numautent);
      System.out.println("numpedido: " + numpedido);
      System.out.println("data: " + data);
      System.out.println("nr_cartao: " + nr_cartao);
      System.out.println("origem_bin: " + origem_bin);
      System.out.println("numprg: " + numprg);
      System.out.println("nr_hash_cartao: " + nr_hash_cartao);
      System.out.println("cod_banco: " + cod_banco);

      if (codret == null || codret.isEmpty()) {
        if (numautor != null && !numautor.isEmpty() && numcv != null && !numcv.isEmpty()) {
          codret = "0";
          msgret = "Transação aprovada! Número autorização: " + numautor;
        }
      }

      System.out.println("carregando pedido com o número: " + numero);
      PreCompra pedido = preCompraDao.carregaPorNumero(numero);
      System.out.println("PEDIDO: " + pedido);
      if (pedido == null)
        throw new Exception("Pedido com o número: " + numero + " não foi encontrado no sistema.");

      id = pedido.getId();

      if (pedido.getPedido() != null) {
        redirectSucesso = "redirect:gopagamentospendentes";
        redirectErro = "redirect:gopagamentospendentes";
      } else {
        redirectSucesso = "redirect:compraconcluida?id=" + pedido.getId();
        redirectErro = "redirect:checkoutcompra?precompraid=" + id;
      }

      pedido.setCodret(codret);
      pedido.setMsgret(msgret);
      pedido.setNumautor(numautor);
      pedido.setNumsqn(numsqn);
      pedido.setNumcv(numcv);
      pedido.setNumautent(numautent);
      pedido.setNumpedido(numpedido);
      pedido.setData(data);
      pedido.setPax1(pax1);
      pedido.setNrCartao(nr_cartao);
      pedido.setOrigemBin(origem_bin);
      pedido.setNumprg(numprg);
      pedido.setNrHashCartao(nr_hash_cartao);
      pedido.setCodBanco(cod_banco);
      pedido.setRetornoCodigo(codret);
      pedido.setRetornoMensagem(msgret);
      System.out.println("Atualizando dados do pedido");
      pedido = preCompraDao.atualiza(pedido);

      System.out.println("Carregando dados da sessão: " + session);
      String usuarioSessao = SessionUtil.getUsuarioId(session);
      System.out.println("USUARIO NA SESSÃO - AUT: " + usuarioSessao);
      if (usuarioSessao == null || usuarioSessao.isEmpty()) {
        System.out.println("Não encontrado, revalidando sessão");
        try {
          Usuario usuario = usuarioDao.carregaPorId(pedido.getAdUserId());
          SessionUtil.setUsuarioId(session, usuario);

          String skin = "skin-blue";
          try {
            skin = usuario.getSkin();
          } catch (Exception e) {
            skin = "skin-blue";
          }
          SessionUtil.setAttribute(session, "skinUser", skin);

          Franqueado franqueado = franqueadoDao.carregaPorUsuario(usuario.getId());
          if (franqueado != null) {
            SessionUtil.setAttribute(session, "avatar", franqueado.getAvatar());
            SessionUtil.setAttribute(session, "autenticacompra", franqueado.getAutenticaCompra());
            SessionUtil.setAttribute(session, "codigobp", franqueado.getCodigo());
          }

        } catch (Exception e) {
          System.out.println("ERRO ao revalidar sessão: " + e.getMessage());
        }
      }

      try {
        System.out.println("Fazendo compra no Openbravo");
        preCompraService.compra(pedido.getId(), codret, msgret);
        System.out.println("COMPRA EFETUADA COM SUCESSO!!!");
      } catch (Exception e) {
        System.out.println("ERRO AO COMPRAR");
        pedido = preCompraDao.carregaPorId(id);
        if (numautor != null && !numautor.isEmpty() && numcv != null && !numcv.isEmpty()
            && pedido.getPedido() != null && pedido.getAutorizada().equalsIgnoreCase("Y")) {
          pedido.setProcessaBackground("Y");
          pedido = preCompraDao.atualiza(pedido);
        } else {
          throw e;
        }
      }

      System.out.println("Chamando tela de conclusão de compra");
      MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Compra efetuada com sucesso");
      return redirectSucesso;
    } catch (Exception e) {
      MessageUtil.erro(redirectAttributes, "ERRO", e);
      return redirectErro;
    }
  }

}