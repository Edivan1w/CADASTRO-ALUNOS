package br.com.sistemaEscola.cadastroAalunos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sistemaEscola.cadastroAalunos.form.AlunoDadosForm;

@Entity
@Table(name = "alunos")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Classe classe;
	private StatusDaMatricola statusDaMatricola = StatusDaMatricola.CURSANDO;
	@Embedded
	private DadosPessoais dadosPessoais;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Endereco endereco;
	@OneToMany(mappedBy = "aluno")
	private List<Disciplina> disciplinas = new ArrayList<>();
	
	
	
	public Aluno() {}


	public Aluno(Classe classe, DadosPessoais dadosPessoais, Endereco endereco, 
			List<Disciplina> disciplinas, StatusDaMatricola statusDaMatricola) {
		
		this.classe = classe;
		this.statusDaMatricola = statusDaMatricola;
		this.dadosPessoais = dadosPessoais;
		this.endereco = endereco;
		this.disciplinas = disciplinas;
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Classe getClasse() {
		return classe;
	}


	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public StatusDaMatricola getStatusDaMatricola() {
		return statusDaMatricola;
	}


	public void setStatusDaMatricola(StatusDaMatricola statusDaMatricola) {
		this.statusDaMatricola = statusDaMatricola;
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}


	public void setDadosPessoais(AlunoDadosForm dadosPessoais) {
		this.dadosPessoais = new DadosPessoais(dadosPessoais);
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}


	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}


	
	

	
	
	
	
}
