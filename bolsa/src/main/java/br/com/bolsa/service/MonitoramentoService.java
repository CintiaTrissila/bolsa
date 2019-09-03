package br.com.bolsa.service;

import java.util.List;

import br.com.bolsa.model.Monitoramento;

public interface MonitoramentoService {
	
	Monitoramento save(Integer id, Monitoramento monitoramento);

	void deleteById(Integer id);

	List<Monitoramento> list();

	Monitoramento listById(Integer id);

}
