package br.com.sistemaEscola.cadastroAalunos.model;






import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity @Table(name = "disciplinas")
public class Disciplina {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private NomeDisciplinas nomeDisciplina;
	private Double primeiroBimestre = 0.0;
	private Double segundoBimestre = 0.0;
	private Double terceiroBimestre = 0.0;
	private Double quartoBimestre = 0.0;
	private Double media = 0.0;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Aluno aluno;
	
	
	public Disciplina() {}
	
	
    
	public Disciplina(NomeDisciplinas nomeDisciplina, Double primeiroBimestre, Double segundoBimestre,
			Double terceiroBimestre, Double quartoBimestre, Double media, Aluno aluno) {
		super();
		this.nomeDisciplina = nomeDisciplina;
		this.primeiroBimestre = primeiroBimestre;
		this.segundoBimestre = segundoBimestre;
		this.terceiroBimestre = terceiroBimestre;
		this.quartoBimestre = quartoBimestre;
		this.media = media;
		this.aluno = aluno;
	}



    



	public NomeDisciplinas getNomeDisciplina() {
		return nomeDisciplina;
	}



	public void setNomeDisciplina(String nomeDisciplina) {
		NomeDisciplinas valueOf = NomeDisciplinas.valueOf(nomeDisciplina.toUpperCase());
		System.out.println(valueOf);
		this.nomeDisciplina = valueOf;
	}



	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Double getPrimeiroBimestre() {
		return primeiroBimestre;
	}
	public void setPrimeiroBimestre(Double primeiroBimestre) {
		this.primeiroBimestre = primeiroBimestre;
	}
	public Double getSegundoBimestre() {
		return segundoBimestre;
	}
	public void setSegundoBimestre(Double segundoBimestre) {
		this.segundoBimestre = segundoBimestre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getTerceiroBimestre() {
		return terceiroBimestre;
	}
	public void setTerceiroBimestre(Double terceiroBimestre) {
		this.terceiroBimestre = terceiroBimestre;
	}
	public Double getQuartoBimestre() {
		return quartoBimestre;
	}
	public void setQuartoBimestre(Double quartoBimestre) {
		this.quartoBimestre = quartoBimestre;
	}
	
	public Double getMedia() {
		return media;
	}
	public void setMedia() {
		this.media = (this.getPrimeiroBimestre() + this.getSegundoBimestre() + 
				this.getTerceiroBimestre() + this.getQuartoBimestre())/4;
	}
	
}
