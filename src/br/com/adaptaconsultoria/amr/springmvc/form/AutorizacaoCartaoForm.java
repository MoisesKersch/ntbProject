package br.com.adaptaconsultoria.amr.springmvc.form;

/**
 * 
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class AutorizacaoCartaoForm {

  private String id = "";
  private String total = "0.01";
  private String transacao = "04";
  private String parcelas = "00";
  private String filiacao = "59298383";
  private String distribuidor = "";
  private String bandeira = "MASTERCARD";
  private String numPedido = "123456";
  private String codVer = "XXXXXXXXXXXXXXXX-XXX";
  private String urlBack = "http://adi.adaptaconsultoria.com.br:8080/ntb/ws/br.com.adaptaconsultoria.amr.autorizaCartaoRedeService";
  private String urlCima = "https://www.loja.com.br/imagem_cima.jpg";
  private String target = "_self";
  private String numeroCartao;
  private String pax1 = "";

  private String json;

  public AutorizacaoCartaoForm() {
    super();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

  public String getTransacao() {
    return transacao;
  }

  public void setTransacao(String transacao) {
    this.transacao = transacao;
  }

  public String getParcelas() {
    return parcelas;
  }

  public void setParcelas(String parcelas) {
    this.parcelas = parcelas;
  }

  public String getFiliacao() {
    return filiacao;
  }

  public void setFiliacao(String filiacao) {
    this.filiacao = filiacao;
  }

  public String getDistribuidor() {
    return distribuidor;
  }

  public void setDistribuidor(String distribuidor) {
    this.distribuidor = distribuidor;
  }

  public String getBandeira() {
    return bandeira;
  }

  public void setBandeira(String bandeira) {
    this.bandeira = bandeira;
  }

  public String getNumPedido() {
    return numPedido;
  }

  public void setNumPedido(String numPedido) {
    this.numPedido = numPedido;
  }

  public String getCodVer() {
    return codVer;
  }

  public void setCodVer(String codVer) {
    this.codVer = codVer;
  }

  public String getUrlBack() {
    return urlBack;
  }

  public void setUrlBack(String urlBack) {
    this.urlBack = urlBack;
  }

  public String getUrlCima() {
    return urlCima;
  }

  public void setUrlCima(String urlCima) {
    this.urlCima = urlCima;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public String getNumeroCartao() {
    return numeroCartao;
  }

  public void setNumeroCartao(String numeroCartao) {
    this.numeroCartao = numeroCartao;
  }

  public String getPax1() {
    return pax1;
  }

  public void setPax1(String pax1) {
    this.pax1 = pax1;
  }

  public String getJson() {
    return json;
  }

  public void setJson(String json) {
    this.json = json;
  }

}