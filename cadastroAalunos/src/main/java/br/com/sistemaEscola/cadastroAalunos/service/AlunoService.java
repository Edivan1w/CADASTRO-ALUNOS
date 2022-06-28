package br.com.sistemaEscola.cadastroAalunos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.form.AlunoDadosForm;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;

import br.com.sistemaEscola.cadastroAalunos.model.Endereco;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;
import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.repository.EnderecoRepository;
import br.com.sistemaEscola.cadastroAalunos.service.imterface.ContratoAluno;
import br.com.sistemaEscola.cadastroAalunos.service.imterface.ViaCepService;

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
	public Page<AlunoDto> buscarListaAlunos(String nome, Pageable pageable) {
		if (nome == null) {
			Page<Aluno> pageAlunos = alunoReposiry.findAll(pageable);
			return AlunoDto.converterParaDto(pageAlunos);
		}else {
			Page<Aluno> pageAlunos = alunoReposiry.findByDadosPessoaisNome(nome, pageable);
			return AlunoDto.converterParaDto(pageAlunos);
		}
		
	}
	
	//retorna apenas um aluno com o parametro id
	@Override
	public Aluno buscarPorId(Long id) {
		return alunoReposiry.findById(id).get();
	}
	
	public Optional<Aluno>bucarOptionalAluno(Long idAluno){
		return alunoReposiry.findById(idAluno);
	}


	//salva o aluno com todos os seus dados
	@Override
	public AlunoDto salvarAlunoComDados(Long id, AlunoDadosForm dados) {
		Classe classe = classeRepository.findById(id).get();
		Aluno aluno = new Aluno();
		aluno.setClasse(classe);
		aluno.setDadosPessoais(dados);
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
		 
         return new AlunoDto(aluno);
          
	}

	//atualiza apenas o endereco do aluno
	@Override
	public void atualizarCepEDados(Long id, AlunoDadosForm enderecoDados) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		Aluno aluno = alunoReposiry.findById(id).get();
		aluno.setDadosPessoais(enderecoDados);
		String cep = enderecoDados.getCep();
		System.out.println(cep);
		if (enderecoRepository.existsById(cep)) {
			System.out.println("1");
			salvarComEnderecoExistete(id, cep);
			
			return;
		} else {
			System.out.println("2");
			salvarComEnderecoNaoExistete(id, cep);
			
			return;
		}
	}
	
//	//atualiza apenas os dados pessoais do aluno
//	@Override
//	public void atualizarDadosPessoais(Long id, DadosPessoais dadosPessoais) {
//		Aluno aluno = alunoReposiry.findById(id).get();
//		aluno.setDadosPessoais(dadosPessoais);
//	}
	
	@Override
	public void deletarPorId(Long id) {
		alunoReposiry.deleteById(id);
	}
	
	

	

	// metodo utilitário
	private void salvarComEnderecoExistete(Long id, String cep) {
		Aluno aluno = alunoReposiry.findById(id).get();
		Endereco endereco2 = enderecoRepository.findById(cep).get();
		aluno.setEndereco(endereco2);

	}

	// metodo utilitário
	private void salvarComEnderecoNaoExistete(Long id, String cep) {
		Aluno aluno = alunoReposiry.findById(id).get();

		Endereco novoEndereco = cepService.consultarCep(cep);

		System.out.println(novoEndereco.getCep());

		aluno.setEndereco(novoEndereco);

	}

	public Optional<Aluno> buscarOp(Long idAluno) {
		return alunoReposiry.findById(idAluno);
	}

}
