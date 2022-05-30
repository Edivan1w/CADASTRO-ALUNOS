package br.com.sistemaEscola.cadastroAalunos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "classe")
public class Classe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Aluno> alunos = new ArrayList<>();
	
	
	public Classe() {}
	
	
	public Classe( String descriçao) {
		this.descricao = descriçao;
			}


public Long getId() {
		return id;
	}

	public String getDescricao() {
	return descricao;
}


public void setDescricao(String descricao) {
	this.descricao = descricao;
}


public void setAlunos(Aluno alunos) {
	
	this.alunos.add(alunos);
}


	public void setId(Long id) {
		this.id = id;
	}

	public String getDescriçao() {
		return descricao;
	}

	public void setDescriçao(String descriçao) {
		this.descricao = descriçao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	
	
	
}
