package br.com.adaptaconsultoria.amr.springmvc.propertyeditor;

import java.beans.PropertyEditorSupport;


import org.springframework.stereotype.Component;


import br.com.adaptaconsultoria.amr.persistence.dao.AtivoPeriodoDao;

/**
 * 
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Component
public class AgendaPeriodoPropertyEditor extends PropertyEditorSupport {

	
	private AtivoPeriodoDao ativoPeriodoDao;

	@Override
	
	public void setAsText(String id) {
		try {
			this.setValue(ativoPeriodoDao.carregaPorId(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}