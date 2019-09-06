package br.com.bolsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.bolsa.model.Conta;
import br.com.bolsa.service.impl.EmpresaServiceImpl;
import br.com.bolsa.service.impl.TransacaoServiceImpl;

@Controller
public class IndexController {
	
	@Autowired
	private TransacaoServiceImpl transacaoServiceImpl;
	@Autowired
	private EmpresaServiceImpl empresaServiceImpl;
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
 
        String message = "Hello Spring Boot + JSP";
 
        model.addAttribute("message", message);
 
        return "index";
    }
	
	@PostMapping("/start/{id}")
	public String start(@ModelAttribute("id") Integer id) {
		for (int i = 0; i < 100; i++) {
			empresaServiceImpl.SimuladorConexaoPreco();
			transacaoServiceImpl.compraVendaAcoes(id);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return transacaoServiceImpl.historicoTransacoes(id);
	}

}
