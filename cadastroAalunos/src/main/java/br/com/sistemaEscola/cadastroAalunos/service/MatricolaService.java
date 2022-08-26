package br.com.sistemaEscola.cadastroAalunos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemaEscola.cadastroAalunos.model.Matricola;
import br.com.sistemaEscola.cadastroAalunos.repository.MatricolaRepository;
import br.com.sistemaEscola.cadastroAalunos.service.exceptions.ObjectNotFoundException;

@Service
public class MatricolaService {

	
	@Autowired
	private MatricolaRepository matricolaRepository;
    @Autowired
    private AlunoService alunoService;
	
	
	
	public Matricola findById(Long id) {
		return alunoService.buscarPorId(id).getMatricolas()
				.stream().filter(a -> a.getNumeroMatricola().equals(id)).findAny().orElseThrow(
				() -> new ObjectNotFoundException("Matricola não encontrada"));
		//return matricolaRepository.findById(id).orElseThrow(
				
	}
	
	
	
	
}
