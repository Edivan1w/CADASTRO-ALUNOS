package br.com.sistemaEscola.cadastroAalunos.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDadosEscolaresDto;
import br.com.sistemaEscola.cadastroAalunos.form.FormPreenchimentoNota;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Disciplina;
import br.com.sistemaEscola.cadastroAalunos.model.NomeDisciplinas;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;
import br.com.sistemaEscola.cadastroAalunos.repository.DisciplinasRepository;
import br.com.sistemaEscola.cadastroAalunos.service.exceptions.ObjectNotFoundException;
import br.com.sistemaEscola.cadastroAalunos.service.util.DisciplinaUtil;

@Service
public class DisciplinaService  {

	@Autowired
	private DisciplinasRepository disciplinasRepository;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private DisciplinaUtil util;
	@Autowired
	private AlunoReposiry alunoReposiry;
	
	public AlunoDadosEscolaresDto incluirDisciplina(Long idAluno, String nomeDisciplina) {
		
		if(!alunoReposiry.findById(idAluno).isPresent() || !util.existeNomeDaDisciplina(nomeDisciplina)) {
			throw new ObjectNotFoundException("Disciplina ou aluno não existem no banco de dados");
		}
		Disciplina disciplina = new Disciplina();
		disciplina.setNomeDisciplina(nomeDisciplina);
		disciplina.setNomeAlunoVinculado(alunoService.buscarPorId(idAluno).getDadosPessoais().getNome());
		disciplinasRepository.save(disciplina);
		Aluno aluno = alunoService.buscarPorId(idAluno);
		disciplina.setAluno(aluno);
		return new AlunoDadosEscolaresDto(aluno, disciplina);
	}
	
	
	public Disciplina cadastrarNotaBimestre( Long idAluno, FormPreenchimentoNota formDisciplina) {
		NomeDisciplinas enumDisciplina = NomeDisciplinas.valueOf(formDisciplina.getNomeDisciplina().toUpperCase());
		if(!alunoReposiry.findById(idAluno).isPresent() ||
				disciplinasRepository.findDisciplina(enumDisciplina, idAluno) == null) {
			throw new ObjectNotFoundException("Disciplina ou aluno não existem no banco de dados");
		}
		
		Disciplina disciplina = disciplinasRepository.findDisciplina(enumDisciplina, idAluno);
		Double notaDoBimestre = formDisciplina.getNota();
		
		Disciplina nota = util.atribuirNotaSeBimestreExistir(formDisciplina, disciplina, notaDoBimestre);
		util.setarMedia(disciplina);
		return nota;
		
	}


	public List<AlunoDadosEscolaresDto> buscarDisciplinaaPorAluno(Long idAluno){
		if(!alunoReposiry.findById(idAluno).isPresent()) {
			throw new ObjectNotFoundException("ALUNO NÃO ENCOTRADO.");
		}
		List<Disciplina> alunoDisciplinas = disciplinasRepository.findByAlunoDisciplinas(idAluno);
		List<AlunoDadosEscolaresDto>listDto = AlunoDadosEscolaresDto.converter(alunoDisciplinas);
		return listDto;
	}


	
	
}
