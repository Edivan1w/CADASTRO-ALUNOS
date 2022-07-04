package br.com.sistemaEscola.cadastroAalunos.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import br.com.sistemaEscola.cadastroAalunos.dto.ClasseDto;
import br.com.sistemaEscola.cadastroAalunos.form.ClasseForm;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;

import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.service.imterface.ContratoClasseService;

@Service
public class ClasseService implements ContratoClasseService {

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
	public ClasseDto buscarPorId(Long id) {
		if(classeRepository.findById(id).isPresent()) {
			return new ClasseDto(classeRepository.findById(id).get());
		}
		throw new NotFoundException("Classe não encontrada para atualização.");
	}
	@Override
	public Optional<Classe> buscarOptional(Long id){
		return classeRepository.findById(id);
	}

	@Override
	public ClasseDto atualizar(Long id, ClasseForm form) {
		if(classeRepository.findById(id).isPresent()) {
			Classe classe = classeRepository.findById(id).get();
			classe.setDescricao(form.getDescricao());
			classe.setNivelClasse(form.getNivelClasse());
			return new ClasseDto(classe);
		}
		throw new NotFoundException("Classe não encontrada para atualização.");
		

	}

	@Override
	public void deletar(Long id) {
		Classe classe = classeRepository.findById(id).get();
		classeRepository.delete(classe);

	}

}
