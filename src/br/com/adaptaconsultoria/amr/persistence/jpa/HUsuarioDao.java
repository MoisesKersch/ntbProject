package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */

public class HUsuarioDao extends HDao<Usuario> implements UsuarioDao {

	@Override
	public Usuario carregaPorLogin(String login, String adClientId) throws Exception {
		String query = "SELECT obj FROM Usuario obj WHERE obj.adClientId = :adClientId and UPPER(obj.login) = UPPER(:login)";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", adClientId);
		parametros.put("login", login);

		return carrega(query, parametros);
	}

	@Override
	public Usuario carregaPorParceiroNegocios(String cBPartnerId) throws Exception {
		String query = "SELECT obj FROM Usuario obj WHERE obj.parceiroNegocios.id = :cBPartnerId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cBPartnerId", cBPartnerId);

		List<Usuario> lista = pesquisa(query, parametros);
		if (lista != null && !lista.isEmpty())
			return lista.get(0);

		return null;
	}

	@Override
	public String carregaIDDoUsuarioPatrocinador(String adUserId) throws Exception {
		Query q = getEntityManager().createNativeQuery("SELECT amr_getpatrocinador('" + adUserId + "')");
		return (String) q.getSingleResult();
	}

	@Override
	public Usuario carregaPorEMail(String email) throws Exception {
		String query = "SELECT obj FROM Usuario obj JOIN obj.parceiroNegocios p WHERE p.email = :email AND obj.adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("email", email);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));

		return carrega(query, parametros);
	}

	@Override
	public Usuario carregaPorCodigoDoParceiroDeNegocios(String codigo) throws Exception {
		String query = "SELECT obj FROM Usuario obj WHERE obj.parceiroNegocios.codigo = :codigo AND obj.adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", codigo);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));

		List<Usuario> lista = pesquisa(query, parametros);
		if (lista != null && !lista.isEmpty())
			return lista.get(0);

		return null;
	}

	@Override
	public Usuario carregaPorNovaSenha(String novaSenha) throws Exception {
		String query = "SELECT obj FROM Usuario obj WHERE obj.solicitouNovaSenha = 'Y' AND obj.novaSenha = :novaSenha AND obj.adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("novaSenha", novaSenha);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));

		List<Usuario> lista = pesquisa(query, parametros);
		if (lista != null && !lista.isEmpty())
			return lista.get(0);

		return null;
	}

	@Override
	public String removerCadastroPendente(String direto) throws Exception {
		Query q = getEntityManager().createNativeQuery("SELECT amr_excluircadastropendente('" + direto + "')");
		return (String) q.getSingleResult();
	}

	public static void main(String[] args) throws Exception {
		Usuario usuario = DaoFactory.getInstance().getUsuarioDao().carregaPorCodigoDoParceiroDeNegocios("101083");
		System.out.println(usuario);
		if (usuario != null)
			System.out.println(usuario.getNome());
	}

}