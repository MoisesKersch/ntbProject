package br.com.adaptaconsultoria.amr.test;

import br.com.adaptaconsultoria.amr.model.PreCompraCliente;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.util.DataUtil;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 * 
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class DaoTeste {

	public static void main(String[] args) {
		PreCompraCliente compra = new PreCompraCliente();
		compra.setId(DataUtil.getId());
		compra.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
		compra.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
		compra.setCreatedby("100");
		compra.setUpdatedby("100");
		compra.setPreCompraId("A6627CE3B6C044FD8B431858E68BE4EC");
		compra.setCpfCnpj("99999999999");
		compra.setNome("Atila Abreu de Cunha e Silva");
		compra.setDataNascimento("10/09/2015");
		compra.setGenero("M");
		compra.setTipoPessoa("F");
		compra.setEmail("wildson@adaptaconsultoria.com.br");
		compra.setCep("89812110");
		compra.setRua("Alameda Celular");
		compra.setNumero("21");
		compra.setComplemento("Complementando o endereço");
		compra.setBairro("Centro");
		compra.setPais("139");
		compra.setEstado("422");
		compra.setCidade("3B0AA478F1B14A559A8D296C81066C0C");
		compra.setReferencia("Referencia XYZ");
		compra.setDddTelefone("49");
		compra.setNumeroTelefone("99355977");
		compra.setOperadoraTelefone("TIM");

		try {
			System.out.println("Salvando");
			compra = DaoFactory.getInstance().getPreCompraClienteDao().salva(compra);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("Atualizando");
			compra = DaoFactory.getInstance().getPreCompraClienteDao().atualiza(compra);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}