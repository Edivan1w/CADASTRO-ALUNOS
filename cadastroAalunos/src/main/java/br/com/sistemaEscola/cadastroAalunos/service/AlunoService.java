package br.com.sistemaEscola.cadastroAalunos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
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
	private ViaCepService cepService;
	@Autowired
	private ClasseService classeService;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public Iterable<Aluno> buscarTodos() {
		return alunoReposiry.findAll();
		 
	}

	@Override
	public Aluno buscarPorId(Long Id) {
		Aluno aluno = alunoReposiry.findById(Id).get();
		return aluno;
	}

	@Override
	public Aluno salvar(DadosPessoais dadosPessoais) {
		Aluno aluno = new Aluno();
		aluno.setDadosPessoais(dadosPessoais);
		alunoReposiry.save(aluno);
		return aluno;
	}
	
	public void salvarComCep(Long id, Aluno aluno) {
		String cep = aluno.getEndereco().getCep();
		Long cep2 = Long.valueOf(cep);
		Endereco endereco = enderecoRepository.findById(cep2).orElseGet(() -> {
			Endereco novoEndereco = cepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		enderecoRepository.save(endereco);
		Aluno aluno2 = alunoReposiry.findById(id).get();
		aluno2.setEndereco(endereco);
		
	}

	@Override
	public void atualizar(Long id, AlunoDto dto) {
		Aluno aluno = alunoReposiry.findById(id).get();
		aluno.setDadosPessoais(dto.getDadosPessoais());
		aluno.setClasse(dto.getClasse());
		aluno.setEndereco(dto.getEndereco());
	}

	@Override
	public void deletar(Long id) {
		alunoReposiry.deleteById(id);
		
	}

	
	
}
