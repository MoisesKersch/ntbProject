package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "amr_redelinear")
public class RedeLinear implements Serializable {

	@Id
	@Column(name = "amr_redelinear_id")
	private String id;
	
	@Column(name = "id")
	private String myId;
	
	@Column(name = "nome")
	private String nome;

	@Column(name = "login")
	private String login;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "indicador")
	private String indicador;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "posicionadoem")
	private Date posicionadoEm;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "perfil")
	private String perfil;
	
	@Column(name = "graduacao")
	private String graduacao;
	
	@Column(name = "nivel")
	private BigDecimal nivel;
	
	@Column(name = "ativopago")
	private String ativoPago;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public Date getPosicionadoEm() {
		return posicionadoEm;
	}

	public void setPosicionadoEm(Date posicionadoEm) {
		this.posicionadoEm = posicionadoEm;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getGraduacao() {
		return graduacao;
	}

	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}

	public BigDecimal getNivel() {
		return nivel;
	}

	public void setNivel(BigDecimal nivel) {
		this.nivel = nivel;
	}

	public String getAtivoPago() {
		return ativoPago;
	}

	public void setAtivoPago(String ativoPago) {
		this.ativoPago = ativoPago;
	}
}