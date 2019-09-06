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
import br.com.bolsa.model.Monitoramento;
import br.com.bolsa.service.impl.ContaServiceImpl;
import br.com.bolsa.service.impl.EmpresaServiceImpl;
import br.com.bolsa.service.impl.MonitoramentoServiceImpl;

@Controller
@RequestMapping("/monitoramento")
public class MonitoramentoController {
	
	@Autowired
	private MonitoramentoServiceImpl monitoraServiceImpl;
	@Autowired
	private EmpresaServiceImpl empresaServiceImpl;
	@Autowired
	private ContaServiceImpl contaServiceImpl;
	
//	public List<Empresa> empresas;
	
	@GetMapping("/list")
	public String listaMonitoramentos(Model model) {
		List<Monitoramento> monitoramentos = monitoraServiceImpl.list();
		model.addAttribute("monitoramentos", monitoramentos);
		
		return "monitoramentos";
	}
	
	@GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
		Monitoramento monit = new Monitoramento();
//		List<Empresa> empList = empresaServiceImpl.list();
        theModel.addAttribute("monit", monit);
        theModel.addAttribute("empList",empresaServiceImpl.list());
        theModel.addAttribute("contaList",contaServiceImpl.list());
        return "monitoramento-form";
    }
	
	@PostMapping("/saveMonitoramento")
    public String saveMonitoramento(@ModelAttribute("monit") Monitoramento monit) {
		monitoraServiceImpl.save(monit.getConta().getId(), monit);
        return "redirect:/monitoramento/list";
    }
	
	@GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("monitId") int theId,
        Model theModel) {
		Monitoramento monit = monitoraServiceImpl.listById(theId);
        theModel.addAttribute("monit", monit);
        theModel.addAttribute("empList",empresaServiceImpl.list());
        theModel.addAttribute("contaList",contaServiceImpl.list());
        return "monitoramento-form";
    }
	
	@GetMapping("/delete")
    public String deleteMonitoramento(@RequestParam("monitId") int theId) {
		monitoraServiceImpl.deleteById(theId);
        return "redirect:/monitoramento/list";
    }

}
