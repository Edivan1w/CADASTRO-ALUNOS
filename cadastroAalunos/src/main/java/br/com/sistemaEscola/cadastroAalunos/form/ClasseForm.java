package br.com.sistemaEscola.cadastroAalunos.form;

public class ClasseForm {
	
	private String descricao;
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
