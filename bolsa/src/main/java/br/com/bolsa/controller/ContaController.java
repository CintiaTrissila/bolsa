package br.com.bolsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.bolsa.model.Conta;
import br.com.bolsa.service.impl.ContaServiceImpl;
import br.com.bolsa.service.impl.EmpresaServiceImpl;

@Controller
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaServiceImpl contaServiceImpl;
		
	@GetMapping("/list")
	public String listaContas(Model model) {
		List<Conta> contas = contaServiceImpl.list();
		model.addAttribute("contas", contas);
		
		return "contas";
	}
	
	@GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
		Conta cont = new Conta();
        theModel.addAttribute("cont", cont);
        return "conta-form";
    }
	
	@PostMapping("/saveConta")
    public String saveEmpresa(@ModelAttribute("cont") Conta cont) {
		if (cont.getId()==null)
			contaServiceImpl.save(cont);
		else
			contaServiceImpl.update(cont.getId(), cont);
        return "redirect:/conta/list";
    }
	
	@GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("contaId") int theId,
        Model theModel) {
		Conta cont = contaServiceImpl.listById(theId);
        theModel.addAttribute("cont", cont);
        return "conta-form";
    }

    @GetMapping("/delete")
    public String deleteConta(@RequestParam("contaId") int theId) {
    	contaServiceImpl.deleteById(theId);
        return "redirect:/conta/list";
    }
    
    @GetMapping("/depositarForm")
    public String showFormForDeposito(@RequestParam("contaId") int theId,
        Model theModel) {
		Conta cont = contaServiceImpl.listById(theId);
        theModel.addAttribute("cont", cont);
        theModel.addAttribute("contaId",theId);
        return "deposito-form";
    }
    
	@PutMapping("/depositar")
	public String depositar(@RequestParam("contaId") int theId, @ModelAttribute("cont") Conta cont) {
		Conta contaDeposito = contaServiceImpl.depositar(theId, cont);
		return "redirect:/conta/list";
	}

}
