package br.com.sistemaEscola.cadastroAalunos.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;



public class AlunoDadosParaCadastrar {
	

	@NotNull @NotEmpty @Size(min = 8)
	private String cep;
	@NotNull @NotEmpty @Size(min = 3)
	private String nome;
	@NotNull @NotEmpty @Size(min = 11)
	private String cpf;
	@NotNull @NotEmpty @Size(min = 3)
	private String nomePai;
	@NotNull @NotEmpty @Size(min = 3)
	private String nomeMae;
	
	
	
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	public DadosPessoais coverterEmDadosPessoais() {
		return new DadosPessoais(nome, cpf, nomePai, nomeMae);
	}
		
	
	

	
	
	
}
