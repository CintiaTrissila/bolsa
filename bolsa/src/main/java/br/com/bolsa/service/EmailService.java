package br.com.bolsa.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.bolsa.model.Empresa;
import br.com.bolsa.model.Monitoramento;
import br.com.bolsa.model.Transacao;

public interface EmailService {
	
	SimpleMailMessage constructReportMail(Transacao transacao, Monitoramento monitoramento, Empresa empresa);

	SimpleMailMessage constructMail(String assunto, String corpo, Transacao transacao);

}
