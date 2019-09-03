package br.com.bolsa.service;

import java.util.List;

import br.com.bolsa.model.Conta;

public interface ContaService {
	
	Conta save(Conta conta);

	Conta update(Integer id, Conta conta);

	void deleteById(Integer id);
	
	Conta depositar(Integer id, Conta conta);
	
	Conta listById(Integer id);

	List<Conta> list();
	
	Double saldoInicial(Integer id);
	
}
