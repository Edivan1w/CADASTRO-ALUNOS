package br.com.sistemaEscola.cadastroAalunos.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistemaEscola.cadastroAalunos.model.Disciplina;
import br.com.sistemaEscola.cadastroAalunos.model.NomeDisciplinas;

public interface DisciplinasRepository extends JpaRepository<Disciplina, Long> {

	@Query("SELECT d FROM Disciplina d WHERE d.nomeDisciplina= :nome and d.aluno.id= :id")
	Disciplina findDisciplina(NomeDisciplinas nome, Long id);

	@Query("SELECT d FROM Disciplina d WHERE d.aluno.id= :idAluno")
	List<Disciplina> findByAlunoDisciplinas(Long idAluno);
   
	
}
