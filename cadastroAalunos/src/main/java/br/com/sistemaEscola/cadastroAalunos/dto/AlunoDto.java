package br.com.sistemaEscola.cadastroAalunos.dto;



import java.util.List;
import java.util.stream.Collectors;

import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;
import br.com.sistemaEscola.cadastroAalunos.model.Endereco;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;

public class AlunoDto {


	private String nome;
	private String nomePai;
	private String nomeMae;
	private String cep;
	
	
	public AlunoDto() {}

	public AlunoDto(Long idClasseParaMatricola, String nome,  String nomePai, String nomeMae) {
		
		this.nome = nome;
		
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
	}

	public AlunoDto(Aluno aluno) {
		this.nome = aluno.getDadosPessoais().getNome();
		this.nomePai = aluno.getDadosPessoais().getNomePai();
		this.nomeMae = aluno.getDadosPessoais().getNomeMae();
		this.cep = aluno.getEndereco().getCep();
	}

	
	
	

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	
	
	public static List<AlunoDto> converterParaDto(List<Aluno> alunos) {
		return alunos.stream().map(AlunoDto::new).collect(Collectors.toList());
	}
	

	
	
}
