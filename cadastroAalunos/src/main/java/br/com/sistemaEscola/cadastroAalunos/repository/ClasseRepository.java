package br.com.sistemaEscola.cadastroAalunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemaEscola.cadastroAalunos.model.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {

}
