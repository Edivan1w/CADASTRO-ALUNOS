package br.com.sistemaEscola.cadastroAalunos.service.impl;


import br.com.sistemaEscola.cadastroAalunos.model.Classe;

public interface ContratoClasseService {
	
	Iterable<Classe> buscarPorTodos();
	void salvar(Classe classe);
	Classe buscarPorId(Long id);
	void atualizar(Long id, String descricao);
	void deletar(Long id);
    
	
	
	
}
