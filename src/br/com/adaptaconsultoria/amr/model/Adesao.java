package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class Adesao implements Serializable {

	private Cadastro parceiro;

	public Adesao() {
		super();
	}

	public Cadastro getParceiro() {
		return parceiro;
	}

	public void setParceiro(Cadastro parceiro) {
		this.parceiro = parceiro;
	}

	public String toXML() throws Exception {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xml = "<adesao adClientId=\"" + AMRProperties.getInstance().getPropriedade("adclientid") + "\" adOrgId=\""
				+ AMRProperties.getInstance().getPropriedade("adorgid") + "\">";
		xml += parceiro.toElement();
		xml += "</adesao>";
		return xml;
	}

}