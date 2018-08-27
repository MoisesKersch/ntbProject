package br.com.adaptaconsultoria.amr.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.model.Endereco;
import br.com.adaptaconsultoria.amr.model.NovoEndereco;
import br.com.adaptaconsultoria.amr.model.ParceiroNegocios;
import br.com.adaptaconsultoria.amr.model.ParceiroNegociosEndereco;
import br.com.adaptaconsultoria.amr.model.PreCompra;
import br.com.adaptaconsultoria.amr.model.PreCompraCliente;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.EnderecoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.NovoEnderecoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosEnderecoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraClienteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.util.lang.TextoUtil;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class PessoaService {

	private ParceiroNegociosDao parceiroNegociosDao = DaoFactory.getInstance().getParceiroNegociosDao();
	private ParceiroNegociosEnderecoDao parceiroNegociosEnderecoDao = DaoFactory.getInstance().getParceiroNegociosEnderecoDao();
	private EnderecoDao enderecoDao = DaoFactory.getInstance().getEnderecoDao();
	private PreCompraDao preCompraDao = DaoFactory.getInstance().getPreCompraDao();
	private PreCompraClienteDao preCompraClienteDao = DaoFactory.getInstance().getPreCompraClienteDao();
	private NovoEnderecoDao novoEnderecoDao = DaoFactory.getInstance().getNovoEnderecoDao();

	public PreCompraCliente carregaParceiroDeNegociosPorDocumentoFiscal(String taxId, ParceiroNegocios parceiroNegocios, boolean venda) throws Exception {
		PreCompraCliente form = new PreCompraCliente();
		form.setReadOnly(!venda);
		form.setCpfCnpj(taxId);

		ParceiroNegocios pn = null;
		if (!venda || (taxId != null && taxId.equalsIgnoreCase("X"))) {
			if (parceiroNegocios != null)
				pn = parceiroNegociosDao.carregaPorId(parceiroNegocios.getId());
		}

		else {
			pn = parceiroNegociosDao.carregaPorTaxID(taxId);
		}

		if (pn == null) {
			if (taxId != null) {
				int qtde = TextoUtil.removeNaoNumericos(taxId).length();
				form.setTipoPessoa(qtde > 11 ? "J" : "F");
			}
			return form;
		}

		form.setcBPartnerId(pn.getId());
		form.setCpfCnpj(pn.getCnpj());
		form.setNome(pn.getNome());

		int qtde = TextoUtil.removeNaoNumericos(pn.getCnpj()).length();
		if (qtde > 11) {
			form.setTipoPessoa("J");
			form.setGenero("M");
		}

		else {
			form.setTipoPessoa("F");
			form.setGenero(pn.getGenero());
		}

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			form.setDataNascimento(dateFormat.format(pn.getDataNascimento()));
		} catch (Exception e) {
		}
		if (form.getDataNascimento() == null)
			form.setDataNascimento("01/01/1900");

		form.setEmail(pn.getEmail());
		form.setDddTelefone(pn.getCelularDDD());
		form.setNumeroTelefone(pn.getCelularNumero());
		form.setOperadoraTelefone(pn.getCelularOperadora());

		List<ParceiroNegociosEndereco> parceiroNegociosEndereco = parceiroNegociosEnderecoDao.listaPorParceiroNegocios(pn.getId());
		if (parceiroNegociosEndereco != null && !parceiroNegociosEndereco.isEmpty()) {
			form.setcBPartnerLocationId(parceiroNegociosEndereco.get(0).getId());
			form.setReferencia(parceiroNegociosEndereco.get(0).getReferencia());

			List<Endereco> enderecos = enderecoDao.listaPorParceiroNegocios(pn.getId());
			if (enderecos != null && !enderecos.isEmpty()) {
				form.setcLocationId(enderecos.get(0).getId());
				form.setCep(enderecos.get(0).getCep());
				form.setRua(enderecos.get(0).getLogradouro());
				form.setNumero(enderecos.get(0).getNumero());
				form.setBairro(enderecos.get(0).getBairro());
				form.setCidade(enderecos.get(0).getCidade());
				form.setEstado(enderecos.get(0).getEstado());
				form.setComplemento(enderecos.get(0).getComplemento());
			}
		}

		return form;
	}

	public void salvaNovoCliente(String adUserId, PreCompraCliente form) throws Exception {

		if (form.getcBPartnerId() == null || form.getcBPartnerId().isEmpty()) {
			form.setId(preCompraClienteDao.getUUID());
			form.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
			form.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
			form.setCreatedby(adUserId);
			form.setUpdatedby(adUserId);
			form = preCompraClienteDao.salva(form);
			preCompraClienteDao.salvaCliente(form.getId());
			form = preCompraClienteDao.carregaPorId(form.getId());
		}

		else {
			atualizaEndereco(false, adUserId, form);
		}

		PreCompra pedido = preCompraDao.carregaPorId(form.getPreCompraId());
		ParceiroNegocios cliente = parceiroNegociosDao.carregaPorId(form.getcBPartnerId());
		pedido.setCliente(cliente);
		pedido.setcBPartnerLocationId(form.getcBPartnerLocationId());
		pedido.setcLocationId(form.getcLocationId());
		preCompraDao.atualiza(pedido);
	}

	private void atualizaEndereco(boolean processa, String adUserId, PreCompraCliente form) throws Exception {
		NovoEndereco novoEndereco = new NovoEndereco();
		novoEndereco.setId(novoEnderecoDao.getUUID());
		novoEndereco.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
		novoEndereco.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
		novoEndereco.setCreatedby(adUserId);
		novoEndereco.setUpdatedby(adUserId);
		novoEndereco.setcBPartnerId(form.getcBPartnerId());
		novoEndereco.setcBPartnerLocationId(form.getcBPartnerLocationId());
		novoEndereco.setcLocationId(form.getcLocationId());
		novoEndereco.setCep(form.getCep());
		novoEndereco.setRua(form.getRua());
		novoEndereco.setNumero(form.getNumero());
		novoEndereco.setComplemento(form.getComplemento());
		novoEndereco.setBairro(form.getBairro());
		novoEndereco.setPais(form.getPais());
		novoEndereco.setEstado(form.getEstado());
		novoEndereco.setCidade(form.getCidade());
		novoEndereco.setReferencia(form.getReferencia());
		novoEndereco.setProcessed("N");
		novoEnderecoDao.salva(novoEndereco);
	}

	public static void main(String[] args) throws Exception {
		PreCompraCliente form = new PreCompraCliente();
		form.setId(DaoFactory.getInstance().getPreCompraClienteDao().getUUID());
		form.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
		form.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
		form.setCreatedby("100");
		form.setUpdatedby("100");

		form.setPreCompraId("A6627CE3B6C044FD8B431858E68BE4EC");
		form.setCpfCnpj("99999999999");
		form.setNome("Atila Abreu de Cunha e Silva");
		form.setDataNascimento("10/09/2015");
		form.setGenero("M");
		form.setTipoPessoa("F");
		form.setEmail("wildson@adaptaconsultoria.com.br");
		form.setCep("89812110");
		form.setRua("Alameda Celular");
		form.setNumero("21");
		form.setComplemento("Complementando o endereço");
		form.setBairro("Centro");
		form.setPais("139");
		form.setEstado("422");
		form.setCidade("3B0AA478F1B14A559A8D296C81066C0C");
		form.setReferencia("Referencia XYZ");
		form.setDddTelefone("49");
		form.setNumeroTelefone("99355977");
		form.setOperadoraTelefone("TIM");

		form = DaoFactory.getInstance().getPreCompraClienteDao().salva(form);
	}

}