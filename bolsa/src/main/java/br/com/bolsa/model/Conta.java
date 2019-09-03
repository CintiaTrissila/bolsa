package br.com.bolsa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String email;
	private Double saldo;
	private Double numeroAcoes = 0.0;
	
	@OneToMany(mappedBy = "conta")
	private List<Monitoramento> monitoramentos;
	
	@OneToMany(mappedBy = "conta")
	private List<Transacao> transacoes;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public List<Monitoramento> getMonitoramentos() {
		return monitoramentos;
	}

	public void setMonitoramentos(List<Monitoramento> monitoramentos) {
		this.monitoramentos = monitoramentos;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public Double getNumeroAcoes() {
		return numeroAcoes;
	}

	public void setNumeroAcoes(Double numeroAcoes) {
		this.numeroAcoes = numeroAcoes;
	}
	
}
