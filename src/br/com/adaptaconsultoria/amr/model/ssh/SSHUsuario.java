package br.com.adaptaconsultoria.amr.model.ssh;

import java.io.Serializable;

import com.jcraft.jsch.UserInfo;

/**
 *
 * @author Will Zaniol
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class SSHUsuario implements UserInfo, Serializable {

	private String senha;
	private String chave;

	@Override
	public String getPassphrase() {
		return chave;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public boolean promptPassphrase(String message) {
		return chave != null && !chave.isEmpty();
	}

	@Override
	public boolean promptPassword(String message) {
		return senha != null && !senha.isEmpty();
	}

	@Override
	public boolean promptYesNo(String yesNo) {
		return true;
	}

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

}