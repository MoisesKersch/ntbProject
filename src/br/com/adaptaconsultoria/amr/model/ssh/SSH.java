package br.com.adaptaconsultoria.amr.model.ssh;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 *
 * @author Will Zaniol
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class SSH implements Serializable {

	private String host;
	private int porta = 22;
	private String usuario;
	private String senha;
	private String arquivoChavePublica;

	public SSH() {
		super();
	}

	private Session conecta() throws Exception {
		JSch jsch = new JSch();

		SSHUsuario info = new SSHUsuario();
		if (arquivoChavePublica != null && !arquivoChavePublica.isEmpty()) {
			jsch.addIdentity(arquivoChavePublica);
			info.setChave(arquivoChavePublica);
		} else
			info.setSenha(senha);

		Session session = jsch.getSession(usuario, host, porta);
		session.setUserInfo(info);

		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.connect();
		return session;
	}

	public void enviaArquivo(File arquivo, File diretorioDestino) throws Exception {
		Session session = null;
		FileInputStream fis = null;
		try {

			session = conecta();

			try {
				Channel channelSFTP = session.openChannel("sftp");
				channelSFTP.connect();

				ChannelSftp sftp = (ChannelSftp) channelSFTP;
				sftp.mkdir(diretorioDestino.getAbsolutePath());

				sftp.disconnect();
				channelSFTP.disconnect();
			} catch (Exception e2) {
			}

			boolean ptimestamp = true;

			String arquivoDestino = diretorioDestino.getAbsolutePath() + "/" + arquivo.getName();
			String command = "scp " + (ptimestamp ? "-p" : "") + " -t " + arquivoDestino;
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			OutputStream out = channel.getOutputStream();
			InputStream in = channel.getInputStream();

			channel.connect();

			if (checkAck(in) != 0) {
				throw new Exception("ERRO 399");
			}

			if (ptimestamp) {
				command = "T " + (arquivo.lastModified() / 1000) + " 0";
				command += (" " + (arquivo.lastModified() / 1000) + " 0\n");
				out.write(command.getBytes());
				out.flush();
				if (checkAck(in) != 0) {
					throw new Exception("ERRO 400");
				}
			}

			long filesize = arquivo.length();
			command = "C0644 " + filesize + " ";
			command += arquivo.getName();
			command += "\n";
			out.write(command.getBytes());
			out.flush();
			if (checkAck(in) != 0) {
				throw new Exception("ERRO 401");
			}

			fis = new FileInputStream(arquivo);
			byte[] buf = new byte[1024];
			while (true) {
				int len = fis.read(buf, 0, buf.length);
				if (len <= 0)
					break;
				out.write(buf, 0, len);
			}
			fis.close();
			fis = null;
			buf[0] = 0;
			out.write(buf, 0, 1);
			out.flush();
			if (checkAck(in) != 0) {
				System.exit(0);
			}
			out.close();

			channel.disconnect();
		} catch (Exception e) {
			System.out.println(e);
			try {
				if (fis != null)
					fis.close();
			} catch (Exception ee) {
			}
			throw e;
		} finally {
			try {
				session.disconnect();
			} catch (Exception e) {
			}
		}
	}

	private int checkAck(InputStream in) throws IOException {
		int b = in.read();
		if (b == 0)
			return b;
		if (b == -1)
			return b;

		if (b == 1 || b == 2) {
			StringBuffer sb = new StringBuffer();
			int c;
			do {
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');
			if (b == 1) { // error
				System.out.print(sb.toString());
			}
			if (b == 2) {
				System.out.print(sb.toString());
			}
		}
		return b;
	}

	public File copiaArquivo(File arquivo, File diretorioDestino) throws Exception {

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		try {
			session = conecta();

			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(arquivo.getParent());
			byte[] buffer = new byte[1024];
			BufferedInputStream bis = new BufferedInputStream(channelSftp.get(arquivo.getName()));

			File newFile = new File(diretorioDestino.getAbsolutePath() + "/" + arquivo.getName());
			OutputStream os = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			int readCount;

			while ((readCount = bis.read(buffer)) > 0) {
				System.out.println("Writing: ");
				bos.write(buffer, 0, readCount);
			}
			bis.close();
			bos.close();

			return newFile;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		} finally {
			try {
				session.disconnect();
			} catch (Exception e) {
			}
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getArquivoChavePublica() {
		return arquivoChavePublica;
	}

	public void setArquivoChavePublica(String arquivoChavePublica) {
		this.arquivoChavePublica = arquivoChavePublica;
	}

}