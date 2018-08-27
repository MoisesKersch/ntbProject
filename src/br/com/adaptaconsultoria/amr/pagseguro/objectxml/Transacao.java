package br.com.adaptaconsultoria.amr.pagseguro.objectxml;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "transaction")
public class Transacao {

	private Date date;
	private String code;
	private String reference;
	private String recoveryCode;
	private Integer type;
	private Integer status;
	private Date lastEventDate;
	private MetodoPagamento paymentMethod;
	private String paymentLink;
	private BigDecimal grossAmount;
	private BigDecimal discountAmount;
	private BigDecimal feeAmount;
	private BigDecimal netAmount;
	private BigDecimal extraAmount;
	private Integer installmentCount;
	private Integer itemCount;
	private List<Item> items;
	private Comprador sender;
	private Frete shipping;
	private GatewaySystem gatewaySystem;

	public Date getDate() {
		return date;
	}

	@XmlElement
	public void setDate(Date date) {
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	@XmlElement
	public void setCode(String code) {
		this.code = code;
	}

	public String getReference() {
		return reference;
	}

	@XmlElement
	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getRecoveryCode() {
		return recoveryCode;
	}

	@XmlElement
	public void setRecoveryCode(String recoveryCode) {
		this.recoveryCode = recoveryCode;
	}

	public Integer getType() {
		return type;
	}

	@XmlElement
	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	@XmlElement
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastEventDate() {
		return lastEventDate;
	}

	@XmlElement
	public void setLastEventDate(Date lastEventDate) {
		this.lastEventDate = lastEventDate;
	}

	public MetodoPagamento getPaymentMethod() {
		return paymentMethod;
	}

	@XmlElement
	public void setPaymentMethod(MetodoPagamento paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentLink() {
		return paymentLink;
	}

	@XmlElement
	public void setPaymentLink(String paymentLink) {
		this.paymentLink = paymentLink;
	}

	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	@XmlElement
	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	@XmlElement
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	@XmlElement
	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	@XmlElement
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public BigDecimal getExtraAmount() {
		return extraAmount;
	}

	@XmlElement
	public void setExtraAmount(BigDecimal extraAmount) {
		this.extraAmount = extraAmount;
	}

	public Integer getInstallmentCount() {
		return installmentCount;
	}

	@XmlElement
	public void setInstallmentCount(Integer installmentCount) {
		this.installmentCount = installmentCount;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	@XmlElement
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public List<Item> getItems() {
		return items;
	}

	@XmlElementWrapper
	@XmlElement(name = "item")
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Comprador getSender() {
		return sender;
	}

	@XmlElement
	public void setSender(Comprador sender) {
		this.sender = sender;
	}

	public Frete getShipping() {
		return shipping;
	}

	@XmlElement
	public void setShipping(Frete shipping) {
		this.shipping = shipping;
	}

	public GatewaySystem getGatewaySystem() {
		return gatewaySystem;
	}

	@XmlElement
	public void setGatewaySystem(GatewaySystem gatewaySystem) {
		this.gatewaySystem = gatewaySystem;
	}

	@Override
	public String toString() {
		return "Transacao [date=" + date + ", code=" + code + ", reference=" + reference + ", recoveryCode="
				+ recoveryCode + ", type=" + type + ", status=" + status + ", lastEventDate=" + lastEventDate
				+ ", paymentMethod=" + paymentMethod + ", paymentLink=" + paymentLink + ", grossAmount=" + grossAmount
				+ ", discountAmount=" + discountAmount + ", feeAmount=" + feeAmount + ", netAmount=" + netAmount
				+ ", extraAmount=" + extraAmount + ", installmentCount=" + installmentCount + ", itemCount=" + itemCount
				+ ", items=" + items + ", sender=" + sender + ", shipping=" + shipping + ", gatewaySystem="
				+ gatewaySystem + "]";
	}

}