package br.com.adaptaconsultoria.amr.service;

import java.io.File;
import java.io.Serializable;

import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.model.Arquivo;
import br.com.adaptaconsultoria.amr.model.Documento;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.model.ssh.SSH;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.ArquivoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.DocumentoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.util.io.FileUtil;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class DocumentoService {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private ArquivoDao arquivoDao = DaoFactory.getInstance().getArquivoDao();
	private DocumentoDao documentoDao = DaoFactory.getInstance().getDocumentoDao();

	public Documento downloadDocumento(Serializable id) throws Exception {

		Documento doc = documentoDao.carregaPorId(id);
		if (doc == null)
			throw new Exception("Nenhum documento com o ID: " + id + " foi encontrado no banco de dados");

		String downloadSSH = AMRProperties.getInstance().getPropriedade("download.ssh");
		if (downloadSSH != null && downloadSSH.equalsIgnoreCase("true"))
			doc.setUrl(copiaArquivo(new File(doc.getUrl())).getAbsolutePath());

		return doc;
	}

	private File copiaArquivo(File arquivo) throws Exception {
		String sftpHost = AMRProperties.getInstance().getPropriedade("ssh.host");
		int sftpPort = Integer.parseInt(AMRProperties.getInstance().getPropriedade("ssh.port"));
		String sftpUser = AMRProperties.getInstance().getPropriedade("ssh.user");
		String sftpPass = AMRProperties.getInstance().getPropriedade("ssh.password");

		SSH ssh = new SSH();
		ssh.setHost(sftpHost);
		ssh.setPorta(sftpPort);
		ssh.setUsuario(sftpUser);
		ssh.setSenha(sftpPass);
		return ssh.copiaArquivo(arquivo, new File(AMRProperties.getInstance().getPropriedade("diretorio.temp")));
	}

	private void enviaArquivo(File arquivo, File diretorioDestino) throws Exception {
		String sftpHost = AMRProperties.getInstance().getPropriedade("ssh.host");
		int sftpPort = Integer.parseInt(AMRProperties.getInstance().getPropriedade("ssh.port"));
		String sftpUser = AMRProperties.getInstance().getPropriedade("ssh.user");
		String sftpPass = AMRProperties.getInstance().getPropriedade("ssh.password");

		SSH ssh = new SSH();
		ssh.setHost(sftpHost);
		ssh.setPorta(sftpPort);
		ssh.setUsuario(sftpUser);
		ssh.setSenha(sftpPass);
		ssh.enviaArquivo(arquivo, diretorioDestino);
	}

	public File carregaDocumentoDoUsuario(String arquivoId) throws Exception {
		Arquivo arquivo = arquivoDao.carregaPorId(arquivoId);
		if (arquivo == null)
			return null;

		File anexo = copiaArquivo(arquivo.path());
		return anexo;
	}

	public void adicionaDocumentoParaOUsuario(String usuarioId, String absolutePath) throws Exception {
		Usuario usuario = usuarioDao.carregaPorId(usuarioId);

		if (usuario == null)
			throw new Exception("Nenhum usuário com esse ID foi encontrado no sistema");

		File file = new File(absolutePath);

		Arquivo arquivo = new Arquivo();
		arquivo.setId(arquivoDao.getUUID());
		arquivo.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
		arquivo.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
		arquivo.setCreatedby(usuario.getId());
		arquivo.setUpdatedby(usuario.getId());
		arquivo.setSeqNo(arquivoDao.getProximoNumeroDaSequencia(usuario.getParceiroNegocios().getId()));
		arquivo.setAdRecordId(usuario.getParceiroNegocios().getId());
		arquivo.setAdTableId("291");
		arquivo.setcDataTypeId(arquivoDao.getDataType(FileUtil.getExtensao(file.getName())));
		arquivo.setName(file.getName());

		String downloadSSH = AMRProperties.getInstance().getPropriedade("download.ssh");
		if (downloadSSH != null && downloadSSH.equalsIgnoreCase("true"))
			enviaArquivo(file, new File(arquivo.path().getParent()));
		else {
			File dir = new File(arquivo.path().getParent());
			if (!dir.exists())
				dir.mkdirs();
			FileUtil.copy(file, arquivo.path());
		}

		arquivoDao.salva(arquivo);
	}

}