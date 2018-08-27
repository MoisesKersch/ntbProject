package br.com.adaptaconsultoria.amr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.model.RedeBinaria;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.RedeBinariaDao;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class RedeBinariaService {

	private RedeBinariaDao redeBinariaDao = DaoFactory.getInstance().getRedeBinariaDao();

	public List<RedeBinaria> montaRedeBinariaHTML5(String adUserId, String adUserLogado) throws Exception {

		RedeBinaria box1 = redeBinariaDao.carregaPorUsuario(adUserId);
		RedeBinaria box2 = null;
		RedeBinaria box3 = null;
		RedeBinaria box4 = null;
		RedeBinaria box5 = null;
		RedeBinaria box6 = null;
		RedeBinaria box7 = null;
		RedeBinaria box8 = null;
		RedeBinaria box9 = null;
		RedeBinaria box10 = null;
		RedeBinaria box11 = null;
		RedeBinaria box12 = null;
		RedeBinaria box13 = null;
		RedeBinaria box14 = null;
		RedeBinaria box15 = null;
		RedeBinaria box16 = null;
		RedeBinaria box17 = null;
		RedeBinaria box18 = null;
		RedeBinaria box19 = null;
		RedeBinaria box20 = null;
		RedeBinaria box21 = null;
		RedeBinaria box22 = null;
		RedeBinaria box23 = null;
		RedeBinaria box24 = null;
		RedeBinaria box25 = null;
		RedeBinaria box26 = null;
		RedeBinaria box27 = null;
		RedeBinaria box28 = null;
		RedeBinaria box29 = null;
		RedeBinaria box30 = null;
		RedeBinaria box31 = null;

		if (box1 != null) {
			box2 = redeBinariaDao.carregaPorId(box1.getEsquerda());
			box3 = redeBinariaDao.carregaPorId(box1.getDireita());

			if (box2 != null) {
				box4 = redeBinariaDao.carregaPorId(box2.getEsquerda());
				box5 = redeBinariaDao.carregaPorId(box2.getDireita());

				if (box4 != null) {
					box8 = redeBinariaDao.carregaPorId(box4.getEsquerda());
					box9 = redeBinariaDao.carregaPorId(box4.getDireita());

					if (box8 != null) {
						box16 = redeBinariaDao.carregaPorId(box8.getEsquerda());
						box17 = redeBinariaDao.carregaPorId(box8.getDireita());
					}

					if (box9 != null) {
						box18 = redeBinariaDao.carregaPorId(box9.getEsquerda());
						box19 = redeBinariaDao.carregaPorId(box9.getDireita());
					}
				}

				if (box5 != null) {
					box10 = redeBinariaDao.carregaPorId(box5.getEsquerda());
					box11 = redeBinariaDao.carregaPorId(box5.getDireita());

					if (box10 != null) {
						box20 = redeBinariaDao.carregaPorId(box10.getEsquerda());
						box21 = redeBinariaDao.carregaPorId(box10.getDireita());
					}

					if (box11 != null) {
						box22 = redeBinariaDao.carregaPorId(box11.getEsquerda());
						box23 = redeBinariaDao.carregaPorId(box11.getDireita());
					}
				}
			}

			if (box3 != null) {
				box6 = redeBinariaDao.carregaPorId(box3.getEsquerda());
				box7 = redeBinariaDao.carregaPorId(box3.getDireita());

				if (box6 != null) {
					box12 = redeBinariaDao.carregaPorId(box6.getEsquerda());
					box13 = redeBinariaDao.carregaPorId(box6.getDireita());

					if (box12 != null) {
						box24 = redeBinariaDao.carregaPorId(box12.getEsquerda());
						box25 = redeBinariaDao.carregaPorId(box12.getDireita());
					}

					if (box13 != null) {
						box26 = redeBinariaDao.carregaPorId(box13.getEsquerda());
						box27 = redeBinariaDao.carregaPorId(box13.getDireita());
					}
				}

				if (box7 != null) {
					box14 = redeBinariaDao.carregaPorId(box7.getEsquerda());
					box15 = redeBinariaDao.carregaPorId(box7.getDireita());

					if (box14 != null) {
						box28 = redeBinariaDao.carregaPorId(box14.getEsquerda());
						box29 = redeBinariaDao.carregaPorId(box14.getDireita());
					}

					if (box15 != null) {
						box30 = redeBinariaDao.carregaPorId(box15.getEsquerda());
						box31 = redeBinariaDao.carregaPorId(box15.getDireita());
					}
				}
			}

			if (!box1.getAdUserId().equals(adUserLogado)) {
				box1.setAdUserId(redeBinariaDao.carregaNoRaiz(box1.getAdUserId()));
			}
		}

		box1 = acabamentoFinal(box1);
		box2 = acabamentoFinal(box2);
		box3 = acabamentoFinal(box3);
		box4 = acabamentoFinal(box4);
		box5 = acabamentoFinal(box5);
		box6 = acabamentoFinal(box6);
		box7 = acabamentoFinal(box7);
		box8 = acabamentoFinal(box8);
		box9 = acabamentoFinal(box9);
		box10 = acabamentoFinal(box10);
		box11 = acabamentoFinal(box11);
		box12 = acabamentoFinal(box12);
		box13 = acabamentoFinal(box13);
		box14 = acabamentoFinal(box14);
		box15 = acabamentoFinal(box15);
		box16 = acabamentoFinal(box16);
		box17 = acabamentoFinal(box17);
		box18 = acabamentoFinal(box18);
		box19 = acabamentoFinal(box19);
		box20 = acabamentoFinal(box20);
		box21 = acabamentoFinal(box21);
		box22 = acabamentoFinal(box22);
		box23 = acabamentoFinal(box23);
		box24 = acabamentoFinal(box24);
		box25 = acabamentoFinal(box25);
		box26 = acabamentoFinal(box26);
		box27 = acabamentoFinal(box27);
		box28 = acabamentoFinal(box28);
		box29 = acabamentoFinal(box29);
		box30 = acabamentoFinal(box30);
		box31 = acabamentoFinal(box31);

		box16.setUltimoNivel(true);
		box17.setUltimoNivel(true);
		box18.setUltimoNivel(true);
		box19.setUltimoNivel(true);
		box20.setUltimoNivel(true);
		box21.setUltimoNivel(true);
		box22.setUltimoNivel(true);
		box23.setUltimoNivel(true);
		box24.setUltimoNivel(true);
		box25.setUltimoNivel(true);
		box26.setUltimoNivel(true);
		box27.setUltimoNivel(true);
		box28.setUltimoNivel(true);
		box29.setUltimoNivel(true);
		box30.setUltimoNivel(true);
		box31.setUltimoNivel(true);

		List<RedeBinaria> rede = new ArrayList<RedeBinaria>();
		rede.add(box16);
		rede.add(box8);
		rede.add(box17);
		rede.add(box4);
		rede.add(box18);
		rede.add(box9);
		rede.add(box19);
		rede.add(box2);
		rede.add(box20);
		rede.add(box10);
		rede.add(box21);
		rede.add(box5);
		rede.add(box22);
		rede.add(box11);
		rede.add(box23);
		rede.add(box1);
		rede.add(box24);
		rede.add(box12);
		rede.add(box25);
		rede.add(box6);
		rede.add(box26);
		rede.add(box13);
		rede.add(box27);
		rede.add(box3);
		rede.add(box28);
		rede.add(box14);
		rede.add(box29);
		rede.add(box7);
		rede.add(box30);
		rede.add(box15);
		rede.add(box31);

		return rede;
	}

	private RedeBinaria acabamentoFinal(RedeBinaria box) throws Exception {
		if (box == null) {
			box = new RedeBinaria();
			box.setName("");
			box.setAdUserId("");
			box.setUsername("");
			box.setTooltip("Nenhum parceiro");
		}
		if (box.getAdUserId() == null)
			box.setAdUserId("");
		box.setAdUserId("'" + box.getAdUserId().replaceAll("'", "") + "'");
		return box;
	}

	public List<RedeBinaria> montaRedeBinariaHTML5ANTIGO(String adUserId, String adUserLogado) throws Exception {

		String id = redeBinariaDao.carregaIDPorUsuario(adUserId);
		List<RedeBinaria> rede = redeBinariaDao.listaPorID(id);

		RedeBinaria box1 = null;
		Map<String, RedeBinaria> map = new HashMap<String, RedeBinaria>();
		for (int i = 0; i < rede.size(); i++) {
			if (rede.get(i).getAdUserId().replaceAll("'", "").equalsIgnoreCase(adUserId))
				box1 = rede.get(i);
			else
				map.put(rede.get(i).getId(), rede.get(i));
		}

		RedeBinaria box2 = null;
		RedeBinaria box3 = null;
		RedeBinaria box4 = null;
		RedeBinaria box5 = null;
		RedeBinaria box6 = null;
		RedeBinaria box7 = null;
		RedeBinaria box8 = null;
		RedeBinaria box9 = null;
		RedeBinaria box10 = null;
		RedeBinaria box11 = null;
		RedeBinaria box12 = null;
		RedeBinaria box13 = null;
		RedeBinaria box14 = null;
		RedeBinaria box15 = null;

		if (box1 != null) {
			box2 = map.get(box1.getEsquerda());
			box3 = map.get(box1.getDireita());

			if (box2 != null) {
				box4 = map.get(box2.getEsquerda());
				box5 = map.get(box2.getDireita());

				if (box4 != null) {
					box8 = map.get(box4.getEsquerda());
					box9 = map.get(box4.getDireita());
				}

				if (box5 != null) {
					box10 = map.get(box5.getEsquerda());
					box11 = map.get(box5.getDireita());
				}
			}

			if (box3 != null) {
				box6 = map.get(box3.getEsquerda());
				box7 = map.get(box3.getDireita());

				if (box6 != null) {
					box12 = map.get(box6.getEsquerda());
					box13 = map.get(box6.getDireita());
				}

				if (box7 != null) {
					box14 = map.get(box7.getEsquerda());
					box15 = map.get(box7.getDireita());
				}
			}

			if (!box1.getAdUserId().equals(adUserLogado)) {
				box1.setAdUserId(redeBinariaDao.carregaNoRaiz(box1.getAdUserId()));
			}
		}

		box1 = acabamentoFinal(box1);
		box2 = acabamentoFinal(box2);
		box3 = acabamentoFinal(box3);
		box4 = acabamentoFinal(box4);
		box5 = acabamentoFinal(box5);
		box6 = acabamentoFinal(box6);
		box7 = acabamentoFinal(box7);
		box8 = acabamentoFinal(box8);
		box9 = acabamentoFinal(box9);
		box10 = acabamentoFinal(box10);
		box11 = acabamentoFinal(box11);
		box12 = acabamentoFinal(box12);
		box13 = acabamentoFinal(box13);
		box14 = acabamentoFinal(box14);
		box15 = acabamentoFinal(box15);

		rede = new ArrayList<RedeBinaria>();
		rede.add(box8);
		rede.add(box4);
		rede.add(box9);
		rede.add(box2);
		rede.add(box10);
		rede.add(box5);
		rede.add(box11);
		rede.add(box1);
		rede.add(box12);
		rede.add(box6);
		rede.add(box13);
		rede.add(box3);
		rede.add(box14);
		rede.add(box7);
		rede.add(box15);
		return rede;
	}

	public void listaFilhos(String adUserId) throws Exception {
		RedeBinaria box = redeBinariaDao.carregaPorUsuario(adUserId);
		System.out.println(box.getUsername() + " - " + box.getName());
		escreveNomesFilho(box);
	}

	public void escreveNomesFilho(RedeBinaria box) throws Exception {
		if (box != null) {
			if (box.getEsquerda() != null) {
				escreveNome(box.getEsquerda());
			}
			if (box.getDireita() != null) {
				escreveNome(box.getDireita());
			}
		}
	}

	private void escreveNome(String id) throws Exception {
		RedeBinaria box = redeBinariaDao.carregaPorId(id);
		System.out.println(box.getUsername() + " - " + box.getName());
		escreveNomesFilho(box);
	}

	public static void main(String[] args) throws Exception {
		RedeBinariaService service = new RedeBinariaService();
		service.listaFilhos("0B7E7F95DE324560A0C978604B8658FB");
	}

}