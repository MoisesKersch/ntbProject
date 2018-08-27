package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.Serializable;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXB;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.adaptaconsultoria.amr.model.DebtPayment;
import br.com.adaptaconsultoria.amr.model.EnderecoView;
import br.com.adaptaconsultoria.amr.model.MetodoEntrega;
import br.com.adaptaconsultoria.amr.model.MetodoPagamentoPS;
import br.com.adaptaconsultoria.amr.model.NotificacaoPS;
import br.com.adaptaconsultoria.amr.model.ParceiroNegocios;
import br.com.adaptaconsultoria.amr.model.PreCompra;
import br.com.adaptaconsultoria.amr.model.PreCompraItem;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.pagseguro.InfoCartao;
import br.com.adaptaconsultoria.amr.pagseguro.objectxml.Transacao;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.DebtPaymentDao;
import br.com.adaptaconsultoria.amr.persistence.dao.EnderecoViewDao;
import br.com.adaptaconsultoria.amr.persistence.dao.MetodoEntregaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.MetodoPagamentoPSDao;
import br.com.adaptaconsultoria.amr.persistence.dao.NotificacaoPSDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.service.CheckoutPagSeguroService;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;
import br.com.adaptaconsultoria.amr.util.cors.CorsNotificacaoPagSeguro;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Controller
@Scope("request")
@SuppressWarnings("serial")
public class CheckoutPagSeguroController extends CorsNotificacaoPagSeguro implements Serializable {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private MetodoEntregaDao metodoEntregaDao = DaoFactory.getInstance().getMetodoEntregaDao();
	private PreCompraDao preCompraDao = DaoFactory.getInstance().getPreCompraDao();
	private EnderecoViewDao enderecoViewDao = DaoFactory.getInstance().getEnderecoViewDao();
	private MetodoPagamentoPSDao metodoPagamentoPSDao = DaoFactory.getInstance().getMetodoPagamentoPSDao();
	private CheckoutPagSeguroService checkoutPagSeguroService = new CheckoutPagSeguroService();
	private DebtPaymentDao debtPaymentDao = DaoFactory.getInstance().getDebtPaymentDao();
	private NotificacaoPSDao notificacaoPSDao = DaoFactory.getInstance().getNotificacaoPSDao();
	private ParceiroNegociosDao parceiroNegociosDao = DaoFactory.getInstance().getParceiroNegociosDao();

	@RequestMapping(value = "/checkoutpagseguro", method = GET)
	public ModelAndView checkOutCartao(HttpSession session, String id) throws Exception {
		if (id == null || id.isEmpty()) {
			throw new Exception("Parâmetro id não especificado!");
		}
		DebtPayment debtPayment = debtPaymentDao.carregaPorId(id);
		if (debtPayment == null) {
			throw new Exception("DebtPayment não encontrado!");
		}
		if (notificacaoPSDao.existeRegistroPosDebtPayment(debtPayment.getId())) {
			throw new Exception("Compra já foi paga!");
		}

		Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));

		String idSessao      = checkoutPagSeguroService.iniciarSessaoPagamento(usuario.getAdClientId());
		String libjsPS       = checkoutPagSeguroService.getLibJS(usuario.getAdClientId());
		String urlImagePS    = checkoutPagSeguroService.getURLImage(usuario.getAdClientId());
		String parcJurosPS   = checkoutPagSeguroService.getParcelaJuros(usuario.getAdClientId());
		boolean soBoleto     = debtPayment.getcOrderId() == null || debtPayment.getcOrderId().isEmpty();

		ModelAndView model = new ModelAndView("checkoutpagseguro");
		model.addObject("compraID"   , debtPayment.getId()    );
		model.addObject("sessaoPS"   , idSessao               );
		model.addObject("libjsPS"    , libjsPS                );
		model.addObject("urlImagePS" , urlImagePS             );
		model.addObject("valorCompra", debtPayment.getAmount());
		model.addObject("parcJurosPS", parcJurosPS            );
		model.addObject("soBoleto"   , soBoleto               );

		return model;
	}

	@RequestMapping(value = "/efetuarpagamento", method = RequestMethod.POST)
	public @ResponseBody String efetuarPagamento(String metodoCode, String metodoName, String metodoOpt, String compraID, String hashSender, InfoCartao infoCartao, HttpSession session) throws Exception {
		DebtPayment debtPayment = debtPaymentDao.carregaPorId(compraID);
		if (notificacaoPSDao.existeRegistroPosDebtPayment(debtPayment.getId())) {
			throw new Exception("Compra já foi paga!");
		}

		Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));

		List<NameValuePair> parametros = new ArrayList<NameValuePair>();
		parametros.add(new BasicNameValuePair("paymentMethod", metodoName));
		parametros.add(new BasicNameValuePair("senderHash", hashSender));
		parametros.add(new BasicNameValuePair("reference" , compraID));

		/* ************************************************************************* */
		/*                                                                           */
		/*                  ATENÇÃO: Padrão para URL ser válida                      */
		/*                                                                           */
		/* - Após a barra, somente os caracteres alfanuméricos e ".?&=-" são aceitos */
		/* - Porta e parâmetros são opcionais, porem só são aceitas portas 80 e 443  */
		/*                                                                           */
		/* ************************************************************************* */
		try {
			String urlNotificacaoPS = checkoutPagSeguroService.getURLNotificacao(usuario.getAdClientId());
			parametros.add(new BasicNameValuePair("notificationURL" , urlNotificacaoPS));
		} catch (Exception e) {
			// Nada a fazer
		}

		if (metodoCode.equals("1")) {
			 // CreditCard
			setParametrosCredito(parametros, infoCartao, debtPayment);
			System.out.println("Cartão de Crédito");
		} else if (metodoCode.equals("2")) {
			 // Boleto
			System.out.println("Boleto");
		} else if (metodoCode.equals("3")) {
			 // OnlineDebit
			parametros.add(new BasicNameValuePair("bankName", metodoOpt));
			System.out.println("Débito");
		}
		parametros = setParametrosDefault(parametros, debtPayment);

		String retornoTransacao = checkoutPagSeguroService.efetuarPagamento(usuario.getAdClientId(), parametros);

		try {
			Transacao transacao = JAXB.unmarshal(new StringReader(retornoTransacao), Transacao.class);
			gravarNotificacao(usuario, debtPayment.getId(), retornoTransacao, transacao.getStatus());
			try {
				PreCompra compra = preCompraDao.porNumeroDePedido(debtPayment.getcOrderId());
				compra.setNumcv(transacao.getCode());
				compra = preCompraDao.atualiza(compra);

				if (infoCartao.getInstallmentQuantity() == null) {
					debtPayment.setnVezes(1);
				} else {
					debtPayment.setnVezes(infoCartao.getInstallmentQuantity());
				}
				debtPayment.setnAutorizacao(transacao.getCode());
				debtPayment = debtPaymentDao.atualiza(debtPayment);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (new Gson()).toJson(transacao);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<NameValuePair> setParametrosCredito(List<NameValuePair> nameValuePairs, InfoCartao infoCartao, DebtPayment debtPayment) throws Exception {
		ParceiroNegocios cliente;
		try {
			PreCompra compra = preCompraDao.porNumeroDePedido(debtPayment.getcOrderId());
			cliente = compra.getCliente();
		} catch (Exception e) {
			cliente = parceiroNegociosDao.carregaPorId(debtPayment.getcBPartnerId());
		}
		if (nameValuePairs == null)
			nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs.add(new BasicNameValuePair("creditCardToken"              , infoCartao.getCreditCardToken()));
		nameValuePairs.add(new BasicNameValuePair("installmentQuantity"          , infoCartao.getInstallmentQuantity().toString()));
		nameValuePairs.add(new BasicNameValuePair("installmentValue"             , infoCartao.getInstallmentValue().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()));
		nameValuePairs.add(new BasicNameValuePair("noInterestInstallmentQuantity", infoCartao.getNoInterestInstallmentQuantity().toString()));
		nameValuePairs.add(new BasicNameValuePair("creditCardHolderName"         , infoCartao.getCreditCardHolderName()));
		nameValuePairs.add(new BasicNameValuePair("creditCardHolderBirthDate"    , (new SimpleDateFormat("dd/MM/yyyy")).format(cliente.getDataNascimento())));
		nameValuePairs.add(new BasicNameValuePair("creditCardHolderCPF"           , cliente.getCnpj().replace(".", "").replace("-", "").replace("/", "")));

//		-- não utilizar
//		&creditCardHolderAreaCode=99\
//		&creditCardHolderPhone=99999999\

		EnderecoView endereco = enderecoViewDao.carregaPorPartner(cliente.getId());
		nameValuePairs.add(new BasicNameValuePair("billingAddressStreet"    , endereco.getRua()        ));
		nameValuePairs.add(new BasicNameValuePair("billingAddressNumber"    , endereco.getNumero()     ));
		nameValuePairs.add(new BasicNameValuePair("billingAddressComplement", endereco.getComplemento()));
		nameValuePairs.add(new BasicNameValuePair("billingAddressDistrict"  , endereco.getBairro()     ));
		nameValuePairs.add(new BasicNameValuePair("billingAddressPostalCode", endereco.getCep().replace(".", "").replace("-", "")));
		nameValuePairs.add(new BasicNameValuePair("billingAddressCity"      , endereco.getCidade()     ));
		nameValuePairs.add(new BasicNameValuePair("billingAddressState"     , endereco.getUf()         ));
		nameValuePairs.add(new BasicNameValuePair("billingAddressCountry"   , "BRA"                    ));

		return nameValuePairs;
	}

	private List<NameValuePair> setParametrosDefault(List<NameValuePair> nameValuePairs, DebtPayment debtPayment) throws Exception {
		BigDecimal acrescimo;
		ParceiroNegocios cliente;
		MetodoEntrega metodoEntrega = null;
		List<PreCompraItem> itens = new ArrayList<PreCompraItem>();
		try {
			PreCompra compra = preCompraDao.porNumeroDePedido(debtPayment.getcOrderId());
			cliente = compra.getCliente();
//			metodoEntrega = metodoEntregaDao.carregaPorId(compra.getAmrMetodoEntregaId());
			metodoEntrega = metodoEntregaDao.porPreCompraMetodoEntrega(compra.getId(), compra.getAmrMetodoEntregaId());
			itens = compra.getItens();
			acrescimo = compra.getAcrescimo();
		} catch (Exception e) {
			cliente = parceiroNegociosDao.carregaPorId(debtPayment.getcBPartnerId());
			// TODO verificar se está correto o que está feito com as variaveis (metodoEntrega, acrescimo e itens) quando não for PreCompra
			acrescimo = BigDecimal.ZERO;
			PreCompraItem item = new PreCompraItem();
			item.setId("1");
			item.setNome(debtPayment.getDescricao());
			item.setPrecoUnitario(debtPayment.getAmount());
			item.setQuantidade(1);
			itens.add(item);
		}
		if (nameValuePairs == null)
			nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs.add(new BasicNameValuePair("paymentMode"   , "default"                             ));
		nameValuePairs.add(new BasicNameValuePair("currency"      , "BRL"                                 ));
		nameValuePairs.add(new BasicNameValuePair("senderName"    , cliente.getNome()         ));
		nameValuePairs.add(new BasicNameValuePair("senderCPF"     , cliente.getCnpj().replace(".", "").replace("-", "").replace("/", "")));
		nameValuePairs.add(new BasicNameValuePair("senderAreaCode", cliente.getCelularDDD()   ));
		nameValuePairs.add(new BasicNameValuePair("senderPhone"   , cliente.getCelularNumero()));
		nameValuePairs.add(new BasicNameValuePair("senderEmail", "c50633738918280365060@sandbox.pagseguro.com.br"));
		// TODO descomentar a linha abaixo e comentar a linha acima
		// nameValuePairs.add(new BasicNameValuePair("senderEmail"   , cliente.getEmail()        ));

		EnderecoView endereco = enderecoViewDao.carregaPorPartner(cliente.getId());
		nameValuePairs.add(new BasicNameValuePair("shippingAddressStreet"    , endereco.getRua()         ));
		nameValuePairs.add(new BasicNameValuePair("shippingAddressNumber"    , endereco.getNumero()      ));
		nameValuePairs.add(new BasicNameValuePair("shippingAddressComplement", endereco.getComplemento() ));
		nameValuePairs.add(new BasicNameValuePair("shippingAddressDistrict"  , endereco.getBairro()      ));
		nameValuePairs.add(new BasicNameValuePair("shippingAddressPostalCode", endereco.getCep().replace(".", "").replace("-", "")));
		nameValuePairs.add(new BasicNameValuePair("shippingAddressCity"      , endereco.getCidade()      ));
		nameValuePairs.add(new BasicNameValuePair("shippingAddressState"     , endereco.getUf()          ));
		nameValuePairs.add(new BasicNameValuePair("shippingAddressCountry"   , "BRA"                     ));

		if (metodoEntrega != null) {
			if (metodoEntrega.getValue().equalsIgnoreCase("PAC")) {
				nameValuePairs.add(new BasicNameValuePair("shippingType", "1"));
			} else if (metodoEntrega.getValue().equalsIgnoreCase("'SEDEX'")) {
				nameValuePairs.add(new BasicNameValuePair("shippingType", "2"));
			} else {
				nameValuePairs.add(new BasicNameValuePair("shippingType", "3"));
			}
			nameValuePairs.add(new BasicNameValuePair("shippingCost", metodoEntrega.getValor().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()));
		} else {
			nameValuePairs.add(new BasicNameValuePair("shippingType", "3"));
			nameValuePairs.add(new BasicNameValuePair("shippingCost", BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()));
		}
		nameValuePairs.add(new BasicNameValuePair("extraAmount", acrescimo.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()));

		for (int i = 0; i < itens.size(); i++) {
			PreCompraItem item = itens.get(i);
			nameValuePairs.add(new BasicNameValuePair("itemId"+(i+1)         , item.getId()                   ));
			nameValuePairs.add(new BasicNameValuePair("itemDescription"+(i+1), item.getNome()                 ));
			nameValuePairs.add(new BasicNameValuePair("itemAmount"+(i+1)     , item.getPrecoUnitario().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()));
			nameValuePairs.add(new BasicNameValuePair("itemQuantity"+(i+1)   , item.getQuantidade().toString()));
		}

		return nameValuePairs;
	}

	@RequestMapping(value = "/notificacaoPagSeguro", method = RequestMethod.POST)
	public @ResponseBody String notificacaoPagSeguroPost(String id, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {
		notificationPagSeguroOptions(response);
		Map<String, String[]> m = request.getParameterMap();
		String notificationCode;
		try {
			notificationCode = m.get("notificationCode")[0];
		} catch (Exception e) {
			notificationCode = null;
		}
		String adClientId = AMRProperties.getInstance().getPropriedade("adclientid");
		String retorno = checkoutPagSeguroService.consultarNotificacao(adClientId, notificationCode);
		Transacao transacao = JAXB.unmarshal((new StringReader(retorno)), Transacao.class);

		DebtPayment debtPayment = debtPaymentDao.carregaPorId(transacao.getReference());
		try {
			MetodoPagamentoPS pagamentoPS = metodoPagamentoPSDao.carregaPorAdClientIdPaymentMethodType(adClientId, transacao.getPaymentMethod().getType());
			debtPayment.setPaymentMethodId(pagamentoPS.getAcPaymentMethodId());
			debtPayment = debtPaymentDao.atualiza(debtPayment);
			PreCompra compra = preCompraDao.porNumeroDePedido(debtPayment.getcOrderId());
			compra.setAcPaymentMethodId(pagamentoPS.getAcPaymentMethodId());
			compra.setNumcv(transacao.getCode());
			compra = preCompraDao.atualiza(compra);
		} catch (Exception e) {
			System.out.println("DebtPayment (" + debtPayment.getId() + ") sem PreCompra");
		}

		gravarNotificacao(null, debtPayment.getId(), retorno, transacao.getStatus());

		switch (transacao.getStatus()) {
		case 1: // Aguardando pagamento
			// Nada a fazer
			break;
		case 2: // Em análise
			// Nada a fazer
			break;
		case 3: // Paga
			pagar(transacao.getPaymentMethod().getType(), debtPayment);
			break;
		case 4: // Disponível
			// TODO Decidir o que fazer
			break;
		case 5: // Em disputa
			congelarConta(debtPayment.getcBPartnerId());
			break;
		case 6: // Devolvida
			congelarConta(debtPayment.getcBPartnerId());
			break;
		case 7: // Cancelada
			congelarConta(debtPayment.getcBPartnerId());
			break;
		case 8: // Debitado
			congelarConta(debtPayment.getcBPartnerId());
			break;
		case 9: // Retenção temporária
			congelarConta(debtPayment.getcBPartnerId());
			break;
		default:
			break;
		}

		return "OK";
	}

	private void pagar(Integer paymentMethodType, DebtPayment debtPayment) throws Exception {
		if (paymentMethodType.equals(1) || paymentMethodType.equals(3)) {
			// Crédito ou Débito
			preCompraDao.pagar(debtPayment.getId());
		} else if (paymentMethodType.equals(2)) {
			// Boleto
			preCompraDao.pagar(debtPayment.getId());
		}
	}

	private void congelarConta(String parceiroId) throws Exception {
		ParceiroNegocios parceiro = parceiroNegociosDao.carregaPorId(parceiroId);
		parceiro.setAmrIsactive("N");
		parceiro = parceiroNegociosDao.atualiza(parceiro);
	}

	private void gravarNotificacao(Usuario usuario, String debtPatmentId, String retornoTransacao, Integer status) throws Exception {
		String adClientId;
		String adOrgId;
		String user;
		if (usuario == null) {
			adClientId = AMRProperties.getInstance().getPropriedade("adclientid");
			adOrgId = AMRProperties.getInstance().getPropriedade("adorgid");
			user = null;
		} else {
			adClientId = usuario.getAdClientId();
			adOrgId = usuario.getAdOrgId();
			user = usuario.getId();
		}
		NotificacaoPS notificacaoPS = new NotificacaoPS();
		notificacaoPS.setId(notificacaoPSDao.getUUID());
		notificacaoPS.setAdClientId(adClientId);
		notificacaoPS.setAdOrgId(adOrgId);
		notificacaoPS.setCreatedBy(user);
		notificacaoPS.setUpdatedBy(user);
		notificacaoPS.setDebtPaymentId(debtPatmentId);
		notificacaoPS.setNotificacao(retornoTransacao);
		notificacaoPS.setStatus(status);
		notificacaoPS = notificacaoPSDao.salva(notificacaoPS);
	}
}
