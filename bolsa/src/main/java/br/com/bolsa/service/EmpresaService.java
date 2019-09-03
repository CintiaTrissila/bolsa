package br.com.bolsa.service;

import java.util.List;

import br.com.bolsa.model.Empresa;

public interface EmpresaService {
	
	Empresa save(Empresa empresa);

	Empresa update(Integer id, Empresa empresa);

	void deleteById(Integer id);
	
	Empresa listById(Integer id);

	List<Empresa> list();

	Empresa listByNome(String nome);

}
