package br.com.sistemaEscola.cadastroAalunos.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.form.AlunoDadosParaCadastrar;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;


public interface ContratoAluno {

	public List<AlunoDto> buscarListaAlunos();
	public Aluno buscarPorId(Long id);
	public List<AlunoDto> buscarPorNome(String nome);
	public ResponseEntity<AlunoDto> salvarAlunoComDados(Long id, AlunoDadosParaCadastrar dados, UriComponentsBuilder builder);
	public ResponseEntity<AlunoDto> atualizarEnderecoAluno(Long id, AlunoDadosParaCadastrar enderecoCep);
	public ResponseEntity<AlunoDto> atualizarDadosPessoais(Long id, DadosPessoais dadosPessoais);
	public void deletarPorId(Long id);
}
