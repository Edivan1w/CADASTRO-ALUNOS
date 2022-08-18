package br.com.sistemaEscola.cadastroAalunos.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Endereco;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;
import br.com.sistemaEscola.cadastroAalunos.repository.EnderecoRepository;
import br.com.sistemaEscola.cadastroAalunos.service.imterface.ViaCepService;

@Component
public class AlunoUtil {
	
	@Autowired
	private AlunoReposiry alunoReposiry;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService cepService;

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
