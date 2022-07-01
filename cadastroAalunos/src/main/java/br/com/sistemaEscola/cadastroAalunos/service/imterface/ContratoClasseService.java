package br.com.sistemaEscola.cadastroAalunos.service.imterface;


import java.util.List;

import br.com.sistemaEscola.cadastroAalunos.dto.ClasseDto;
import br.com.sistemaEscola.cadastroAalunos.form.ClasseForm;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;

public interface ContratoClasseService {
	
	List<ClasseDto> buscarPorTodos();
	ClasseDto salvar(ClasseForm classeForm);
	Classe buscarPorId(Long id);
	void atualizar(Long id, Classe classe);
	void deletar(Long id);
    
	
	
	
}
