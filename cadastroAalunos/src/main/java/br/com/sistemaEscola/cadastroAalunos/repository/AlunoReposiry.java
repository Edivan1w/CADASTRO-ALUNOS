package br.com.sistemaEscola.cadastroAalunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemaEscola.cadastroAalunos.model.Aluno;

public interface AlunoReposiry extends JpaRepository<Aluno, Long> {

}
