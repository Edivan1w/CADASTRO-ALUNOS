package br.com.sistemaEscola.cadastroAalunos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;
import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.service.impl.ContratoClasseService;
@Service
public class ClasseService implements ContratoClasseService{

	@Autowired
	private ClasseRepository classeRepository;
	@Autowired
	private AlunoReposiry alunoReposiry;
	
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
	public void atualizar(Long id, String descricao) {
		 Classe classe = classeRepository.findById(id).get();
		 classe.setDescriçao(descricao);
		 classeRepository.save(classe);
	}

	@Override
	public void deletar(Long id) {
		Classe classe = classeRepository.findById(id).get();
		classeRepository.delete(classe);
		
	}

	

}















