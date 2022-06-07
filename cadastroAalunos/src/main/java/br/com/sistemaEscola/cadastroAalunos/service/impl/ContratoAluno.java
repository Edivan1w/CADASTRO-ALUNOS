package br.com.sistemaEscola.cadastroAalunos.service.impl;

import java.util.List;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.AlunoDadosParaCadastrar;
import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;


public interface ContratoAluno {

	public List<AlunoDto> buscarListaAlunos();
	public Aluno buscarPorId(Long id);
	public List<AlunoDto> buscarPorNome(String nome);
	public void salvarAlunoComDados(Long id, AlunoDadosParaCadastrar dados);
	public void atualizarEnderecoAluno(Long id, String endereco);
	public void atualizarDadosPessoais(Long id, DadosPessoais dadosPessoais);
	public void deletarPorId(Long id);
}
