package br.com.sistemaEscola.cadastroAalunos.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import br.com.sistemaEscola.cadastroAalunos.dto.ClasseDto;
import br.com.sistemaEscola.cadastroAalunos.form.ClasseForm;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;

import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.service.imterface.ContratoClasseService;
@Service
public class ClasseService implements ContratoClasseService{

	@Autowired
	private ClasseRepository classeRepository;
	
	
	@Override
	public List<ClasseDto> buscarPorTodos() {
		List<Classe> todos = classeRepository.findAll();
		return ClasseDto.converter(todos);
		 
		
	}

	@Override
	public ClasseDto salvar(ClasseForm classeForm) {
		try {
			Classe classe = Classe.converterForParaClasse(classeForm);
			classeRepository.save(classe);
			return new ClasseDto(classe);
		} catch (Exception e) {
                 throw new NotFoundException("O nivel da classe informado não existe");
		}
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















