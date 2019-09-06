package br.com.bolsa.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolsa.model.Conta;
import br.com.bolsa.model.Empresa;
import br.com.bolsa.model.Monitoramento;
import br.com.bolsa.model.Transacao;
import br.com.bolsa.model.enums.TipoTransacao;
import br.com.bolsa.repository.TransacaoRepository;
import br.com.bolsa.service.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService{

	@Autowired
	private TransacaoRepository transacaoRepository;
	@Autowired
	private ContaServiceImpl contaServiceImpl;
	@Autowired
	private MonitoramentoServiceImpl monitoramentoServiceImpl;
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@Override
	public Transacao save(Integer id, Transacao transacao) {
		//negociacao.setConta(contaServiceImpl.listaContaPeloId(id));
		return transacaoRepository.save(transacao);
	}

	@Override
	public Transacao compra(Monitoramento monitoramento) {
		Empresa empresa = monitoramento.getEmpresa();
		Conta conta = monitoramento.getConta();
		Double acoes = conta.getSaldo() / empresa.getValorAcao();

		Transacao transacao = new Transacao();
		transacao.setTipoTransacao(TipoTransacao.COMPRA);
		transacao.setData(LocalDateTime.now());
		transacao.setEmpresa(monitoramento.getEmpresa());
		transacao.setQuantidadeAcoes(acoes);
		transacao.setValor(empresa.getValorAcao());
		conta.setSaldo(0.0);
		conta.setNumeroAcoes(acoes);
		Transacao tra = save(conta.getId(), transacao);
		contaServiceImpl.save(conta);

		emailServiceImpl.sendEmail(tra, monitoramento, empresa);
		return tra;
	}

	@Override
	public Transacao venda(Monitoramento monitoramento) {
		Empresa empresa = monitoramento.getEmpresa();
		Conta conta = monitoramento.getConta();

		Transacao transacao = new Transacao();
		transacao.setValor(empresa.getValorAcao());
		transacao.setTipoTransacao(TipoTransacao.VENDA);
		transacao.setData(LocalDateTime.now());
		transacao.setEmpresa(monitoramento.getEmpresa());
		transacao.setQuantidadeAcoes(conta.getNumeroAcoes());
		conta.setSaldo(conta.getNumeroAcoes() * empresa.getValorAcao());
		conta.setNumeroAcoes(0.0);
		Transacao tra = save(conta.getId(), transacao);
		contaServiceImpl.save(conta);

		emailServiceImpl.sendEmail(tra, monitoramento, empresa);
		return tra;
	}

	@Override
	public void compraVendaAcoes(Integer id) {
		Conta conta = contaServiceImpl.listById(id);
		List<Monitoramento> monitoramentos = monitoramentoServiceImpl.monitoramentosByConta(conta);
		for (Monitoramento monitoramento : monitoramentos) {
			Empresa empresa = monitoramento.getEmpresa();
			if (monitoramento.getConta().getSaldo() > 0) {
				if (monitoramento.getPrecoCompra() >= empresa.getValorAcao()) {
					compra(monitoramento);
				}
			} else if (monitoramento.getConta().getNumeroAcoes() > 0) {
				if (empresa.getValorAcao() >= monitoramento.getPrecoVenda()) {
					venda(monitoramento);
				}
			}
		}
	}

	@Override
	public String historicoTransacoes(Integer id) {
		Conta conta = contaServiceImpl.listById(id);
		List<Transacao> transacoes = conta.getTransacoes();
		String retorno = "";
		for (Transacao transacao : transacoes) {
			retorno += "------------------------------------------------------------";
			retorno += "-------------------------------------------------";
			retorno += "-----------------------------------";
			retorno += "Identificador: " + transacao.getId();
			retorno += "Tipo : " + transacao.getTipoTransacao();
			retorno += "Empresa: " + transacao.getEmpresa();
			retorno += "Valor: " + transacao.getValor();
			retorno += "Quantidade de ações: " + transacao.getQuantidadeAcoes();
			retorno += "Data: " + transacao.getData();
			retorno += "------------------------------------------------------------";
			retorno += "-------------------------------------------------";
			retorno += "------------------------------------------";
			retorno += "-----------------------------------";
		}
		retorno += "------------------------------------------------------------";
		retorno += "---                                                      ---";
		retorno += "---                                                      ---";
		retorno += "---    Saldo Final: " + conta.getSaldo() + "             ---";
		retorno += "---    Número de ações: " + conta.getNumeroAcoes() + "   ---";
		retorno += "---                                                      ---";
		retorno += "---                                                      ---";
		retorno += "------------------------------------------------------------";
		retorno += "------------------------------------------------------------";
		retorno += "------------------------------------------------------------";
		return retorno;
	}

	@Override
	public void deleteById(Integer id) {
		transacaoRepository.deleteById(id);
	}

	@Override
	public List<Transacao> listTransacoes(Integer id) {
		return transacaoRepository.findByConta(contaServiceImpl.listById(id));
	}

}
