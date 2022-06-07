package br.com.sistemaEscola.cadastroAalunos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.AlunoDadosParaCadastrar;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;
import br.com.sistemaEscola.cadastroAalunos.model.Endereco;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;
import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.repository.EnderecoRepository;
import br.com.sistemaEscola.cadastroAalunos.service.impl.ContratoAluno;
import br.com.sistemaEscola.cadastroAalunos.service.impl.ViaCepService;

@Service
public class AlunoService implements ContratoAluno {

	@Autowired
	private AlunoReposiry alunoReposiry;
	@Autowired
	private ClasseRepository classeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService cepService;

	// esse método busca apenas os dados dos alunos que se deseja mostrar.
	@Override
	public List<AlunoDto> buscarListaAlunos() {
		List<Aluno> list = alunoReposiry.findAll();
		return AlunoDto.converterParaDto(list);
	}
	
	//retorna apenas um aluno com o parametro id
	@Override
	public Aluno buscarPorId(Long id) {
		return alunoReposiry.findById(id).get();
	}
	@Override
	public List<AlunoDto> buscarPorNome(String nome) {
		List<Aluno> list = alunoReposiry.findByDadosPessoaisNome(nome);
		return AlunoDto.converterParaDto(list);
	}

	//salva o aluno com todos os seus dados
	@Override
	public void salvarAlunoComDados(Long id, AlunoDadosParaCadastrar dados) {
		Classe classe = classeRepository.findById(id).get();
		Aluno aluno = new Aluno();
		aluno.setClasse(classe);
		aluno.setDadosPessoais(dados.getDadosPessoais());
		System.out.println(dados.getDadosPessoais().getNome());
		if (enderecoRepository.existsById(dados.getCep())) {
			System.out.println("1");
			Endereco endereco = enderecoRepository.findById(dados.getCep()).get();
			aluno.setEndereco(endereco);

		} else {
			System.out.println("2");
			String cep = dados.getCep();
			Endereco endereco = cepService.consultarCep(cep);
			aluno.setEndereco(endereco);
		}

		alunoReposiry.save(aluno);
	}

	//atualiza apenas o endereco do aluno
	@Override
	public void atualizarEnderecoAluno(Long id, String cep) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		
		System.out.println(cep);
		if (enderecoRepository.existsById(cep)) {
			System.out.println("1");
			salvarComEnderecoExistete(id, cep);
			ResponseEntity.ok();
			return;
		} else {
			System.out.println("2");
			salvarComEnderecoNaoExistete(id, cep);
			ResponseEntity.ok();
			return;
		}
	}
	
	//atualiza apenas os dados pessoais do aluno
	@Override
	public void atualizarDadosPessoais(Long id, DadosPessoais dadosPessoais) {
		Aluno aluno = alunoReposiry.findById(id).get();
		aluno.setDadosPessoais(dadosPessoais);
	}
	
	@Override
	public void deletarPorId(Long id) {
		alunoReposiry.deleteById(id);
	}

	// metodo utilitário
	public void salvarComEnderecoExistete(Long id, String cep) {
		Aluno aluno = alunoReposiry.findById(id).get();
		Endereco endereco2 = enderecoRepository.findById(cep).get();
		aluno.setEndereco(endereco2);

	}

	// metodo utilitário
	public void salvarComEnderecoNaoExistete(Long id, String cep) {
		Aluno aluno = alunoReposiry.findById(id).get();

		Endereco novoEndereco = cepService.consultarCep(cep);

		System.out.println(novoEndereco.getCep());

		aluno.setEndereco(novoEndereco);

	}

}
