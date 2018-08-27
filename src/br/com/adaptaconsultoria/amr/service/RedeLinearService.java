//package br.com.adaptaconsultoria.amr.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import br.com.adaptaconsultoria.amr.model.RedeLinear;
//import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
//import br.com.adaptaconsultoria.amr.persistence.dao.RedeLinearDao;
//import br.com.adaptaconsultoria.amr.util.DateUtil;
//
///**
// *
// *
// * @author Will Zaniol
// * @author www.adaptaconsultoria.com.br
// * @version 1.0.0
// *
// */
//@Service
//public class RedeLinearService {
//
//	private RedeLinearDao redeLinearDao = DaoFactory.getInstance().getRedeLinearDao();
//
////	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
//
//	public List<String> montaRedeLinearHTML5(String adUserId) throws Exception {
//
//		List<RedeLinear> rede = redeLinearDao.carregaPorUsuario(adUserId);
//		
//		List<String> itens = new ArrayList<String>();
//
//		for (RedeLinear redeLinear : rede) {
//			if (!redeLinear.getOrdem().equals(new Integer(0))) {
//				String tooltip = "Celular: " + redeLinear.getCelular() + "\nE-mail: " + redeLinear.getEmail();
//				itens.add("<tr>");
//				itens.add("<td title=\""+tooltip+"\">" + (redeLinear.getDiretoId()      == null ? "" : redeLinear.getDiretoId())   + "</td>");
//				itens.add("<td title=\""+tooltip+"\">" + (redeLinear.getDiretoNome()    == null ? "" : redeLinear.getDiretoNome()) + "</td>");
//				itens.add("<td title=\""+tooltip+"\">" + (redeLinear.getPerfil()        == null ? "" : redeLinear.getPerfil())     + "</td>");
//				itens.add("<td title=\""+tooltip+"\">" + (redeLinear.getCidade()        == null ? "" : redeLinear.getCidade())     + "</td>");
//				itens.add("<td title=\""+tooltip+"\">" + (redeLinear.getGenero()        == null ? "" : redeLinear.getGenero())     + "</td>");
//				itens.add("<td title=\""+tooltip+"\">" + (redeLinear.getPosicionadoEm() == null ? "" : DateUtil.dateToString(redeLinear.getPosicionadoEm(), "dd/MM/yyyy")) + "</td>");
//				itens.add("</tr>");
//			}
//		}
//
//		return itens;
//	}
//
////	private List<String> adicionaItem(RedeLinear rede) throws Exception 
////	{
////		List<String> itens = new ArrayList<String>();
////	
////		if (rede.getDireto() == null)
////			return itens;
////		
////		itens.add("<li>");
////		itens.add(item(rede.getId(), rede.getDiretoUsername(), rede.getDiretoNome(), rede.getTooltip(), rede.getIcone()));
////		List<RedeLinear> nodes = redeLinearDao.carregaPorUsuario(rede.getId());
////		if (nodes != null && !nodes.isEmpty()) {
////			itens.add("<ul>");
////			for (int i = 0; i < nodes.size(); i++) {
////				itens.addAll(adicionaItem(nivel, nodes.get(i)));
////			}
////			itens.add("</ul>");
////		}
////
////		itens.add("</li>");
////		return itens;
////	}
////
////	private String item(String login, String nome, String tooltip, String icone) throws Exception {
////		if (icone == null || icone.isEmpty())
////			icone = "<c:url value=\"/resources/img/icone-pessoa-padrao.png\" />";
////
////		String label = "<span style=\"cursor: pointer;\" title=\"" + tooltip + "\">";
////		label += "<i class=\"icon-folder-open\"></i>";
////		label += "<img src=\"" + icone + "\" class=\"img-thumbnail-icon-linear\">";
////		label += nome + "</span>";
////
////		return label;
////	}
////
////	private String item(String adUserId, String login, String nome, String tooltip, String icone) throws Exception {
////		if (icone == null || icone.isEmpty())
////			icone = "<c:url value=\"/resources/img/icone-pessoa-padrao.png\" />";
////
////		String label = "<span style=\"cursor: pointer;\" onclick=\"recarrega('" + adUserId + "');\" title=\"" + tooltip + "\">";
////		label += "<i class=\"icon-folder-open\"></i>";
////		label += "<img src=\"" + icone + "\" class=\"img-thumbnail-icon-linear\">";
////		label += nome + "</span>";
////		return label;
////	}
//
//	public static void main(String[] args) throws Exception {
//		RedeLinearService service = new RedeLinearService();
//		System.out.println(service.montaRedeLinearHTML5("0B7E7F95DE324560A0C978604B8658FB"));
//	}
//
//}