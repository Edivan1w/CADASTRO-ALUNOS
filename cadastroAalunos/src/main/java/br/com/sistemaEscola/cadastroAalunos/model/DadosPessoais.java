package br.com.sistemaEscola.cadastroAalunos.model;

import javax.persistence.Embeddable;

@Embeddable
public class DadosPessoais {

	private String nome;
	private String cpf;
	private String nomePai;
	private String nomeMae;
	
	public DadosPessoais() {}
	
	public DadosPessoais(String nome, String cpf, String nomePai, String nomeMae) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
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
	
	
	
}
