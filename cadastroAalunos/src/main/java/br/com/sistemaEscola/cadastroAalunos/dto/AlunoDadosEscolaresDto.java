package br.com.sistemaEscola.cadastroAalunos.dto;

import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Disciplina;

public class AlunoDadosEscolaresDto {
	
	private Long idAluno;
	private String nome;
	private String nomeDisciplina;
	private Double notaPrimeroBimestre;
	private Double notaSegundoBimestre;
	private Double notaTerceiroBimestre;
	private Double notaQuartoBimestre;
	
	public AlunoDadosEscolaresDto(Aluno aluno, Disciplina disciplina) {
		this.idAluno = aluno.getId();
		this.nome = aluno.getDadosPessoais().getNome();
		this.nomeDisciplina = disciplina.getNomeDisciplina().toString();
		this.notaPrimeroBimestre = disciplina.getPrimeiroBimestre();
		this.notaSegundoBimestre = disciplina.getSegundoBimestre();
		this.notaTerceiroBimestre = disciplina.getTerceiroBimestre();
		this.notaQuartoBimestre = disciplina.getQuartoBimestre();
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public String getNome() {
		return nome;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public Double getNotaPrimeroBimestre() {
		return notaPrimeroBimestre;
	}

	public Double getNotaSegundoBimestre() {
		return notaSegundoBimestre;
	}

	public Double getNotaTerceiroBimestre() {
		return notaTerceiroBimestre;
	}

	public Double getNotaQuartoBimestre() {
		return notaQuartoBimestre;
	}
	
	
	

}
