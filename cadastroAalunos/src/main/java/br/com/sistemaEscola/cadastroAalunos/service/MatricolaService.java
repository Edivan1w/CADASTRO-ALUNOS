package br.com.sistemaEscola.cadastroAalunos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemaEscola.cadastroAalunos.model.Matricola;
import br.com.sistemaEscola.cadastroAalunos.repository.MatricolaRepository;
import br.com.sistemaEscola.cadastroAalunos.service.exceptions.ObjectNotFoundException;
import br.com.sistemaEscola.cadastroAalunos.service.util.MatricolaUtil;

@Service
public class MatricolaService {

	@Autowired
	private MatricolaRepository matricolaRepository;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private ClasseService classeService;
	@Autowired
	private MatricolaUtil matricolaUtil;

	public List<Matricola> findAll(Long id) {
		return alunoService.buscarPorId(id).getMatricolas();
	}

	public Matricola findById(Long idMatricola) {
		return matricolaRepository.findById(idMatricola).orElseThrow(
				() -> new ObjectNotFoundException("Matricola não encontrada, verifique no perfil do aluno"));
	}

	public Matricola matricular(Long idClasse, Long idAluno) {
		return matricolaRepository
				.save(matricolaUtil.matricular(classeService.buscarPorId(idClasse), alunoService.buscarPorId(idAluno)));
	}

	public void dletar(Long idMatricola) {
        matricolaRepository.delete(this.findById(idMatricola)); 
	}

}
