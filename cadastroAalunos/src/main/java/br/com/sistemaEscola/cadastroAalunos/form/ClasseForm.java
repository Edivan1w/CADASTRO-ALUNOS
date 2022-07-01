package br.com.sistemaEscola.cadastroAalunos.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClasseForm {
	
	@NotEmpty @NotNull @Size(max = 40)
	private String descricao;
	@NotEmpty @NotNull @Size(max = 40)
	private String nivelClasse;
	
	
	
	public ClasseForm(String descricao, String nivelClasse) {
		super();
		this.descricao = descricao;
		this.nivelClasse = nivelClasse;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNivelClasse() {
		return nivelClasse;
	}
	public void setNivelClasse(String nivelClasse) {
		this.nivelClasse = nivelClasse;
	}
	
	
	
}
