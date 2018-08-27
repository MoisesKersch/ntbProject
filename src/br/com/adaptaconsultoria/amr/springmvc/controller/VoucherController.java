package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.model.Voucher;
import br.com.adaptaconsultoria.amr.model.VoucherProduto;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.persistence.dao.VoucherDao;
import br.com.adaptaconsultoria.amr.persistence.dao.VoucherProdutoDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;
import br.com.adaptaconsultoria.amr.util.FormatUtilities;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("request")
public class VoucherController implements Serializable {

	private VoucherDao voucherDao = DaoFactory.getInstance().getVoucherDao();
	private VoucherProdutoDao voucherProdutoDao = DaoFactory.getInstance().getVoucherProdutoDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();

	@RequestMapping(value = "/vouchers", method = GET)
	public ModelAndView lista(HttpSession session) {
		SessionUtil.setSubMenuAtivo(session, "vouchers");
		List<Voucher> lista = new ArrayList<Voucher>();
		try {
			lista = voucherDao.listaDisponiveisPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("vouchers", "lista", lista);
		return model;
	}

	@RequestMapping(value = "/voucherprodutos", method = GET)
	public ModelAndView listaProdutos(HttpSession session) {
		SessionUtil.setSubMenuAtivo(session, "voucherprodutos");
		List<VoucherProduto> lista = new ArrayList<VoucherProduto>();
		try {
			lista = voucherProdutoDao.listaOfertasPorEmpresa(AMRProperties.getInstance().getPropriedade("adclientid"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("voucherprodutos", "lista", lista);
		return model;
	}

	@RequestMapping(value = "/precompravoucher", method = GET)
	public ModelAndView preCompraVoucher(String id, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		try {
			VoucherProduto produto = voucherProdutoDao.carregaPorId(id);
			ModelAndView model = new ModelAndView("precompravoucher", "produto", produto);
			return model;
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return listaProdutos(session);
		}
	}

	@RequestMapping(value = "/compravoucher", method = GET)
	public String compra(String id, String senhaFinanceira, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		try {

			boolean autenticaCompra = SessionUtil.getAttribute(session, "autenticacompra").equals("Y");
			String adUserId = SessionUtil.getUsuarioId(session);

			if (autenticaCompra) {
				Usuario usuario = usuarioDao.carregaPorId(adUserId);
				if (usuario == null)
					throw new Exception("Nenhum registro de usuario com o id de sessão encontrado no banco de dados");

				if (usuario.getSenhaFinanceira() == null || usuario.getSenhaFinanceira().isEmpty())
					throw new Exception("Senha financeira não confere");

				if (!usuario.getSenhaFinanceira().equals(FormatUtilities.sha1Base64(senhaFinanceira)))
					throw new Exception("Senha financeira não confere");
			}

			String retorno = voucherProdutoDao.compraVoucher(adUserId, id);
			MessageUtil.sucesso(redirectAttributes, "Parabéns!", retorno);

			return "redirect:voucherprodutos";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:precompravoucher?id=" + id;
		}
	}

}