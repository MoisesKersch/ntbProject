package br.com.adaptaconsultoria.amr.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.PagamentoPendenteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.proxy.AtualizaBoletoProxy;
import br.com.adaptaconsultoria.amr.util.FormatUtilities;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class PagamentoService {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private PagamentoPendenteDao pagamentoPendenteDao = DaoFactory.getInstance().getPagamentoPendenteDao();

	public String pagaTitulo(String adUserId, String cDebtPaymentId, String senhaFinanceira, String voucher) throws Exception {

		Usuario usuario = usuarioDao.carregaPorId(adUserId);
		if (usuario == null)
			throw new Exception("Nenhum registro de usuario com o id de sessão encontrado no banco de dados");

		if (usuario.getSenhaFinanceira() == null || usuario.getSenhaFinanceira().isEmpty())
			throw new Exception("Senha financeira não confere");

		if (!usuario.getSenhaFinanceira().equals(FormatUtilities.sha1Base64(senhaFinanceira)))
			throw new Exception("Senha financeira não confere");

		return pagamentoPendenteDao.paga(adUserId, cDebtPaymentId, voucher);
	}

	public File criaBoleto(String id) throws Exception {

		AtualizaBoletoProxy proxy = new AtualizaBoletoProxy();
		String retorno = proxy.doPost(id);
		if (retorno != null && retorno.startsWith("ERRO")) {
			System.out.println(retorno);
			throw new Exception(retorno);
		}

		String downloadSSH = AMRProperties.getInstance().getPropriedade("download.ssh");
		if (downloadSSH != null && downloadSSH.equalsIgnoreCase("true"))
			return copiaArquivo(new File(retorno));

		return new File(retorno);
	}

	private File copiaArquivo(File arquivo) throws Exception {
		String SFTPHOST = AMRProperties.getInstance().getPropriedade("ssh.host");
		int SFTPPORT = 22;
		String SFTPUSER = AMRProperties.getInstance().getPropriedade("ssh.user");
		String SFTPPASS = AMRProperties.getInstance().getPropriedade("ssh.password");

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;

		JSch jsch = new JSch();
		session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
		session.setPassword(SFTPPASS);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();

		channel = session.openChannel("sftp");
		channel.connect();
		channelSftp = (ChannelSftp) channel;
		channelSftp.cd(arquivo.getParent());
		byte[] buffer = new byte[1024];
		BufferedInputStream bis = new BufferedInputStream(channelSftp.get(arquivo.getName()));

		File newFile = new File(AMRProperties.getInstance().getPropriedade("diretorio.temp") + arquivo.getName());
		OutputStream os = new FileOutputStream(newFile);
		BufferedOutputStream bos = new BufferedOutputStream(os);
		int readCount;

		while ((readCount = bis.read(buffer)) > 0) {
			System.out.println("Writing: ");
			bos.write(buffer, 0, readCount);
		}
		bis.close();
		bos.close();

		return new File(AMRProperties.getInstance().getPropriedade("diretorio.temp") + arquivo.getName());
	}

}