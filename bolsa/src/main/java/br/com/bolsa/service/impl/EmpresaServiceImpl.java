package br.com.bolsa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolsa.model.Empresa;
import br.com.bolsa.repository.EmpresaRepository;
import br.com.bolsa.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private RandomNumberImpl randomNumberImpl;
	
	@Override
	public Empresa save(Empresa empresa) {
		Empresa emp = empresaRepository.findByNome(empresa.getNome());
		if (emp != null) {
			return null;
		}
		return empresaRepository.save(empresa);
	}

	@Override
	public Empresa update(Integer id, Empresa empresa) {
		Empresa newEmpresa = listById(id);
		if(empresa.getNome() != null) {
			newEmpresa.setNome(empresa.getNome());
		}
		if(empresa.getValorAcao() > 0) {
			newEmpresa.setValorAcao(empresa.getValorAcao());
		}
		return empresaRepository.save(newEmpresa);
	}

	@Override
	public void deleteById(Integer id) {
		empresaRepository.deleteById(id);
	}

	@Override
	public Empresa listById(Integer id) {
		return empresaRepository.getOne(id);
	}

	@Override
	public List<Empresa> list() {
		return empresaRepository.findAll();
	}

	@Override
	public Empresa listByNome(String nome) {
		return empresaRepository.findByNome(nome);
	}
	
	public void SimuladorConexaoPreco() {
		List<Empresa> empresas = list();
		for(Empresa empresa : empresas) {
			empresa.setValorAcao(randomNumberImpl.randomValorAcao());
			empresaRepository.save(empresa);
		}
	}

}
