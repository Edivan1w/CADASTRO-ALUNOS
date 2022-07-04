package br.com.sistemaEscola.cadastroAalunos.service.imterface;


import java.util.List;
import java.util.Optional;

import br.com.sistemaEscola.cadastroAalunos.dto.ClasseDto;
import br.com.sistemaEscola.cadastroAalunos.form.ClasseForm;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;

public interface ContratoClasseService {
	
	List<ClasseDto> buscarPorTodos();
	ClasseDto salvar(ClasseForm classeForm);
	ClasseDto buscarPorId(Long id);
	ClasseDto atualizar(Long id, ClasseForm classe);
	void deletar(Long id);
	Optional<Classe> buscarOptional(Long id);
    
	
	
	
}
