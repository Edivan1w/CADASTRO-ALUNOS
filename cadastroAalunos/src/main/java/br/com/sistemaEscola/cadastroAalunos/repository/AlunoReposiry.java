package br.com.sistemaEscola.cadastroAalunos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.Disciplina;

public interface AlunoReposiry extends JpaRepository<Aluno, Long> {

	

	List<Aluno> findByDadosPessoaisNomeLike(String nome);

	Page<Aluno> findByDadosPessoaisNome(String nome, Pageable pageable);

	Disciplina findByDisciplinasNomeDisciplina(String nome);

	

	
}
