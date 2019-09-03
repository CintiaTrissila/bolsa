package br.com.bolsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import br.com.bolsa.model.Empresa;
import br.com.bolsa.model.Monitoramento;
import br.com.bolsa.model.Transacao;
import br.com.bolsa.model.enums.TipoTransacao;
import br.com.bolsa.service.EmailService;

public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender sender;
	
	@Override
	public SimpleMailMessage constructReportMail(Transacao transacao, Monitoramento monitoramento, Empresa empresa) {
		String assunto;
		String mensagem;
		if (transacao.getTipoTransacao() == TipoTransacao.VENDA) {
			assunto = "Venda de ações do(a) " + transacao.getEmpresa();
			mensagem = "Preço de venda: " + monitoramento.getPrecoVenda() + ". Valor negociado: "
					+ empresa.getValorAcao();
		} else {
			assunto = "Compra de ações do(a) " + transacao.getEmpresa();
			mensagem = "Preço de compra: " + monitoramento.getPrecoCompra() + ". Valor negociado: "
					+ empresa.getValorAcao();
		}
		return constructMail(assunto, mensagem, transacao);
	}

	@Override
	public SimpleMailMessage constructMail(String assunto, String corpo, Transacao transacao) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(assunto);
		email.setText(corpo);
		email.setTo(transacao.getConta().getEmail());
		email.setFrom("naoresponda@app.bolsa.com.br");
		return email;
	}
	
	public void sendEmail(Transacao transacao, Monitoramento monitoramento, Empresa empresa) {
		sender.send(constructReportMail(transacao, monitoramento, empresa));
	}

}
