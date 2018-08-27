package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class Bandeira implements Serializable {

	public static final String VISA = "visa";
	public static final String MASTERCARD = "mastercard";
	public static final String AMEX = "amex";
	public static final String DINERSCLUB = "dinersclub";
	public static final String DISCOVER = "discover";
	public static final String JCB = "jcb";
	public static final String HIPERCARD = "hipercard";
	public static final String ELO = "elo";
	public static final String REGEX_VISA = "^4[0-9]{12}(?:[0-9]{3})";
	public static final String REGEX_MASTERCARD = "^5[1-5][0-9]{14}";
	public static final String REGEX_AMEX = "^3[47][0-9]{13}";
	public static final String REGEX_DINERSCLUB = "^3(?:0[0-5]|[68][0-9])[0-9]{11}";
	public static final String REGEX_DISCOVER = "^6(?:011|5[0-9]{2})[0-9]{12}";
	public static final String REGEX_JCB = "^(?:2131|1800|35\\d{3})\\d{11}";
	public static final String REGEX_HIPERCARD = "^(606282\\d{10}(\\d{3})?)|(3841\\d{15})$";
	public static final String REGEX_ELO = "^((((636368)|(438935)|(504175)|(451416)|(636297))\\d{0,10})|((5067)|(4576)|(4011))\\d{0,12})$";
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "codigo")
	private String codigo;
	@Column(name = "nome")
	private String nome;

	public Bandeira() {
		super();
	}

	public static String carregaBandeira(String numeroCartao) throws Exception {
		if (numeroCartao == null || numeroCartao.isEmpty())
			throw new Exception("Número do cartão é necessário");

		if (essaBandeira(REGEX_ELO, numeroCartao))
			return Bandeira.ELO;
		if (essaBandeira(REGEX_VISA, numeroCartao))
			return Bandeira.VISA;
		if (essaBandeira(REGEX_MASTERCARD, numeroCartao))
			return Bandeira.MASTERCARD;
		if (essaBandeira(REGEX_AMEX, numeroCartao))
			return Bandeira.AMEX;
		if (essaBandeira(REGEX_DINERSCLUB, numeroCartao))
			return Bandeira.DINERSCLUB;
		if (essaBandeira(REGEX_DISCOVER, numeroCartao))
			return Bandeira.DISCOVER;
		if (essaBandeira(REGEX_JCB, numeroCartao))
			return Bandeira.JCB;
		if (essaBandeira(REGEX_HIPERCARD, numeroCartao))
			return Bandeira.HIPERCARD;

		throw new Exception("Nenhuma bandeira suportada encontrada para esse número de cartão");
	}

	private static boolean essaBandeira(String bandeiraRegex, String numeroCartao) throws Exception {
		Pattern p = Pattern.compile(bandeiraRegex);
		Matcher m = p.matcher(numeroCartao);
		return m.find();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}