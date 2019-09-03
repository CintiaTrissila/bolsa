package br.com.bolsa.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.bolsa.model.enums.TipoTransacao;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoTransacao tipoTransacao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Conta conta;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Empresa empresa;
	
	private Double valor;
	private Double quantidadeAcoes;
	private LocalDateTime data;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getQuantidadeAcoes() {
		return quantidadeAcoes;
	}
	public void setQuantidadeAcoes(Double quantidadeAcoes) {
		this.quantidadeAcoes = quantidadeAcoes;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}

}
