package br.com.adaptaconsultoria.amr.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.tempuri.CServico;

import br.com.adaptaconsultoria.amr.model.MetodoEntrega;
import br.com.adaptaconsultoria.amr.model.PacoteUpgrade;
import br.com.adaptaconsultoria.amr.model.PreCompra;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.EnderecoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.MetodoEntregaDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.util.lang.TextoUtil;
import br.com.adaptaconsultoria.correios.model.CodigoServico;
import br.com.adaptaconsultoria.correios.model.FormatoEncomenda;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class EntregaService {

	private MetodoEntregaDao metodoEntregaDao = DaoFactory.getInstance().getMetodoEntregaDao();
	private EnderecoDao enderecoDao = DaoFactory.getInstance().getEnderecoDao();

	public List<MetodoEntrega> listaMetodosDeEntrega(PreCompra preCompra, String adUserId) throws Exception {
		BigDecimal pesoKG = preCompra.getPeso();
		BigDecimal comprimentoTotalCM = preCompra.getComprimento();
		BigDecimal alturaEncomendaCM = preCompra.getAltura();
		BigDecimal larguraEncomendaCM = preCompra.getLargura();
		BigDecimal diametroEncomendaCM = preCompra.getDiametro();
		return listaMetodosDeEntrega(adUserId, comprimentoTotalCM, alturaEncomendaCM, larguraEncomendaCM, diametroEncomendaCM, pesoKG, preCompra.getId());
	}

	public List<MetodoEntrega> listaMetodosDeEntrega(PacoteUpgrade pacote, String adUserId, String amrPreCompraId) throws Exception {
		BigDecimal pesoKG = pacote.getPeso();
		BigDecimal comprimentoTotalCM = pacote.getComprimento();
		BigDecimal alturaEncomendaCM = pacote.getAltura();
		BigDecimal larguraEncomendaCM = pacote.getLargura();
		BigDecimal diametroEncomendaCM = pacote.getDiametro();
		return listaMetodosDeEntrega(adUserId, comprimentoTotalCM, alturaEncomendaCM, larguraEncomendaCM, diametroEncomendaCM, pesoKG, amrPreCompraId);
	}

	public List<MetodoEntrega> listaMetodosDeEntrega(String adUserId, BigDecimal comprimentoTotalCM, BigDecimal alturaEncomendaCM,
			BigDecimal larguraEncomendaCM, BigDecimal diametroEncomendaCM, BigDecimal pesoKG, String amrPreCompraId) throws Exception {
		try {

			// TODO verificar se alteração está correta
//			List<MetodoEntrega> lista = metodoEntregaDao.listaPorPesoMaximo(AMRProperties.getInstance().getPropriedade("adclientid"), amrPreCompraId, pesoKG);
			List<MetodoEntrega> lista = metodoEntregaDao.porPreCompra(amrPreCompraId);
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getMetodoentrega().equalsIgnoreCase("Correios")) {
					BigDecimal valor = null;
					/* TODO (Recuperar da versão antiga) Substituido código comentado abaixo pois não está buscando valor nos correios */
//					lista.get(i).setValor(BigDecimal.ZERO);
					try {
						valor = calculaPrecoDosCorreios(lista.get(i), adUserId, comprimentoTotalCM, alturaEncomendaCM, larguraEncomendaCM, diametroEncomendaCM, pesoKG.toString());
						lista.get(i).setValor(valor);
					} catch (Exception e) {
						if (!e.getMessage().startsWith("008")) {
							lista.get(i).setServico(e.getMessage());
							lista.get(i).setValor(BigDecimal.ONE);
							System.out.println(e.getMessage());
							// throw e;
						}
					}
				}
			}
			return lista;
		} catch (Exception e) {
			throw e;
		}
	}

	public BigDecimal calculaPrecoDosCorreios(MetodoEntrega metodoEntrega, String adUserId, BigDecimal comprimentoTotalCM, BigDecimal alturaEncomendaCM,
			BigDecimal larguraEncomendaCM, BigDecimal diametroEncomendaCM, String pesoKG) throws Exception {

		CodigoServico codigoServico = null;
		if (metodoEntrega.getValue().equalsIgnoreCase("PAC"))
			codigoServico = CodigoServico.PAC_VAREJO;
		else if (metodoEntrega.getValue().equalsIgnoreCase("SEDEX"))
			codigoServico = CodigoServico.SEDEX_VAREJO;
		else if (metodoEntrega.getValue().equalsIgnoreCase("SEDEX10"))
			codigoServico = CodigoServico.SEDEX_10_VAREJO;
		else
			throw new Exception("Código do serviço não encontrado para: " + metodoEntrega.getValue());

		String cepOrigem = enderecoDao.carregaCEPOrigem(AMRProperties.getInstance().getPropriedade("adorgid"));
		String cepDestino = enderecoDao.carregaCEPDestino(adUserId);

		if (cepOrigem == null || cepOrigem.isEmpty())
			throw new Exception("Nenhum CEP definido para a organização de origem.\nContate o administrador do sistema e informe o ocorrido.");

		if (cepDestino == null || cepDestino.isEmpty())
			throw new Exception("Nenhum CEP definido para o usuário destino");

		cepOrigem = TextoUtil.removeNaoNumericos(cepOrigem);
		cepDestino = TextoUtil.removeNaoNumericos(cepDestino);

		FormatoEncomenda formatoEncomenda = FormatoEncomenda.FORMATO_CAIXA_PACOTE;

		if (comprimentoTotalCM == null)
			comprimentoTotalCM = BigDecimal.ZERO;
		if (alturaEncomendaCM == null)
			alturaEncomendaCM = BigDecimal.ZERO;
		if (larguraEncomendaCM == null)
			larguraEncomendaCM = BigDecimal.ZERO;
		if (diametroEncomendaCM == null)
			diametroEncomendaCM = BigDecimal.ZERO;

		boolean maoPropria = false;
		boolean servicoValorDeclarado = false;
		BigDecimal valorDeclarado = BigDecimal.ZERO;
		boolean avisoRecebimento = false;

		br.com.adaptaconsultoria.correios.service.EntregaService service = new br.com.adaptaconsultoria.correios.service.EntregaService();
		CServico retorno;
		try {
			retorno = service.calculaPrecoEPrazo(codigoServico, cepOrigem, cepDestino, pesoKG, formatoEncomenda, comprimentoTotalCM, alturaEncomendaCM,
					larguraEncomendaCM, diametroEncomendaCM, maoPropria, servicoValorDeclarado, valorDeclarado, avisoRecebimento);

			return new BigDecimal(retorno.getValor().replaceAll(",", "."));
		} catch (Exception e) {
			throw e;
		}
	}

	public void setMetodoEntregaDao(MetodoEntregaDao metodoEntregaDao) {
		this.metodoEntregaDao = metodoEntregaDao;
	}

	public void setEnderecoDao(EnderecoDao enderecoDao) {
		this.enderecoDao = enderecoDao;
	}

	public static void main(String[] args) throws Exception {
		String adUserId = "D2A5E23CFD124D2BB78E90C886EDE1CB";
		String pacoteUpgradeId = "363E4D897D8440C6860C5356091DFBDB";
		PacoteUpgrade pacote = DaoFactory.getInstance().getPacoteUpgradeDao().carregaPorUsuario(pacoteUpgradeId, adUserId);
		EntregaService service = new EntregaService();
		service.listaMetodosDeEntrega(pacote, adUserId, "");
	}

}