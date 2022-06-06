package br.com.sistemaEscola.cadastroAalunos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;
import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.repository.EnderecoRepository;
import br.com.sistemaEscola.cadastroAalunos.service.impl.ViaCepService;

@Service
public class AlunoService {

	@Autowired
	private AlunoReposiry alunoReposiry;
	@Autowired
    private ClasseRepository classeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService cepService;
	
	//esse método busca apenas os dados dos alunos que se deseja mostrar. 
	public List<AlunoDto>buscarListaAlunos(){
		List<Aluno> list = alunoReposiry.findAll();
		return AlunoDto.converterParaDto(list);
	}
	
	public List<AlunoDto>buscarPorNome(String nome){
		List<Aluno>list = alunoReposiry.findByDadosPessoaisNome(nome);
		list.forEach(x -> System.out.println(x.getDadosPessoais().getNome()));
		return AlunoDto.converterParaDto(list);
	}
	
	public void deletarPorId(Long id) {
		alunoReposiry.deleteById(id);
	}
	
}
