package br.com.sistemaEscola.cadastroAalunos.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@Enumerated(EnumType.STRING)
	private StatusDaMatricola statusDaMatricola = StatusDaMatricola.CURSANDO;
	private Long aluno;
	private Long classe;
	private LocalDate dataMatricola;
    private LocalDate dataEncerramentoMatricola;
    
     public Matricola() {}
     
     public Matricola(Classe classe, Aluno aluno) {
    	 this.aluno = aluno.getId();
    	 this.classe = classe.getId();
    	 this.dataMatricola = LocalDate.now();
    	 aluno.getMatricolas().add(this);
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

	public LocalDate getDataEncerramentoMatricola() {
		return dataEncerramentoMatricola;
	}

	public void setDataEncerramentoMatricola(LocalDate dataIncerramentoMatricola) {
		this.dataEncerramentoMatricola = dataIncerramentoMatricola;
	}
     
     
}
