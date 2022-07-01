package br.com.sistemaEscola.cadastroAalunos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sistemaEscola.cadastroAalunos.form.ClasseForm;

@Entity
@Table(name = "classe")
public class Classe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private NivelClasse nivelClasse;
	


	@OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
	private List<Aluno> alunos = new ArrayList<>();

	public Classe() {
	}

	public Classe(String descricao, NivelClasse nivelClasse) {
		this.descricao = descricao;
		this.nivelClasse = nivelClasse;
	}

	
	
	

	public Long getId() {
		return id;
	}

	public NivelClasse getNivelClasse() {
		return nivelClasse;
	}



	public void setNivelClasse(String nivelClasse) {
		this.nivelClasse = NivelClasse.verificarSeExisteNivel(nivelClasse);
	}

	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public static Classe converterForParaClasse(ClasseForm classeForm) {
		 Classe classe = new Classe();
		 classe.setDescricao(classeForm.getDescricao());
		 classe.setNivelClasse(classeForm.getNivelClasse());
		 return classe;
	}

	
	
	
}
