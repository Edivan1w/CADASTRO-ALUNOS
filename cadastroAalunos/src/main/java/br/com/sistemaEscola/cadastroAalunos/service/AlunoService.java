package br.com.sistemaEscola.cadastroAalunos.service;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.form.AlunoDadosParaCadastrar;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
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

	// retorna apenas um aluno com o parametro id
	@Override
	public Aluno buscarPorId(Long id) {
		return alunoReposiry.findById(id).get();
	}

	@Override
	public List<AlunoDto> buscarPorNome(String nome) {
		List<Aluno> list = alunoReposiry.findByDadosPessoaisNome(nome);
		return AlunoDto.converterParaDto(list);
	}

	// salva o aluno com todos os seus dados
	@Override
	public ResponseEntity<AlunoDto> salvarAlunoComDados(Long id, AlunoDadosParaCadastrar dadosCadastrar, UriComponentsBuilder builder) {
		Classe classe = classeRepository.findById(id).get();
		Aluno aluno = new Aluno();
		aluno.setClasse(classe);
		DadosPessoais dados = dadosCadastrar.coverterEmDadosPessoais();
		aluno.setDadosPessoais(dados);
		System.out.println(dados.getNome());
		if (enderecoRepository.existsById(dadosCadastrar.getCep())) {
			System.out.println("1");
			Endereco endereco = enderecoRepository.findById(dadosCadastrar.getCep()).get();
			aluno.setEndereco(endereco);

		} else {
			System.out.println("2");
			String cep = dadosCadastrar.getCep();
			Endereco endereco = cepService.consultarCep(cep);
			aluno.setEndereco(endereco);
		}

		alunoReposiry.save(aluno);
		// aqui está sendo criada a nova url que será devolvida ao cliente.
		URI uri = builder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
		// mandar o status 201 que é o de criação, que recebe umURI
        //é uma boa prática do padrão REST devolver dto.
		return ResponseEntity.created(uri).body(new AlunoDto(aluno));
	}

	// atualiza apenas o endereco do aluno
	@Override
	public ResponseEntity<AlunoDto> atualizarEnderecoAluno(Long id, AlunoDadosParaCadastrar enderecoCep) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = enderecoCep.getCep();
		System.out.println(cep);
		if (enderecoRepository.existsById(cep)) {
			System.out.println("1");
			salvarComEnderecoExistete(id, cep);
			
		} else {
			System.out.println("2");
			salvarComEnderecoNaoExistete(id, cep);
		}
		
		Aluno aluno = alunoReposiry.findById(id).get();
		return ResponseEntity.ok(new AlunoDto(aluno));
	}

	// atualiza apenas os dados pessoais do aluno
	@Override
	public ResponseEntity<AlunoDto> atualizarDadosPessoais(Long id, DadosPessoais dadosPessoais) {
		Aluno aluno = alunoReposiry.findById(id).get();
		aluno.setDadosPessoais(dadosPessoais);
		return ResponseEntity.ok(new AlunoDto(aluno));
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
