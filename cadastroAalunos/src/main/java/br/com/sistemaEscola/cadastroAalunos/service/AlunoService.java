package br.com.sistemaEscola.cadastroAalunos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.dto.ClasseAlunoDto;
import br.com.sistemaEscola.cadastroAalunos.form.AlunoDadosForm;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.Endereco;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;
import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.repository.EnderecoRepository;
import br.com.sistemaEscola.cadastroAalunos.service.exceptions.ObjectNotFoundException;
import br.com.sistemaEscola.cadastroAalunos.service.imterface.ContratoAluno;
import br.com.sistemaEscola.cadastroAalunos.service.imterface.ViaCepService;
import br.com.sistemaEscola.cadastroAalunos.service.util.AlunoUtil;

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
	@Autowired
	private AlunoUtil alunoUtil;

	// esse método busca apenas os dados dos alunos que se deseja mostrar.
	@Override
	public Page<AlunoDto> buscarListaAlunos(String nome, Pageable pageable) {
		if (nome == null) {
			Page<Aluno> pageAlunos = alunoReposiry.findAll(pageable);
			return AlunoDto.converterParaDto(pageAlunos);
		} else {
			Page<Aluno> pageAlunos = alunoReposiry.findByDadosPessoaisNome(nome, pageable);
			return AlunoDto.converterParaDto(pageAlunos);
		}
	}

	public ClasseAlunoDto buscarDadosMatricola(Long idAluno) {
		if (alunoReposiry.findById(idAluno).isPresent()) {
			Aluno aluno = this.buscarPorId(idAluno);
			return new ClasseAlunoDto(aluno.getClasse(), aluno);
		}
		throw new ObjectNotFoundException("ALUNO OU CLASSE NÃO ENCOTRADO.");
	}

	// retorna apenas um aluno com o parametro id
	@Override
	public Aluno buscarPorId(Long id) {
		return alunoReposiry.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ALUNO NÃO ENCOTRADO NO BANCO DE DADOS."));
	}

	// salva o aluno com todos os seus dados
	@Override
	public AlunoDto salvarAlunoComDados(AlunoDadosForm dados) {
		Aluno aluno = new Aluno();
		aluno.setDadosPessoais(dados);
		if (enderecoRepository.existsById(dados.getCep())) {
			Endereco endereco = enderecoRepository.findById(dados.getCep()).get();
			aluno.setEndereco(endereco);

		} else {
			String cep = dados.getCep();
			Endereco endereco = cepService.consultarCep(cep);
			aluno.setEndereco(endereco);
		}

		alunoReposiry.save(aluno);
		return new AlunoDto(aluno);

	}

	// atualiza apenas o endereco do aluno
	@Override
	public void atualizarCepEDados(Long idAluno, AlunoDadosForm enderecoDados) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		this.buscarPorId(idAluno).setDadosPessoais(enderecoDados);
		String cep = enderecoDados.getCep();
		if (enderecoRepository.existsById(cep)) {
			alunoUtil.salvarComEnderecoExistete(idAluno, cep);
		} else {
			alunoUtil.salvarComEnderecoNaoExistete(idAluno, cep);
		}
	}

	@Override
	public void deletarPorId(Long id) {
		this.buscarPorId(id);
		alunoReposiry.deleteById(id);
	}

}
