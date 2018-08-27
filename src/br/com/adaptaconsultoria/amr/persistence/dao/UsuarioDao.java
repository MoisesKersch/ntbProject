package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.Usuario;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface UsuarioDao extends Dao<Usuario> {

	public Usuario carregaPorLogin(String login, String adClientId) throws Exception;

	public Usuario carregaPorParceiroNegocios(String cBPartnerId) throws Exception;

	public String carregaIDDoUsuarioPatrocinador(String adUserId) throws Exception;

	public Usuario carregaPorEMail(String destinatario) throws Exception;

	public Usuario carregaPorCodigoDoParceiroDeNegocios(String codigo) throws Exception;

	public Usuario carregaPorNovaSenha(String novaSenha) throws Exception;

	public String removerCadastroPendente(String direto) throws Exception;

}