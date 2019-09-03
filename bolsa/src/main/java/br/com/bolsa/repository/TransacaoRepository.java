package br.com.bolsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bolsa.model.Conta;
import br.com.bolsa.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer>{
	List<Transacao> findByConta(Conta conta);
}
