package br.com.sistemaEscola.cadastroAalunos.service.impl;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;

public interface ContratoAluno {

	Iterable<Aluno> buscarTodos();
	
	Aluno buscarPorId(Long id);
	
	Aluno salvar(DadosPessoais dadosPessoais);
	
	void atualizar(Long id, AlunoDto dto);
	
	void deletar(Long id);
	
}
