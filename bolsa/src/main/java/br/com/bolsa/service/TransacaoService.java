package br.com.bolsa.service;

import java.util.List;

import br.com.bolsa.model.Monitoramento;
import br.com.bolsa.model.Transacao;

public interface TransacaoService {
	
	Transacao save(Integer id, Transacao transacao);

	Transacao compra(Monitoramento monitoramento);

	Transacao venda(Monitoramento monitoramento);
	
	void compraVendaAcoes(Integer id);
	
	void historicoTransacoes(Integer id);
	
	void deleteById(Integer id);
	
	List<Transacao> listTransacoes(Integer id);
	
}
