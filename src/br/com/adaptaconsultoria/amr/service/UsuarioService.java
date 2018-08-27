package br.com.adaptaconsultoria.amr.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.builder.EMailBuilder;
import br.com.adaptaconsultoria.amr.model.AmrLogin;
import br.com.adaptaconsultoria.amr.model.Cadastro;
import br.com.adaptaconsultoria.amr.model.DadosBancarios;
import br.com.adaptaconsultoria.amr.model.EMail;
import br.com.adaptaconsultoria.amr.model.Endereco;
import br.com.adaptaconsultoria.amr.model.Franqueado;
import br.com.adaptaconsultoria.amr.model.ParceiroNegocios;
import br.com.adaptaconsultoria.amr.model.ParceiroNegociosEndereco;
import br.com.adaptaconsultoria.amr.model.ServidorEMail;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.AmrLoginDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CadastroPendenteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.EnderecoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.FranqueadoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosEnderecoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.persistence.util.DataUtil;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.util.FormatUtilities;
import br.com.adaptaconsultoria.amr.util.SenhaUtil;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class UsuarioService {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private ParceiroNegociosDao parceiroNegociosDao = DaoFactory.getInstance().getParceiroNegociosDao();
	private FranqueadoDao franqueadoDao = DaoFactory.getInstance().getFranqueadoDao();
	private CadastroPendenteDao cadastroPendenteDao = DaoFactory.getInstance().getCadastroPendenteDao();
	private ParceiroNegociosEnderecoDao parceiroNegociosEnderecoDao = DaoFactory.getInstance().getParceiroNegociosEnderecoDao();
	private EnderecoDao enderecoDao = DaoFactory.getInstance().getEnderecoDao();
	private AmrLoginDao amrLoginDao = DaoFactory.getInstance().getAmrLoginDao();

	public Usuario login(String login, String senha, String adClient) throws Exception {

		boolean adminLogin = login.startsWith("!");

//		Usuario usuario = usuarioDao.carregaPorLogin(login.replaceFirst("!", "").toUpperCase(), adClient);
		AmrLogin amrLogin = amrLoginDao.carregaPorLogin(login.replaceFirst("!", "").toUpperCase());

		if (amrLogin == null)
			throw new Exception("Nenhum usuário com esse login foi encontrado ativo no sistema");

		Usuario usuario = usuarioDao.carregaPorId(amrLogin.getAdUserId());
		if (usuario == null)
			throw new Exception("Nenhum usuário com esse login foi encontrado no sistema");

		if (adminLogin) {
			Usuario root = usuarioDao.carregaPorLogin(AMRProperties.getInstance().getPropriedade("admin"), AMRProperties.getInstance().getPropriedade("adclientid"));
			if (!root.getSenha().equals(FormatUtilities.sha1Base64(senha)))
				throw new Exception("Senha incorreta!");
		}

		else {
			if (usuario.getSenha() == null)
				throw new Exception("Usuário sem senha cadastrada!");
				
			if (!usuario.getSenha().equals(FormatUtilities.sha1Base64(senha)))
				throw new Exception("Senha incorreta!");
		}

		return usuario;
	}

	public void alteraSenha(String adUserId, String senhaAtual, String senhaNova) throws Exception {

		Usuario usuario = usuarioDao.carregaPorId(adUserId);
		if (usuario == null)
			throw new Exception("Nenhum usuário com esse login foi encontrado no sistema");

		if (!usuario.getSenha().equals(FormatUtilities.sha1Base64(senhaAtual)))
			throw new Exception("Senha atual incorreta!");

		usuario.setSenha(FormatUtilities.sha1Base64(senhaNova));
		usuarioDao.atualiza(usuario);
	}

	public void alteraSenhaFinanceira(String adUserId, String senhaAtual, String senhaNova) throws Exception {

		Usuario usuario = usuarioDao.carregaPorId(adUserId);
		if (usuario == null)
			throw new Exception("Nenhum usuário com esse login foi encontrado no sistema");

		if (usuario.getSenhaFinanceira() != null && !usuario.getSenhaFinanceira().isEmpty()) {
			if (!usuario.getSenhaFinanceira().equals(FormatUtilities.sha1Base64(senhaAtual)))
				throw new Exception("Senha atual incorreta!");
		}

		usuario.setSenhaFinanceira(FormatUtilities.sha1Base64(senhaNova));
		usuarioDao.atualiza(usuario);
	}

	public Cadastro criaCadastroDeParceiroDeNegocios(String id, boolean meuCadastro, String adUserId) throws Exception {
		Cadastro cadastro = new Cadastro();

		ParceiroNegocios parceiroNegocios = parceiroNegociosDao.carregaPorId(id);
		if (parceiroNegocios == null)
			throw new Exception("Nenhum parceiro de negócios com esse ID: " + id + " foi encontrado.");

		cadastro.setcBPartnerId(id);
		cadastro.setNome(parceiroNegocios.getNome());
		cadastro.setCpfCnpj(parceiroNegocios.getCnpj());
		cadastro.setNit(parceiroNegocios.getNit());
		if (!meuCadastro) {
			Franqueado franqueado = franqueadoDao.carregaPorUsuario(adUserId);
			if (franqueado != null)
				cadastro.setLiberaCrescimento(franqueado.getLiberaCrescimento());
			cadastro.setCrescimento(parceiroNegocios.getCrescimento());
		}

		ParceiroNegocios patrocinador = parceiroNegociosDao.carregaPorId(parceiroNegocios.getParent());
		if (patrocinador != null)
			cadastro.setPatrocinador(patrocinador.getNome());

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			cadastro.setDataNascimento(dateFormat.format(parceiroNegocios.getDataNascimento()));
		} catch (Exception e) {
		}
		cadastro.setEmail(parceiroNegocios.getEmail());

		cadastro.setTipoTelefone1(parceiroNegocios.getCelularTipoTelefone());
		cadastro.setDddTelefone1(parceiroNegocios.getCelularDDD());
		cadastro.setNumeroTelefone1(parceiroNegocios.getCelularNumero());
		cadastro.setOperadoraTelefone1(parceiroNegocios.getCelularOperadora());
		cadastro.setGenero(parceiroNegocios.getGenero());

		List<ParceiroNegociosEndereco> parceiroNegociosEndereco = parceiroNegociosEnderecoDao.listaPorParceiroNegocios(id);
		if (parceiroNegociosEndereco != null && !parceiroNegociosEndereco.isEmpty()) {
			cadastro.setcBPartnerLocationId(parceiroNegociosEndereco.get(0).getId());
			cadastro.setTipoTelefone2(parceiroNegociosEndereco.get(0).getTipoTelefone());
			cadastro.setDddTelefone2(parceiroNegociosEndereco.get(0).getDdd() != null ? String.valueOf(parceiroNegociosEndereco.get(0).getDdd()) : "");
			cadastro.setNumeroTelefone2(parceiroNegociosEndereco.get(0).getTelefone());
			cadastro.setReferencia(parceiroNegociosEndereco.get(0).getReferencia());

			List<Endereco> enderecos = enderecoDao.listaPorParceiroNegocios(id);
			if (enderecos != null && !enderecos.isEmpty()) {
				cadastro.setcLocationId(enderecos.get(0).getId());
				cadastro.setCep(enderecos.get(0).getCep());
				cadastro.setRua(enderecos.get(0).getLogradouro());
				cadastro.setNumero(enderecos.get(0).getNumero());
				cadastro.setBairro(enderecos.get(0).getBairro());
				cadastro.setCidade(enderecos.get(0).getCidade());
				cadastro.setEstado(enderecos.get(0).getEstado());
				cadastro.setComplemento(enderecos.get(0).getComplemento());
			}
		}

		cadastro.setTipoTelefone3(parceiroNegocios.getOutroTipoTelefone());
		cadastro.setDddTelefone3(parceiroNegocios.getOutroDDD());
		cadastro.setNumeroTelefone3(parceiroNegocios.getOutroTelefone());
		cadastro.setOperadoraTelefone3(parceiroNegocios.getOutroOperadora());

		Usuario usuario = usuarioDao.carregaPorParceiroNegocios(id);
		if (usuario != null) {
			cadastro.setAdUserId(usuario.getId());
			cadastro.setUsuario(usuario.getLogin().toUpperCase());
			cadastro.setSenha(usuario.getSenha());
			if (meuCadastro)
				cadastro.setCrescimento(usuario.getCrescimento());
		}

		return cadastro;
	}

	public void salvaMeuCadastro(Cadastro cadastro) throws Exception {
		salvaParceiroDeNegocios(cadastro, true);
		salvaUsuario(cadastro, true);
		salvaEndereco(cadastro);
	}

	public void salvaCadastroPendente(Cadastro cadastro) throws Exception {
		salvaParceiroDeNegocios(cadastro, false);
		salvaUsuario(cadastro, false);
		salvaEndereco(cadastro);
	}

	private void salvaEndereco(Cadastro cadastro) throws Exception {

		ParceiroNegociosEndereco endereco = parceiroNegociosEnderecoDao.carregaPorId(cadastro.getcBPartnerLocationId());
		if (endereco != null) {

			endereco.setTipoTelefone(cadastro.getTipoTelefone2());
			endereco.setDdd(cadastro.getDddTelefone2() != null && !cadastro.getDddTelefone2().isEmpty() ? Integer.parseInt(cadastro.getDddTelefone2()) : null);
			endereco.setTelefone(cadastro.getNumeroTelefone2());
			endereco.setReferencia(cadastro.getReferencia());
			parceiroNegociosEnderecoDao.atualiza(endereco);
		}

		Endereco localizacao = enderecoDao.carregaPorId(cadastro.getcLocationId());
		if (localizacao != null) {

			localizacao.setCep(cadastro.getCep());
			localizacao.setLogradouro(cadastro.getRua());
			localizacao.setNumero(cadastro.getNumero());
			localizacao.setBairro(cadastro.getBairro());
			localizacao.setCidade(cadastro.getCidade());
			localizacao.setEstado(cadastro.getEstado());
			localizacao.setComplemento(cadastro.getComplemento());
			enderecoDao.atualiza(localizacao);
		}
	}

	private void salvaUsuario(Cadastro cadastro, boolean meuCadastro) throws Exception {
		Usuario usuario = usuarioDao.carregaPorId(cadastro.getAdUserId());
		if (usuario != null) {

			if (meuCadastro) {
				usuario.setCrescimento(cadastro.getCrescimento());
			}

			else {
				if (cadastro.getSenha() != null && !cadastro.getSenha().isEmpty() && !cadastro.getSenha().equals(usuario.getSenha())) {
					usuario.setSenha(FormatUtilities.sha1Base64(cadastro.getSenha()));
				}
				usuario.setLogin(cadastro.getUsuario().toUpperCase());
				usuario.setNome(cadastro.getNome());
			}

			usuarioDao.atualiza(usuario);
		}
	}

	private void salvaParceiroDeNegocios(Cadastro cadastro, boolean meuCadastro) throws Exception {

		ParceiroNegocios parceiroNegocios = parceiroNegociosDao.carregaPorId(cadastro.getcBPartnerId());
		if (parceiroNegocios == null)
			throw new Exception("Nenhum parceiro de negócios com esse ID: " + cadastro.getcBPartnerId() + " foi encontrado.");

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		parceiroNegocios.setNome(cadastro.getNome());
		parceiroNegocios.setCnpj(cadastro.getCpfCnpj());
		parceiroNegocios.setDataNascimento(dateFormat.parse(cadastro.getDataNascimento()));
		parceiroNegocios.setEmail(cadastro.getEmail());
		parceiroNegocios.setCelularTipoTelefone(cadastro.getTipoTelefone1());
		parceiroNegocios.setCelularDDD(cadastro.getDddTelefone1());
		parceiroNegocios.setCelularNumero(cadastro.getNumeroTelefone1());
		parceiroNegocios.setCelularOperadora(cadastro.getOperadoraTelefone1());
		parceiroNegocios.setOutroTipoTelefone(cadastro.getTipoTelefone3());
		parceiroNegocios.setOutroDDD(cadastro.getDddTelefone3());
		parceiroNegocios.setOutroTelefone(cadastro.getNumeroTelefone3());
		parceiroNegocios.setOutroOperadora(cadastro.getOperadoraTelefone3());
		parceiroNegocios.setGenero(cadastro.getGenero());
		parceiroNegocios.setNit(cadastro.getNit());
		if (!meuCadastro)
			parceiroNegocios.setCrescimento(cadastro.getCrescimento());

		parceiroNegociosDao.atualiza(parceiroNegocios);
	}

	public void pagaCadastroPendente(String adUserId, Cadastro cadastro) throws Exception {

		Usuario usuario = usuarioDao.carregaPorId(adUserId);
		if (usuario == null)
			throw new Exception("Nenhum registro de usuario com o id de sessão encontrado no banco de dados");

		if (usuario.getSenhaFinanceira() == null || usuario.getSenhaFinanceira().isEmpty())
			throw new Exception("Senha financeira não confere");

		if (!usuario.getSenhaFinanceira().equals(FormatUtilities.sha1Base64(cadastro.getSenhaFinanceira())))
			throw new Exception("Senha financeira não confere");

		cadastroPendenteDao.paga(cadastro.getAdUserId());
	}

	public String solicitaNovaDefinicaoDeSenha(String login) throws Exception {

		Usuario usuario = usuarioDao.carregaPorLogin(login, AMRProperties.getInstance().getPropriedade("adclientid"));
		if (usuario == null)
			throw new Exception("Não foi encontrado um usuário com esse login: " + login);

		String senha = DataUtil.getId();
		usuario.setSolicitouNovaSenha("Y");
		usuario.setNovaSenha(senha);
		usuarioDao.atualiza(usuario);

		ServidorEMail servidor = EMailBuilder.criaServidorPorProperties();

		EMail email = new EMail();
		email.setServidor(servidor);
		email.setAssunto("Backoffice - Solicitação de redefinição de senha");
		email.setMensagem(EMailBuilder.criaMensagemEsqueciMinhaSenhaSolicitacao(usuario.getLogin(), senha));
		email.addDestinatario(usuario.getParceiroNegocios().getEmail());
		email.setHtml(true);
		email.envia();

		return "Um e-mail com a solicitação de nova senha para: " + usuario.getParceiroNegocios().getEmail();
	}

	public String redefineSenhaEEnviaPorEMail(String novaSenha) throws Exception {

		Usuario usuario = usuarioDao.carregaPorNovaSenha(novaSenha);
		if (usuario == null)
			throw new Exception("Não foi possivel redefinir nova senha");

		String senha = SenhaUtil.criaSenhaCom4Caracteres();
		usuario.setSolicitouNovaSenha("N");
		usuario.setNovaSenha(null);
		usuario.setSenha(FormatUtilities.sha1Base64(senha));
		usuarioDao.atualiza(usuario);

		ServidorEMail servidor = EMailBuilder.criaServidorPorProperties();

		EMail email = new EMail();
		email.setServidor(servidor);
		email.setAssunto("Backoffice - Redefinição de senha");
		email.setMensagem(EMailBuilder.criaMensagemEsqueciMinhaSenha(usuario.getLogin(), senha));
		email.addDestinatario(usuario.getParceiroNegocios().getEmail());
		email.setHtml(true);
		email.envia();

		return "Um e-mail com sua nova senha foi enviado para: " + usuario.getParceiroNegocios().getEmail();
	}

	public DadosBancarios carregaDadosBancarios(String cBPartnerId, String adUserId) throws Exception {

		ParceiroNegocios parceiroNegocios = parceiroNegociosDao.carregaPorId(cBPartnerId);
		if (parceiroNegocios == null)
			throw new Exception("Nenhum parceiro de negócios com esse ID: " + cBPartnerId + " foi encontrado.");

		DadosBancarios dados = new DadosBancarios();
		dados.setcBPartnerId(cBPartnerId);
		dados.setAdUserId(adUserId);
		dados.setBanco(parceiroNegocios.getBancoID());
		dados.setAgencia(parceiroNegocios.getAgencia());
		dados.setConta(parceiroNegocios.getConta());
		dados.setContaDV(parceiroNegocios.getContaDV());
		dados.setOperacao(parceiroNegocios.getBancoOperacao());
		return dados;
	}

	public void salvaDadosBancarios(DadosBancarios dados) throws Exception {

		Usuario usuario = usuarioDao.carregaPorId(dados.getAdUserId());
		if (usuario == null)
			throw new Exception("Nenhum usuário com esse login foi encontrado no sistema");

		if (usuario.getSenhaFinanceira() != null && !usuario.getSenhaFinanceira().isEmpty()) {
			if (!usuario.getSenhaFinanceira().equals(FormatUtilities.sha1Base64(dados.getSenhaFinanceira())))
				throw new Exception("Senha financeira incorreta!");
		}

		ParceiroNegocios parceiroNegocios = parceiroNegociosDao.carregaPorId(dados.getcBPartnerId());
		if (parceiroNegocios == null)
			throw new Exception("Nenhum parceiro de negócios com esse ID: " + dados.getcBPartnerId() + " foi encontrado.");

		parceiroNegocios.setBancoID(dados.getBanco());
		parceiroNegocios.setAgencia(dados.getAgencia());
		parceiroNegocios.setConta(dados.getConta());
		parceiroNegocios.setContaDV(dados.getContaDV());
		parceiroNegocios.setBancoOperacao(dados.getOperacao());
		parceiroNegociosDao.atualiza(parceiroNegocios);
	}

	public String redefineSenhaFinanceiraEEnviaPorEMail(String id) throws Exception {

		Usuario usuario = usuarioDao.carregaPorId(id);
		if (usuario == null)
			throw new Exception("Nenhum usuário com esse ID foi encontrado no sistema");

		String senha = SenhaUtil.criaSenhaCom4Caracteres();
		usuario.setSenhaFinanceira(FormatUtilities.sha1Base64(senha));
		usuarioDao.atualiza(usuario);

		ServidorEMail servidor = EMailBuilder.criaServidorPorProperties();

		EMail email = new EMail();
		email.setServidor(servidor);
		email.setAssunto("Backoffice - Redefinição de senha financeira");
		email.setMensagem(EMailBuilder.criaMensagemEsqueciMinhaSenhaFinanceira(senha));
		email.addDestinatario(usuario.getParceiroNegocios().getEmail());
		email.setHtml(true);
		email.envia();

		return "Um e-mail com sua nova senha financeira foi enviado para: " + email.getDestinatario().get(0);
	}

	public String removerCadastroPendente(String direto) throws Exception {
		return usuarioDao.removerCadastroPendente(direto);
	}

	public static void main(String[] args) throws Exception {

//		String login = "MASTER MIND";
//		String adUserId = "A7A9FC0EBD1B4AC4B7BEC6EF3A0B2B38";
//		String cBPartnerId = "EC9FB4E569694C65A6CD1D5B070E0F0D";

		UsuarioService service = new UsuarioService();

		// System.out.println(service.solicitaNovaDefinicaoDeSenha(login));
//		service.redefineSenhaEEnviaPorEMail("D1D7EBA184554EA09F674B96FA1D2F77");
		service.solicitaNovaDefinicaoDeSenha("NTB3");
	}

}