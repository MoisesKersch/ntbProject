package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.PreCompra;
import br.com.adaptaconsultoria.amr.model.PreCompraCliente;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraDao;
import br.com.adaptaconsultoria.amr.service.PessoaService;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;
import br.com.adaptaconsultoria.amr.util.documento.DocumentoPessoalUtil;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

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
public class PessoaController implements Serializable {

	private PessoaService pessoaService = new PessoaService();
	private PreCompraDao preCompraDao = DaoFactory.getInstance().getPreCompraDao();

	@RequestMapping(value = "/checkoutcompraprodutocliente", method = GET)
	public ModelAndView clientePreCompra(String id, String taxid, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {

		ModelAndView model = new ModelAndView("checkoutcompraprodutocliente");
		try {

			PreCompra preCompra = preCompraDao.carregaPorId(id);
			// model.addObject("pedido", preCompra);

			PreCompraCliente cadastro = new PreCompraCliente();
			try {

				cadastro = pessoaService.carregaParceiroDeNegociosPorDocumentoFiscal(taxid, preCompra.getCliente(), preCompra.getVendaDireta()
						.equalsIgnoreCase("Y"));

				if (cadastro.getcBPartnerId() == null || cadastro.getcBPartnerId().isEmpty()) {
					if (cadastro.getTipoPessoa().equalsIgnoreCase("F")) {
						if (!DocumentoPessoalUtil.validaCPF(cadastro.getCpfCnpj())) {
							model.addObject("messageType", "0");
							model.addObject("messageTitle", "ERRO");
							model.addObject("messageDetail", "CPF Inválido!");
							cadastro.setCpfCnpj(null);
						}
					} else {
						if (!DocumentoPessoalUtil.validaCNPJ(cadastro.getCpfCnpj())) {
							model.addObject("messageType", "0");
							model.addObject("messageTitle", "ERRO");
							model.addObject("messageDetail", "CNPJ Inválido!");
							cadastro.setCpfCnpj(null);
						}
					}
				}

			} catch (Exception e) {
			}

			boolean vendaDireta = preCompra.getVendaDireta().equalsIgnoreCase("Y");
			cadastro.setVendaDireta(vendaDireta);
			cadastro.setPreCompraId(id);
			model.addObject("cadastro", cadastro);
			model.addObject("venda", vendaDireta);

			return model;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			model.addObject("messageType", "0");
			model.addObject("messageTitle", "ERRO");
			model.addObject("messageDetail", e.getMessage());
			return model;
		}
	}

	@RequestMapping(value = "/checkoutcompraprodutocliente", method = POST)
	public String novaAdesaoPatrocinada(@Valid @ModelAttribute("cadastro") final PreCompraCliente cadastro, BindingResult result,
			RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {

		try {

			if (cadastro.getcBPartnerId() != null || !cadastro.getcBPartnerId().isEmpty()) {
				cadastro.setDddTelefone("49");
				cadastro.setNumeroTelefone("9999-9999");
			}

			if (result.hasErrors()) {
				return "checkoutcompraprodutocliente";
			}

			String adUserId = SessionUtil.getUsuarioId(session);
			pessoaService.salvaNovoCliente(adUserId, cadastro);
			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Cliente salvo com sucesso");

			return "redirect:checkoutcompra?precompraid=" + cadastro.getPreCompraId();
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.erro(redirectAttributes, "ERRO", ErrorUtil.getLastMessage(e));
			return "checkoutcompraprodutocliente";
		}
	}

}