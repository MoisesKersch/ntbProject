package br.com.adaptaconsultoria.amr.service;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adaptaconsultoria.amr.model.CarrinhoComprasItem;
import br.com.adaptaconsultoria.amr.model.FormaPagamento;
import br.com.adaptaconsultoria.amr.model.MetodoEntrega;
import br.com.adaptaconsultoria.amr.model.Pacote;
import br.com.adaptaconsultoria.amr.model.PacoteUpgrade;
import br.com.adaptaconsultoria.amr.model.PacoteUpgradeLinha;
import br.com.adaptaconsultoria.amr.model.PreCompra;
import br.com.adaptaconsultoria.amr.model.PreCompraItem;
import br.com.adaptaconsultoria.amr.model.RetAutorizaPreCompra;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.FormaPagamentoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.MetodoEntregaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteUpgradeDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteUpgradeLinhaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraItemDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.proxy.AutorizaPreCompraServiceProxy;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class PreCompraService {

	private PreCompraDao preCompraDao = DaoFactory.getInstance().getPreCompraDao();
	private PreCompraItemDao preCompraItemDao = DaoFactory.getInstance().getPreCompraItemDao();
	private MetodoEntregaDao metodoEntregaDao = DaoFactory.getInstance().getMetodoEntregaDao();
	private PacoteDao pacoteDao = DaoFactory.getInstance().getPacoteDao();
	private EntregaService entregaService = new EntregaService();
	private CarrinhoComprasService carrinhoComprasService = new CarrinhoComprasService();
	private String cDebtPaymentId = null;
	private CartaoRedeService cartaoRedeService = new CartaoRedeService();
	private PacoteUpgradeDao pacoteUpgradeDao = DaoFactory.getInstance().getPacoteUpgradeDao();
	private PacoteUpgradeLinhaDao pacoteUpgradeLinhaDao = DaoFactory.getInstance().getPacoteUpgradeLinhaDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private FormaPagamentoDao formaPagamentoDao = DaoFactory.getInstance().getFormaPagamentoDao();

	public PreCompra criaPreCompraDePacote(String adUserId, String amrPacoteId, Boolean vendaDireta) throws Exception {

		Pacote pacote = pacoteDao.carregaPorId(amrPacoteId);
		if (pacote == null)
			throw new Exception("Pacote não encontrado com o ID: " + amrPacoteId);

		PreCompra preCompra = criaPreCompra(adUserId, vendaDireta);

		BigDecimal precoUnitario = new BigDecimal(vendaDireta ? pacote.getPrecoDe() : pacote.getPrecoPor());
		criaItemPreCompra(preCompra, pacote.getNome(), pacote.getDescricao(), pacote.getmProductId(), 1, precoUnitario, precoUnitario, pacote.getImageURL(),
				pacote.getPeso(), pacote.getAltura(), pacote.getLargura(), pacote.getComprimento(), pacote.getDiametro(), new BigDecimal(pacote.getPrecoDe()),
				new BigDecimal(pacote.getPrecoPor()), pacote.getmPriceListVersionId());

		preCompra = preCompraDao.carregaPorId(preCompra.getId());
		return preCompra;
	}

	public PreCompra criaPreCompraDePacoteUpgrade(String adUserId, String amrPacoteUpgradeId, Boolean vendaDireta) throws Exception {

		PacoteUpgrade pacote = pacoteUpgradeDao.carregaPorUsuario(amrPacoteUpgradeId, adUserId);
		if (pacote == null)
			throw new Exception("Pacote de upgrade não encontrado com o ID: " + amrPacoteUpgradeId);

		PreCompra preCompra = criaPreCompra(adUserId, vendaDireta);
		preCompra.setDescricao(pacote.getNome());
		preCompra.setPeso(pacote.getPeso());
		preCompra.setUpgrade("Y");
		preCompra = preCompraDao.atualiza(preCompra);

		List<PacoteUpgradeLinha> linhas = pacoteUpgradeLinhaDao.listaPorPacote(amrPacoteUpgradeId);
		for (int i = 0; i < linhas.size(); i++) {
			BigDecimal precoUnitario = linhas.get(i).getPricelist();
			for (int j = 0; j < linhas.get(i).getQuantidade().intValue(); j++) {
				criaItemPreCompra(preCompra, pacote.getNome(), pacote.getDescricao(), linhas.get(i).getmProductId(), linhas.get(i).getQuantidade().intValue(),
						precoUnitario, precoUnitario, null, pacote.getPeso(), pacote.getAltura(), pacote.getLargura(), pacote.getComprimento(),
						pacote.getDiametro(), pacote.getPricelist(), pacote.getPricelist(), null);
			}
		}

		preCompra = preCompraDao.carregaPorId(preCompra.getId());
		return preCompra;
	}

	public PreCompra criaPreCompraDeCarrinho(String adUserId, Boolean vendaDireta) throws Exception {

		PreCompra preCompra = criaPreCompra(adUserId, vendaDireta);

		List<CarrinhoComprasItem> itens = carrinhoComprasService.listaItensDoCarrinho(adUserId, vendaDireta);
		for (int i = 0; i < itens.size(); i++) {
			PreCompraItem item = criaItemPreCompra(preCompra, itens.get(i).getNome(), itens.get(i).getDescricao(), itens.get(i).getmProductId(), itens.get(i)
					.getQuantidade(), itens.get(i).getPrecoUnitario(), itens.get(i).getTotal(), itens.get(i).getImagemURL(), itens.get(i).getPeso(),
					itens.get(i).getAltura(), itens.get(i).getLargura(), itens.get(i).getComprimento(), itens.get(i).getDiametro(), itens.get(i).getPrecoDe(),
					itens.get(i).getPrecoPor(), itens.get(i).getmPriceListVersionId());

			preCompra.getItens().add(item);
		}

		preCompra = preCompraDao.carregaPorId(preCompra.getId());

		return preCompra;
	}

	public PreCompra atualizaDadosDeEntregaEPagamento(String id, String adUserId, String amrMetodoEntregaId, String amrFormaPagamentoId, String voucher) throws Exception {

		PreCompra preCompra = preCompraDao.carregaPorId(id);

		if (preCompra == null)
			throw new Exception("Nenhuma pré compra com o ID: " + id + " foi encontrada no banco de dados");

		preCompra.setAmrFormaPagamentoId(amrFormaPagamentoId);
		preCompra.setAmrMetodoEntregaId(amrMetodoEntregaId);
		preCompra.setVoucher(voucher);

		FormaPagamento formaPagamento = DaoFactory.getInstance().getFormaPagamentoDao().carregaPorId(amrFormaPagamentoId);
		
		System.out.println(formaPagamento);
	
		if (formaPagamento == null)
			throw new Exception("Nenhuma forma de pagamento com o ID: " + amrFormaPagamentoId
					+ " foi encontrada no banco de dados.<br>Contate o administrador do sistema e informe o ocorrido.");
		preCompra.setAcPaymentMethodId(formaPagamento.getAcPaymentMethodId());
		
		preCompra.setcPaymentTermId(formaPagamento.getAcPaymenttermId());

		Usuario usuario = new Usuario();
		usuario = usuarioDao.carregaPorId(adUserId);

		BigDecimal desconto = BigDecimal.ZERO;
		try
		{
			desconto = getDesconto(amrFormaPagamentoId, preCompra.getTotalLinhas(), usuario, id);
		} catch (Exception ignorar) {
		}

		MetodoEntrega metodoEntrega = metodoEntregaDao.porPreCompraMetodoEntrega(preCompra.getId(), amrMetodoEntregaId);
		if (metodoEntrega == null)
			throw new Exception("Método de entrega com o ID: " + amrMetodoEntregaId + " não foi encontrado no sistema.\nContate o suporte técnico.");

		if (metodoEntrega.getMetodoentrega().equalsIgnoreCase("Correios")) {
			BigDecimal valor = entregaService.calculaPrecoDosCorreios(metodoEntrega, adUserId, preCompra.getComprimento(), preCompra.getAltura(),
					preCompra.getLargura(), preCompra.getDiametro(), preCompra.getPeso().toString());
			metodoEntrega.setValor(valor);
		}

		preCompra.setDeliveryviarule(metodoEntrega.getDeliveryviarule());
		preCompra.setmShipperId(metodoEntrega.getM_shipper_id());
		preCompra.setAcServicoentregaId(metodoEntrega.getAcServicoentregaId());
		preCompra.setValorEntrega(metodoEntrega.getValor());

		if (metodoEntrega.getValor() != null)
			preCompra.setValorEntrega(metodoEntrega.getValor());

		BigDecimal total = BigDecimal.ZERO;
		BigDecimal totalItens = null;
		
		if (preCompra.getAmrFormaPagamentoId().equals("24B1C40DC7364229AF3C452F9B5057E9") && desconto.compareTo(BigDecimal.ZERO) > 0)
		{
			totalItens = new BigDecimal("0");
			for (PreCompraItem x:  preCompra.getItens()) {
				x.setPrecoUnitario( formaPagamento.getDescontoLoja().divide(new BigDecimal("100")).multiply(x.getPrecoUnitario()) );
				total = total.add(x.getTotal());
			}
			preCompra.setDescontoEspecial("Y");
			totalItens = preCompra.getTotalItens().subtract(desconto);
		}
		
		preCompra.setTotalLinhas(total);
		preCompra.setAcrescimo(BigDecimal.ZERO);
		
		if (totalItens != null)
			preCompra.setTotalItens(totalItens);
		else
			preCompra.setTotalItens(preCompra.getTotalItens());
		
		preCompra.setTotal(total);
		preCompra.calculaTotal();
		
		try {
			preCompra = preCompraDao.atualiza(preCompra);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return preCompra;
	}
	
	public BigDecimal getDesconto(String paymentId, BigDecimal oldValue, Usuario usuario, String preCompraId) throws Exception
	{
		PreCompra preCompra = preCompraDao.carregaPorId(preCompraId);
		
		boolean obrigatorioAdesao = false;;
		
		for (PreCompraItem x : preCompra.getItens())
		{
			Pacote pacote = pacoteDao.getProductDiscount(usuario.getAdClientId(), x.getmProductId());
			
			if (pacote.getObrigatorioAdesao().equals("Y"))
			{
				obrigatorioAdesao = true;
				break;
			}
			if (pacote.getObrigatorioAdesao().equals("N"))
			{
				obrigatorioAdesao = false;
				break;
			}
		}
		
		try
		{
			FormaPagamento formaPagamento = formaPagamentoDao.carregaPorId(paymentId);
			
			if (obrigatorioAdesao)
			{
				if (formaPagamento.getDescontoAdesao() != null)
					return formaPagamento.getDescontoAdesao().divide(new BigDecimal("100")).multiply(oldValue);
				else return null;
			}
			else
			{
				if (formaPagamento.getDescontoLoja() != null)
					return formaPagamento.getDescontoLoja().divide(new BigDecimal("100")).multiply(oldValue);
				else return null;
			}
		} catch (Exception e)
		{
			return null;
		}
	}

	private PreCompra criaPreCompra(String adUserId, boolean vendaDireta) throws Exception {
		PreCompra preCompra = new PreCompra();
		preCompra.setId(preCompraDao.getUUID());
		preCompra.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
		preCompra.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
		preCompra.setCreatedby(adUserId);
		preCompra.setUpdatedby(adUserId);
		preCompra.setAdUserId(adUserId);
		preCompra.setAutorizada("N");
		preCompra.setNumero(preCompraDao.proximoNumeroDePedido());
		preCompra.setVendaDireta(vendaDireta ? "Y" : "N");

		if (!vendaDireta) {
			Usuario usuario = usuarioDao.carregaPorId(adUserId);
			preCompra.setCliente(usuario.getParceiroNegocios());
		}

		return preCompraDao.salva(preCompra);
	}

	private PreCompraItem criaItemPreCompra(PreCompra preCompra, String nome, String descricao, String mProductId, Integer quantidade,
			BigDecimal precoUnitario, BigDecimal total, String imagemURL, BigDecimal peso, BigDecimal altura, BigDecimal largura, BigDecimal comprimento,
			BigDecimal diametro, BigDecimal precoDe, BigDecimal precoPor, String mPriceListVersionId) throws Exception {
		PreCompraItem item = new PreCompraItem();
		item.setId(preCompraDao.getUUID());
		item.setAdClientId(preCompra.getAdClientId());
		item.setAdOrgId(preCompra.getAdOrgId());
		item.setCreatedby(preCompra.getCreatedby());
		item.setUpdatedby(preCompra.getUpdatedby());
		item.setPreCompra(preCompra);
		item.setNome(nome);
		item.setDescricao(descricao);
		item.setImagemURL(imagemURL);
		item.setmProductId(mProductId);
		item.setQuantidade(quantidade);
		item.setPrecoUnitario(precoUnitario);
		item.setTotal(total);
		item.setPeso(peso);
		item.setAltura(altura);
		item.setLargura(largura);
		item.setComprimento(comprimento);
		item.setDiametro(diametro);
		item.setPrecoDe(precoDe);
		item.setPrecoPor(precoPor);
		item.setmPriceListVersionId(mPriceListVersionId);
		return preCompraItemDao.salva(item);
	}

	public String compra(String id, String retornoCodigo, String retornoMensagem) throws Exception {

		PreCompra preCompra = preCompraDao.carregaPorId(id);
		
		if (preCompra == null)
			throw new Exception("Nenhuma pre venda com o ID: <h4>" + id
					+ "</h4> foi encontrada no banco de dados.<br>Contate o administrador do sistema para maiores informações");

		preCompra.setRetornoCodigo(retornoCodigo);
		preCompra.setRetornoMensagem(retornoMensagem);
		preCompra = preCompraDao.atualiza(preCompra);

		this.cDebtPaymentId = null;

		if (retornoCodigo.equals("0")) {

			if (preCompra.getAmrFormaPagamentoId().equalsIgnoreCase("CARTAO")) {
				preCompra = cartaoRedeService.confirmaTransacao(preCompra);

				if (!preCompra.getAutorizada().equalsIgnoreCase("Y"))
					throw new Exception(preCompra.getRetornoCodigo() + " - " + preCompra.getRetornoMensagem());
			} else {
				preCompra.setParcelas("1");
			}

			AutorizaPreCompraServiceProxy proxy = new AutorizaPreCompraServiceProxy();

			RetAutorizaPreCompra retAutorizaPreCompra = proxy.doGet(preCompra.getId());
			
			if (!retAutorizaPreCompra.isSucesso()) {
				preCompra.setRetornoCodigo("ERRO");
				preCompra.setRetornoMensagem(retAutorizaPreCompra.getDescricao());
				preCompra = preCompraDao.atualiza(preCompra);
				throw new Exception(retAutorizaPreCompra.getDescricao());
			}

			String mensagem = "Pedido de compra concluida com sucesso";
			if (retAutorizaPreCompra.isPagamentoSucesso()) {
				this.cDebtPaymentId = retAutorizaPreCompra.getcDebtPaymentId();
				return mensagem;
			} else {
//				mensagem = "<br>Porém não foi possível completar o pagamento.";
				mensagem += "<br>Motivo: " + retAutorizaPreCompra.getDescricao();
			}
			return mensagem;
		}

		else {
			throw new Exception(retornoCodigo + " - " + retornoMensagem);
		}

	}

	public String getCDebtPaymentId() {
		return cDebtPaymentId;
	}

	public static void main(String[] args) throws Exception {

		PreCompraDao preCompraDao = DaoFactory.getInstance().getPreCompraDao();
		PreCompra preCompra = preCompraDao.carregaPorId("AFE9E48900894859ACA430FCAF5F6D00");

		AutorizaPreCompraServiceProxy proxy = new AutorizaPreCompraServiceProxy();
		RetAutorizaPreCompra retAutorizaPreCompra = proxy.doGet(preCompra.getId());
		if (!retAutorizaPreCompra.isSucesso()) {
			preCompra.setRetornoCodigo("ERRO");
			preCompra.setRetornoMensagem(retAutorizaPreCompra.getDescricao());
			preCompra = preCompraDao.atualiza(preCompra);
			throw new Exception(retAutorizaPreCompra.getDescricao());
		}

		String mensagem = "Pedido de compra concluida com sucesso";
		if (retAutorizaPreCompra.isPagamentoSucesso()) {
			System.out.println(retAutorizaPreCompra.getcDebtPaymentId());
			System.out.println(mensagem);
		} else {
			mensagem = "<br>Porém não foi possível completar o pagamento.";
			mensagem += "<br>Motivo: " + retAutorizaPreCompra.getDescricao();
		}
		System.out.println(mensagem);

	}

}