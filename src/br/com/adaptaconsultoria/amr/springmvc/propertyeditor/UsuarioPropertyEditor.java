package br.com.adaptaconsultoria.amr.springmvc.propertyeditor;

import java.beans.PropertyEditorSupport;


import org.springframework.stereotype.Component;

import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;

@Component
public class UsuarioPropertyEditor extends PropertyEditorSupport {

	
	private UsuarioDao usuarioDao;

	@Override
	public void setAsText(String id) {
		try {
			this.setValue(usuarioDao.carregaPorId(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}