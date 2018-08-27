package br.com.adaptaconsultoria.amr.loader;

import java.util.ArrayList;
import java.util.List;

import br.com.adaptaconsultoria.amr.model.Cidade;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class CidadeLoader {

	public static List<Cidade> listaCidadesPorEstado(String id) {
		if (id.equals("397"))
			return CidadeLoader397.getCidades();
		if (id.equals("398"))
			return CidadeLoader398.getCidades();
		if (id.equals("399"))
			return CidadeLoader399.getCidades();
		if (id.equals("400"))
			return CidadeLoader400.getCidades();
		if (id.equals("401"))
			return CidadeLoader401.getCidades();
		if (id.equals("402"))
			return CidadeLoader402.getCidades();
		if (id.equals("403"))
			return CidadeLoader403.getCidades();
		if (id.equals("404"))
			return CidadeLoader404.getCidades();
		if (id.equals("405"))
			return CidadeLoader405.getCidades();
		if (id.equals("406"))
			return CidadeLoader406.getCidades();
		if (id.equals("408"))
			return CidadeLoader408.getCidades();
		if (id.equals("409"))
			return CidadeLoader409.getCidades();
		if (id.equals("410"))
			return CidadeLoader410.getCidades();
		if (id.equals("411"))
			return CidadeLoader411.getCidades();
		if (id.equals("412"))
			return CidadeLoader412.getCidades();
		if (id.equals("413"))
			return CidadeLoader413.getCidades();
		if (id.equals("414"))
			return CidadeLoader414.getCidades();
		if (id.equals("415"))
			return CidadeLoader415.getCidades();
		if (id.equals("416"))
			return CidadeLoader416.getCidades();
		if (id.equals("417"))
			return CidadeLoader417.getCidades();
		if (id.equals("418"))
			return CidadeLoader418.getCidades();
		if (id.equals("419"))
			return CidadeLoader419.getCidades();
		if (id.equals("420"))
			return CidadeLoader420.getCidades();
		if (id.equals("421"))
			return CidadeLoader421.getCidades();
		if (id.equals("422"))
			return CidadeLoader422.getCidades();
		if (id.equals("423"))
			return CidadeLoader423.getCidades();
		if (id.equals("424"))
			return CidadeLoader424.getCidades();
		return new ArrayList<Cidade>();
	}

}