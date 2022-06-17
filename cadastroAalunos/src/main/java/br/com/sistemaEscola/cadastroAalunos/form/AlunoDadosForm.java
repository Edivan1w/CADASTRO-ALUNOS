package br.com.sistemaEscola.cadastroAalunos.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class AlunoDadosForm {
	

	@NonNull @NotEmpty @Size(min = 9)
	private String cep;
	@NonNull @NotEmpty @Size(min = 3)
	private String nome;
	@NonNull @NotEmpty @Size(min = 11)
	private String cpf;
	@NonNull @NotEmpty @Size(min = 3)
	private String nomePai;
	@NonNull @NotEmpty @Size(min = 3)
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
	

   
	
	
	

	
	
	
}
