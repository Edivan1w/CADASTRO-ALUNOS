package br.com.sistemaEscola.cadastroAalunos.service.imterface;


import java.util.List;

import br.com.sistemaEscola.cadastroAalunos.dto.ClasseDto;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;

public interface ContratoClasseService {
	
	List<ClasseDto> buscarPorTodos();
	void salvar(Classe classe);
	Classe buscarPorId(Long id);
	void atualizar(Long id, Classe classe);
	void deletar(Long id);
    
	
	
	
}
