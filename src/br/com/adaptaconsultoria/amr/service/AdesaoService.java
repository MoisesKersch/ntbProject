package br.com.adaptaconsultoria.amr.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.model.Aceite;
import br.com.adaptaconsultoria.amr.model.Adesao;
import br.com.adaptaconsultoria.amr.model.Cadastro;
import br.com.adaptaconsultoria.amr.model.Franqueado;
import br.com.adaptaconsultoria.amr.model.ParceiroNegocios;
import br.com.adaptaconsultoria.amr.model.RetEnvAdesao;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.AceiteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.FranqueadoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.proxy.AdesaoServiceProxy;
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
public class AdesaoService {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private FranqueadoDao franqueadoDao = DaoFactory.getInstance().getFranqueadoDao();
	private AceiteDao aceiteDao = DaoFactory.getInstance().getAceiteDao();
	private ParceiroNegociosDao parceiroNegociosDao = DaoFactory.getInstance().getParceiroNegociosDao();

	public void enviaAdesao(Cadastro cadastro) throws Exception {

		Usuario usuario = usuarioDao.carregaPorLogin(cadastro.getUsuario(), AMRProperties.getInstance().getPropriedade("adclientid"));
		
		if (usuario != null)
			throw new Exception("Usuário com esse login já existe no sistema.<br>Mude o login do usuário.");

		ParceiroNegocios parceiroNegocios = parceiroNegociosDao.carregaPorTaxID(cadastro.getCpfCnpj());
		if (parceiroNegocios != null)
			throw new Exception("Já existe alguem cadastrado com esse número de documento.<br>Somente é permitido um cadastro por documento de identificação");

		Adesao adesao = new Adesao();
		adesao.setParceiro(cadastro);
		String xml = adesao.toXML();

		AdesaoServiceProxy proxy = new AdesaoServiceProxy();
		RetEnvAdesao retEnvAdesao = proxy.doPost(xml);

		if (!retEnvAdesao.isSucesso()) {
			throw new Exception(retEnvAdesao.getDescricao());
		}
	}
	
	public void compraParaNovoCadastro(HttpSession session, Cadastro cadastro) throws Exception {

		Usuario patrocinador = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
		Usuario usuario = null;
		for (int i = 0; i < 5; i++) {

			usuario = usuarioDao.carregaPorLogin(cadastro.getUsuario().toUpperCase(), AMRProperties.getInstance().getPropriedade("adclientid"));
			if (usuario != null)
				break;

			System.out.println("1 VEZ NÃO ACHOU");
			Thread.sleep(100);
		}

		SessionUtil.setPatrocinador(session, patrocinador);
		SessionUtil.setUsuarioId(session, usuario);

		Franqueado franqueado = franqueadoDao.carregaPorUsuario(usuario.getId());
		if (franqueado != null) {
			SessionUtil.setAttribute(session, "avatar", franqueado.getAvatar());
			SessionUtil.setAttribute(session, "autenticacompra", franqueado.getAutenticaCompra());
			SessionUtil.setAttribute(session, "codigobp", franqueado.getCodigo());
		}

		Aceite aceite = new Aceite();
		aceite.setId(aceiteDao.getUUID());
		aceite.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
		aceite.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
		aceite.setAdUserId(usuario.getId());
		aceite.setAceito("Y");
		aceite = aceiteDao.salva(aceite);

		SessionUtil.setAceite(session, true);
	}

}