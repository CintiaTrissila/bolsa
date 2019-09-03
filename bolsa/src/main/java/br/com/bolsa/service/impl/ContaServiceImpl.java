package br.com.bolsa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolsa.model.Conta;
import br.com.bolsa.model.Monitoramento;
import br.com.bolsa.model.Transacao;
import br.com.bolsa.repository.ContaRepository;
import br.com.bolsa.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService{

	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private MonitoramentoServiceImpl monitoramentoServiceImpl;
	@Autowired
	private TransacaoServiceImpl transacaoServiceImpl;
	
	@Override
	public Conta save(Conta conta) {
		return contaRepository.save(conta);
	}

	@Override
	public Conta update(Integer id, Conta conta) {
		Conta newConta = listById(id);
		if(conta.getEmail() != null) {
			newConta.setEmail(conta.getEmail());
		}
		return contaRepository.save(newConta);
	}

	@Override
	public void deleteById(Integer id) {
		Conta conta = listById(id);
		List<Monitoramento> monitoramentos = conta.getMonitoramentos();
		List<Transacao> transacoes = conta.getTransacoes();
		for (Transacao transacao : transacoes) {
			transacaoServiceImpl.deleteById(transacao.getId());
		}
		for (Monitoramento monitoramento : monitoramentos) {
			monitoramentoServiceImpl.deleteById(monitoramento.getId());
		}
		contaRepository.deleteById(id);
	}

	@Override
	public Conta depositar(Integer id, Conta conta) {
		Conta contaDp = listById(id);
		if(conta.getSaldo() > 0) {
			contaDp.setSaldo(contaDp.getSaldo() + conta.getSaldo());
			return contaRepository.save(contaDp);
		}
		return null;
	}

	@Override
	public Conta listById(Integer id) {
		return contaRepository.getOne(id);
	}

	@Override
	public List<Conta> list() {
		return contaRepository.findAll();
	}

	@Override
	public Double saldoInicial(Integer id) {
		return listById(id).getSaldo();
	}

}
