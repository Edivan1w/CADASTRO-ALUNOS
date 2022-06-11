package br.com.sistemaEscola.cadastroAalunos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemaEscola.cadastroAalunos.model.Aluno;

public interface AlunoReposiry extends JpaRepository<Aluno, Long> {

	List<Aluno> findByClasseId(Long id);

	List<Aluno> findByDadosPessoaisNome(String nome);
}
