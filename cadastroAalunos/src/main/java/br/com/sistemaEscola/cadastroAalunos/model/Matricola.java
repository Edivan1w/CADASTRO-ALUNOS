package br.com.sistemaEscola.cadastroAalunos.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matricola")
public class Matricola {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numeroMatricola;
	private Long aluno;
	private Long classe;
	private LocalDate dataMatricola;
    private LocalDate dataIncerramentoMatricola;
    
     public Matricola() {}
     
     public Matricola(Aluno aluno, Classe classe) {
    	 this.aluno = aluno.getId();
    	 this.classe = classe.getId();
    	 this.dataMatricola = LocalDate.now();
     }

	public Long getNumeroMatricola() {
		return numeroMatricola;
	}

	public void setNumeroMatricola(Long numeroMatricola) {
		this.numeroMatricola = numeroMatricola;
	}

	public Long getAluno() {
		return aluno;
	}

	public void setAluno(Long aluno) {
		this.aluno = aluno;
	}

	public Long getClasse() {
		return classe;
	}

	public void setClasse(Long classe) {
		this.classe = classe;
	}

	public LocalDate getDataMatricola() {
		return dataMatricola;
	}

	public void setDataMatricola(LocalDate dataMatricola) {
		this.dataMatricola = dataMatricola;
	}

	public LocalDate getDataIncerramentoMatricola() {
		return dataIncerramentoMatricola;
	}

	public void setDataIncerramentoMatricola(LocalDate dataIncerramentoMatricola) {
		this.dataIncerramentoMatricola = dataIncerramentoMatricola;
	}
     
     
}
