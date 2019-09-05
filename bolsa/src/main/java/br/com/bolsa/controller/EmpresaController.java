package br.com.bolsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.bolsa.model.Empresa;
import br.com.bolsa.service.impl.EmpresaServiceImpl;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaServiceImpl empresaServiceImpl;
		
	@GetMapping("/list")
	public String listaEmpresas(Model model) {
		List<Empresa> empresas = empresaServiceImpl.list();
		model.addAttribute("empresas", empresas);
		
		return "empresas";
	}
	
	@GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
		Empresa emp = new Empresa();
        theModel.addAttribute("emp", emp);
        return "empresa-form";
    }
	
	@PostMapping("/saveEmpresa")
    public String saveEmpresa(@ModelAttribute("emp") Empresa emp) {
		if (emp.getId()==null)
			empresaServiceImpl.save(emp);
		else
			empresaServiceImpl.update(emp.getId(), emp);
        return "redirect:/empresa/list";
    }
	
	@GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("empresaId") int theId,
        Model theModel) {
		Empresa emp = empresaServiceImpl.listById(theId);
        theModel.addAttribute("emp", emp);
        return "empresa-form";
    }

    @GetMapping("/delete")
    public String deleteEmpresa(@RequestParam("empresaId") int theId) {
    	empresaServiceImpl.deleteById(theId);
        return "redirect:/empresa/list";
    }

}
