package br.com.sistemaEscola.cadastroAalunos.model;

import javax.persistence.Embeddable;

import br.com.sistemaEscola.cadastroAalunos.form.AlunoDadosForm;


@Embeddable
public class DadosPessoais {


	private String nome;
	private String cpf;
	private String nomePai;
	private String nomeMae;
	
	public DadosPessoais() {}
	
	public DadosPessoais(AlunoDadosForm alunoDadosForm) {
		this.nome = alunoDadosForm.getNome();
		this.cpf = alunoDadosForm.getCpf();
		this.nomePai = alunoDadosForm.getNomePai();
		this.nomeMae = alunoDadosForm.getNomeMae();
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
