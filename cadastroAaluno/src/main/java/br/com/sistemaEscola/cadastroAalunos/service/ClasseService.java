package br.com.sistemaEscola.cadastroAalunos.service;


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
	public void atualizar(Long id, Classe classe) {
		 Classe classe2 = classeRepository.findById(id).get();
		 classe2.setDescricao(classe.getDescricao());
		 classeRepository.save(classe2);
	}

	@Override
	public void deletar(Long id) {
		Classe classe = classeRepository.findById(id).get();
		classeRepository.delete(classe);
		
	}

	
	public void matricularAluno(Long idClasse, Long idAluno) {
		Classe classe = classeRepository.findById(idClasse).get();
		Aluno aluno = alunoReposiry.findById(idAluno).get();
		aluno.setClasse(classe);
	}

}















