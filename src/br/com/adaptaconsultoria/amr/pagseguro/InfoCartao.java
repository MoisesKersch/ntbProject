package br.com.adaptaconsultoria.amr.pagseguro;

import java.math.BigDecimal;

public class InfoCartao {

	private String creditCardToken;
	private Integer installmentQuantity;
	private BigDecimal installmentValue;
	private Integer noInterestInstallmentQuantity;
	private String creditCardHolderName;

	public String getCreditCardToken() {
		return creditCardToken;
	}

	public void setCreditCardToken(String creditCardToken) {
		this.creditCardToken = creditCardToken;
	}

	public Integer getInstallmentQuantity() {
		return installmentQuantity;
	}

	public void setInstallmentQuantity(Integer installmentQuantity) {
		this.installmentQuantity = installmentQuantity;
	}

	public BigDecimal getInstallmentValue() {
		return installmentValue;
	}

	public void setInstallmentValue(BigDecimal installmentValue) {
		this.installmentValue = installmentValue;
	}

	public Integer getNoInterestInstallmentQuantity() {
		return noInterestInstallmentQuantity;
	}

	public void setNoInterestInstallmentQuantity(Integer noInterestInstallmentQuantity) {
		this.noInterestInstallmentQuantity = noInterestInstallmentQuantity;
	}

	public String getCreditCardHolderName() {
		return creditCardHolderName;
	}

	public void setCreditCardHolderName(String creditCardHolderName) {
		this.creditCardHolderName = creditCardHolderName;
	}

}