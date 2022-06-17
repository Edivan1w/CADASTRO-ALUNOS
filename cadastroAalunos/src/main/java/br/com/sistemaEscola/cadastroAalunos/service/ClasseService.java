package br.com.sistemaEscola.cadastroAalunos.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.sistemaEscola.cadastroAalunos.model.Classe;

import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.service.imterface.ContratoClasseService;
@Service
public class ClasseService implements ContratoClasseService{

	@Autowired
	private ClasseRepository classeRepository;
	
	
	@Override
	public Iterable<Classe> buscarPorTodos() {
		return classeRepository.findAll();
		 
	}

	@Override
	public void salvar(Classe classe) {
		classeRepository.save(classe);
	}

	@Override
	public Classe buscarPorId(Long id) {
	  Classe classe = classeRepository.findById(id).get();
	  return classe;
	
	}

	@Override
	public void atualizar(Long id, Classe classe) {
		 Classe classe2 = classeRepository.findById(id).get();
		 classe2.setDescricao(classe.getDescricao());
		
	}

	@Override
	public void deletar(Long id) {
		Classe classe = classeRepository.findById(id).get();
		classeRepository.delete(classe);
		
	}

	
}















