package br.com.sistemaEscola.cadastroAalunos.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	public void incluirDisciplina(Long idAluno, String nomeDisciplina) {
		Disciplina disciplina = new Disciplina();
		disciplina.setNomeDisciplina(nomeDisciplina);
		disciplinasRepository.save(disciplina);
		Aluno aluno = alunoService.buscarPorId(idAluno);
		disciplina.setAluno(aluno);
	}
	
	
	public void cadastrarNotaPimeiroBimestre(NomeDisciplinas nomeDisciplina, Long idAluno, Double notaDoBimestre, Integer semestre) {
		Disciplina disciplina = disciplinasRepository.findDisciplina(nomeDisciplina, idAluno);
		
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
	}
	
	public List<Disciplina> buscarDisciplinaaPorAluno(Long idAluno){
		List<Disciplina> list = disciplinasRepository.findByAlunoDisciplinas(idAluno);
		return list;
	}
	
	
	
	
	
}
