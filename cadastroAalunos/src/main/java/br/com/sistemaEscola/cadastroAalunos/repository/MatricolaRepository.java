package br.com.sistemaEscola.cadastroAalunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemaEscola.cadastroAalunos.model.Matricola;

public interface MatricolaRepository extends JpaRepository<Matricola, Long>{

}
