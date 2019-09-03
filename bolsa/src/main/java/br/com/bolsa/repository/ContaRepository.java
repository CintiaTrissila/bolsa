package br.com.bolsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bolsa.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

}
