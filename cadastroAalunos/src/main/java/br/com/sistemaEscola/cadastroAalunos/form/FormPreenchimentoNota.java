package br.com.sistemaEscola.cadastroAalunos.form;



public class FormPreenchimentoNota {
	
	private String nomeDisciplina;
	private Double nota;
	private Integer semestreParaCadastrarNota;
	
	public FormPreenchimentoNota(String nomeDisciplina, Double nota, Integer semestreParaCadastrarNota) {
		this.nomeDisciplina = nomeDisciplina;
		this.nota = nota;
		this.semestreParaCadastrarNota = semestreParaCadastrarNota;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Integer getSemestreParaCadastrarNota() {
		return semestreParaCadastrarNota;
	}

	public void setSemestreParaCadastrarNota(Integer semestreParaCadastrarNota) {
		this.semestreParaCadastrarNota = semestreParaCadastrarNota;
	}
	
	

}
