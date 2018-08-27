package br.com.adaptaconsultoria.amr.springmvc.form;

public class Payment {

  private String boletoDiscount;
  private String debitDiscount;
  private RecurrentPayment recurrentPayment;

  public String getBoletoDiscount() {
    return boletoDiscount;
  }

  public void setBoletoDiscount(String boletoDiscount) {
    this.boletoDiscount = boletoDiscount;
  }

  public String getDebitDiscount() {
    return debitDiscount;
  }

  public void setDebitDiscount(String debitDiscount) {
    this.debitDiscount = debitDiscount;
  }

  public RecurrentPayment getRecurrentPayment() {
    return recurrentPayment;
  }

  public void setRecurrentPayment(RecurrentPayment recurrentPayment) {
    this.recurrentPayment = recurrentPayment;
  }

}
