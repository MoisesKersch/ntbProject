package br.com.adaptaconsultoria.amr.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.model.Saque;
import br.com.adaptaconsultoria.amr.model.Transferencia;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.SaqueDao;
import br.com.adaptaconsultoria.amr.persistence.dao.TransferenciaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.util.FormatUtilities;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class FinanceiroService {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private SaqueDao saqueDao = DaoFactory.getInstance().getSaqueDao();
	private TransferenciaDao transferenciaDao = DaoFactory.getInstance().getTransferenciaDao();

	public void saca(String adUserId, Saque saque) throws Exception {

		Usuario usuario = usuarioDao.carregaPorId(adUserId);
		if (usuario == null)
			throw new Exception("Nenhum registro de usuario com o id de sessão encontrado no banco de dados");

		if (usuario.getSenhaFinanceira() == null || usuario.getSenhaFinanceira().isEmpty())
			throw new Exception("Senha financeira não confere");

		if (!usuario.getSenhaFinanceira().equals(FormatUtilities.sha1Base64(saque.getSenhaFinanceira())))
			throw new Exception("Senha financeira não confere");

		saque.setId(saqueDao.getUUID());
		saque.setAdUserId(usuario.getId());
		saque.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
		saque.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
		saque.setcBPartnerId(usuario.getParceiroNegocios().getId());
		saqueDao.salva(saque);
	}

	public String transfere(String adUserId, Transferencia transferencia) throws Exception {

		Usuario usuario = usuarioDao.carregaPorCodigoDoParceiroDeNegocios(transferencia.getAdUserIdCredito());
		if (usuario == null)
			throw new Exception("Nenhum registro de usuario com o login " + transferencia.getAdUserIdCredito() + " encontrado no banco de dados");
		transferencia.setAdUserIdCredito(usuario.getId());

		usuario = usuarioDao.carregaPorId(adUserId);
		if (usuario == null)
			throw new Exception("Nenhum registro de usuario com o id de sessão encontrado no banco de dados");

		if (adUserId.equals(transferencia.getAdUserIdCredito()))
			throw new Exception("Não é possível fazer uma transferência entre a mesma conta");

		if (usuario.getSenhaFinanceira() == null || usuario.getSenhaFinanceira().isEmpty())
			throw new Exception("Senha financeira não confere");

		if (!usuario.getSenhaFinanceira().equals(FormatUtilities.sha1Base64(transferencia.getSenhaFinanceira())))
			throw new Exception("Senha financeira não confere");

		try {
			transferencia.setId(transferenciaDao.getUUID());
			transferencia.setAdUserIdDebito(adUserId);
			transferencia.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
			transferencia.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
			try {
				transferencia.setTaxa(taxaTransferencia(transferencia.getAdClientId(), transferencia.getValor()));				
			} catch (Exception e) {
				transferencia.setTaxa(BigDecimal.ZERO);
			}
			transferenciaDao.salva(transferencia);

			return transferenciaDao.post(transferencia.getId());
		} catch (Exception e) {
			try {
				transferenciaDao.exclui(transferencia);
			} catch (Exception ignorado) {
			}
			throw e;
		}
	}

	public BigDecimal taxaSaque(String clientId) {
		try {
			return saqueDao.taxaSaque(clientId);
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	public BigDecimal taxaSaque(String clientId, BigDecimal valor) {
		try {
			return saqueDao.taxaSaque(clientId, valor);
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	public BigDecimal taxaTransferencia(String clientId, BigDecimal valor) {
		try {
			return transferenciaDao.taxaTransferencia(clientId, valor);
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

}