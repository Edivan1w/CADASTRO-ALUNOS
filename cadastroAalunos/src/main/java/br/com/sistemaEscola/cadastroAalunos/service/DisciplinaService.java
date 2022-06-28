package br.com.sistemaEscola.cadastroAalunos.service;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDadosEscolaresDto;
import br.com.sistemaEscola.cadastroAalunos.form.FormPreenchimentoNota;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Disciplina;
import br.com.sistemaEscola.cadastroAalunos.model.NomeDisciplinas;
import br.com.sistemaEscola.cadastroAalunos.repository.DisciplinasRepository;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinasRepository disciplinasRepository;
	@Autowired
	private AlunoService alunoService;
	
	
	public AlunoDadosEscolaresDto incluirDisciplina(Long idAluno, String nomeDisciplina) {
		
		if(!alunoService.buscarOp(idAluno).isPresent() && !existeNomeDaDisciplina(nomeDisciplina)) {
			throw new NotFoundException("Disciplina ou aluno não existem no banco de dados");
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
		Disciplina disciplina = disciplinasRepository.findDisciplina(enumDisciplina, idAluno);
		Double notaDoBimestre = formDisciplina.getNota();
		return atribuirNotaSeBimestreExistir(formDisciplina, disciplina, notaDoBimestre);
	}

	
	public List<AlunoDadosEscolaresDto> buscarDisciplinaaPorAluno(Long idAluno){
		List<Disciplina> alunoDisciplinas = disciplinasRepository.findByAlunoDisciplinas(idAluno);
		List<AlunoDadosEscolaresDto>listDto = AlunoDadosEscolaresDto.converter(alunoDisciplinas);
		return listDto;
	}

	private Disciplina atribuirNotaSeBimestreExistir(FormPreenchimentoNota formDisciplina, Disciplina disciplina,
			Double notaDoBimestre) {
		Integer semestre = formDisciplina.getSemestreParaCadastrarNota();
		if(semestre <1 || semestre > 4){
			throw new NotFoundException("Bimestre não encontrado");
		}
		switch(semestre) {
		case 1:
			disciplina.setPrimeiroBimestre(notaDoBimestre);
			break;
		case 2: 
			disciplina.setSegundoBimestre(notaDoBimestre);
			break;
		case 3:
			disciplina.setTerceiroBimestre(notaDoBimestre);
			break;
		case 4:
			disciplina.setQuartoBimestre(notaDoBimestre);
			break;	
		}
		disciplina.setMedia();
		return disciplina;
	}
	
	
	private boolean existeNomeDaDisciplina(String nomeDisciplina) {
		NomeDisciplinas[] values = NomeDisciplinas.values();
		for(int i = 0; i <= values.length; i++) {
			if(nomeDisciplina.equalsIgnoreCase(values[i].toString())) {
				return true;
			}
		}
		return false;
	}
	
	
}
