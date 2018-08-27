package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "amr_precompra")
public class PreCompra implements Serializable {

	@Id
	@Column(name = "amr_precompra_id")
	private String id;

	@Column(name = "ad_client_id")
	private String adClientId;

	@Column(name = "ad_org_id")
	private String adOrgId;

	@Column(name = "updatedby")
	private String updatedby = "100";

	@Column(name = "createdby")
	private String createdby = "100";

	@NotNull
	@Column(name = "numero")
	private String numero;

	@NotNull
	@Column(name = "ad_user_id")
	private String adUserId;

	@Column(name = "amr_formapagamento_id")
	private String amrFormaPagamentoId;

	@Column(name = "amr_metodoentrega_id")
	private String amrMetodoEntregaId;

	@Column(name = "autorizada")
	private String autorizada;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_order_id")
	private Pedido pedido;

	@Column(name = "deliveryviarule")
	private String deliveryviarule = null;

	@Column(name = "m_shipper_id")
	private String mShipperId = null;

	@Column(name = "ac_serviceentrega_id")
	private String acServicoentregaId = null;

	@Column(name = "valorentrega")
	private BigDecimal valorEntrega = null;

	@Column(name = "ac_paymentmethod_id")
	private String acPaymentMethodId;

	@Column(name = "c_paymentterm_id")
	private String cPaymentTermId;

	@Column(name = "retornocodigo")
	private String retornoCodigo = null;

	@Column(name = "retornomensagem")
	private String retornoMensagem = null;

	@Column(name = "total")
	private BigDecimal total = null;

	@Column(name = "totalitens")
	private BigDecimal totalItens = BigDecimal.ZERO;

	@Column(name = "acrescimo")
	private BigDecimal acrescimo = BigDecimal.ZERO;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "preCompra")
	private List<PreCompraItem> itens = new ArrayList<PreCompraItem>();

	@Column(name = "peso")
	private BigDecimal peso;

	@Column(name = "altura")
	private BigDecimal altura = BigDecimal.ZERO;

	@Column(name = "largura")
	private BigDecimal largura = BigDecimal.ZERO;

	@Column(name = "comprimento")
	private BigDecimal comprimento = BigDecimal.ZERO;

	@Column(name = "diametro")
	private BigDecimal diametro = BigDecimal.ZERO;

	@Transient
	private BigDecimal totalLinhas = BigDecimal.ZERO;

	@Column(name = "codret")
	private String codret;

	@Column(name = "msgret")
	private String msgret;

	@Column(name = "numautor")
	private String numautor;

	@Column(name = "numsqn")
	private String numsqn;

	@Column(name = "numcv")
	private String numcv;

	@Column(name = "numautent")
	private String numautent;

	@Column(name = "numpedido")
	private String numpedido;

	@Column(name = "data")
	private String data;

	@Column(name = "pax1")
	private String pax1;

	@Column(name = "nr_cartao")
	private String nrCartao;

	@Column(name = "origem_bin")
	private String origemBin;

	@Column(name = "numprg")
	private String numprg;

	@Column(name = "nr_hash_cartao")
	private String nrHashCartao;

	@Column(name = "cod_banco")
	private String codBanco;

	@Column(name = "processabackground")
	private String processaBackground = "N";

	@Column(name = "transacao")
	private String transacao;

	@Column(name = "parcelas")
	private String parcelas;

	@Column(name = "description")
	private String descricao;

	@Column(name = "upgrade")
	private String upgrade = "N";

	@Column(name = "bandeira")
	private String bandeira;

	@Column(name = "vendadireta")
	private String vendaDireta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "c_bpartner_id")
	private ParceiroNegocios cliente;

	@Column(name = "c_bpartner_location_id")
	private String cBPartnerLocationId;

	@Column(name = "c_location_id")
	private String cLocationId;

	@Column(name = "gateway_transacao")
	private String gatewayTransacao;

	@Column(name = "gateway_notificacao")
	private String gatewayNotificacao;
	
	@Column(name = "voucher")
	private String voucher;
	
	@Column(name = "descontoespecial")
	private String descontoEspecial = "N";
	

	public PreCompra() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdClientId() {
		return adClientId;
	}

	public void setAdClientId(String adClientId) {
		this.adClientId = adClientId;
	}

	public String getAdOrgId() {
		return adOrgId;
	}

	public void setAdOrgId(String adOrgId) {
		this.adOrgId = adOrgId;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
	}

	public String getAmrFormaPagamentoId() {
		return amrFormaPagamentoId;
	}

	public void setAmrFormaPagamentoId(String amrFormaPagamentoId) {
		this.amrFormaPagamentoId = amrFormaPagamentoId;
	}

	public String getAmrMetodoEntregaId() {
		return amrMetodoEntregaId;
	}

	public void setAmrMetodoEntregaId(String amrMetodoEntregaId) {
		this.amrMetodoEntregaId = amrMetodoEntregaId;
	}

	public String getAutorizada() {
		return autorizada;
	}

	public void setAutorizada(String autorizada) {
		this.autorizada = autorizada;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getDeliveryviarule() {
		return deliveryviarule;
	}

	public void setDeliveryviarule(String deliveryviarule) {
		this.deliveryviarule = deliveryviarule;
	}

	public String getmShipperId() {
		return mShipperId;
	}

	public void setmShipperId(String mShipperId) {
		this.mShipperId = mShipperId;
	}

	public String getAcServicoentregaId() {
		return acServicoentregaId;
	}

	public void setAcServicoentregaId(String acServicoentregaId) {
		this.acServicoentregaId = acServicoentregaId;
	}

	public BigDecimal getValorEntrega() {
		return valorEntrega;
	}

	public void setValorEntrega(BigDecimal valorEntrega) {
		this.valorEntrega = valorEntrega;
	}

	public String getRetornoCodigo() {
		return retornoCodigo;
	}

	public void setRetornoCodigo(String retornoCodigo) {
		this.retornoCodigo = retornoCodigo;
	}

	public String getRetornoMensagem() {
		return retornoMensagem;
	}

	public void setRetornoMensagem(String retornoMensagem) {
		this.retornoMensagem = retornoMensagem;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<PreCompraItem> getItens() {
		return itens;
	}

	public void setItens(List<PreCompraItem> itens) {
		this.itens = itens;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getLargura() {
		return largura;
	}

	public void setLargura(BigDecimal largura) {
		this.largura = largura;
	}

	public BigDecimal getComprimento() {
		return comprimento;
	}

	public void setComprimento(BigDecimal comprimento) {
		this.comprimento = comprimento;
	}

	public BigDecimal getDiametro() {
		return diametro;
	}

	public void setDiametro(BigDecimal diametro) {
		this.diametro = diametro;
	}

	public String getAcPaymentMethodId() {
		return acPaymentMethodId;
	}

	public void setAcPaymentMethodId(String acPaymentMethodId) {
		this.acPaymentMethodId = acPaymentMethodId;
	}

	public String getcPaymentTermId() {
		return cPaymentTermId;
	}

	public void setcPaymentTermId(String cPaymentTermId) {
		this.cPaymentTermId = cPaymentTermId;
	}

	public BigDecimal getTotalLinhas() {
		this.totalLinhas = this.total;
		if (this.valorEntrega != null)
			this.totalLinhas = this.total.subtract(this.valorEntrega);
		return this.totalLinhas;
	}

	public void setTotalLinhas(BigDecimal totalLinhas) {
		this.totalLinhas = totalLinhas;
	}

	public String getCodret() {
		return codret;
	}

	public void setCodret(String codret) {
		this.codret = codret;
	}

	public String getMsgret() {
		return msgret;
	}

	public void setMsgret(String msgret) {
		this.msgret = msgret;
	}

	public String getNumautor() {
		return numautor;
	}

	public void setNumautor(String numautor) {
		this.numautor = numautor;
	}

	public String getNumsqn() {
		return numsqn;
	}

	public void setNumsqn(String numsqn) {
		this.numsqn = numsqn;
	}

	public String getNumcv() {
		return numcv;
	}

	public void setNumcv(String numcv) {
		this.numcv = numcv;
	}

	public String getNumautent() {
		return numautent;
	}

	public void setNumautent(String numautent) {
		this.numautent = numautent;
	}

	public String getNumpedido() {
		return numpedido;
	}

	public void setNumpedido(String numpedido) {
		this.numpedido = numpedido;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPax1() {
		return pax1;
	}

	public void setPax1(String pax1) {
		this.pax1 = pax1;
	}

	public String getNrCartao() {
		return nrCartao;
	}

	public void setNrCartao(String nrCartao) {
		this.nrCartao = nrCartao;
	}

	public String getOrigemBin() {
		return origemBin;
	}

	public void setOrigemBin(String origemBin) {
		this.origemBin = origemBin;
	}

	public String getNumprg() {
		return numprg;
	}

	public void setNumprg(String numprg) {
		this.numprg = numprg;
	}

	public String getNrHashCartao() {
		return nrHashCartao;
	}

	public void setNrHashCartao(String nrHashCartao) {
		this.nrHashCartao = nrHashCartao;
	}

	public String getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}

	public String getProcessaBackground() {
		return processaBackground;
	}

	public void setProcessaBackground(String processaBackground) {
		this.processaBackground = processaBackground;
	}

	public BigDecimal getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(BigDecimal acrescimo) {
		this.acrescimo = acrescimo;
	}

	public BigDecimal getTotalItens() {
		return totalItens;
	}

	public void setTotalItens(BigDecimal totalItens) {
		this.totalItens = totalItens;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(String upgrade) {
		this.upgrade = upgrade;
	}

	public void calculaTotal() {
		this.total = totalItens.add(acrescimo).add(valorEntrega);
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getVendaDireta() {
		return vendaDireta;
	}

	public void setVendaDireta(String vendaDireta) {
		this.vendaDireta = vendaDireta;
	}

	public ParceiroNegocios getCliente() {
		return cliente;
	}

	public void setCliente(ParceiroNegocios cliente) {
		this.cliente = cliente;
	}

	public String getcBPartnerLocationId() {
		return cBPartnerLocationId;
	}

	public void setcBPartnerLocationId(String cBPartnerLocationId) {
		this.cBPartnerLocationId = cBPartnerLocationId;
	}

	public String getcLocationId() {
		return cLocationId;
	}

	public void setcLocationId(String cLocationId) {
		this.cLocationId = cLocationId;
	}

	public String getGatewayTransacao() {
		return gatewayTransacao;
	}

	public void setGatewayTransacao(String gatewayTransacao) {
		this.gatewayTransacao = gatewayTransacao;
	}

	public String getGatewayNotificacao() {
		return gatewayNotificacao;
	}

	public void setGatewayNotificacao(String gatewayNotificacao) {
		this.gatewayNotificacao = gatewayNotificacao;
	}

	public String getDescontoEspecial()
	{
		return descontoEspecial;
	}

	public void setDescontoEspecial(String descontoEspecial)
	{
		this.descontoEspecial = descontoEspecial;
	}
}