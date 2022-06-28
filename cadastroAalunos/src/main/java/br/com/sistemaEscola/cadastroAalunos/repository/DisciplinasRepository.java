package br.com.sistemaEscola.cadastroAalunos.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistemaEscola.cadastroAalunos.model.Disciplina;
import br.com.sistemaEscola.cadastroAalunos.model.NomeDisciplinas;

public interface DisciplinasRepository extends JpaRepository<Disciplina, Long> {

	@Query("SELECT d FROM Disciplina d WHERE d.nomeDisciplina= :nome and d.aluno.id= :id")
	Disciplina findDisciplina(NomeDisciplinas nome, Long id);

	@Query(value = "select * from disciplinas where aluno_id = :idAluno", nativeQuery = true)
	List<Disciplina> findByAlunoDisciplinas(Long idAluno);
	
	@Query("SELECT d FROM Disciplina d WHERE d.nomeDisciplina= :nome and d.aluno.id= :id")
	Optional<Disciplina> findDisciplinaOp(String nome, Long id);
}
