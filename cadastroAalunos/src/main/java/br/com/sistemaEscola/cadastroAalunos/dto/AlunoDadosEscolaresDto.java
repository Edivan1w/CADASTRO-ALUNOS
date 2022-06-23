package br.com.sistemaEscola.cadastroAalunos.dto;

import java.util.List;
import java.util.stream.Collectors;


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
	
	public AlunoDadosEscolaresDto(Disciplina disciplina) {
		this.idAluno = disciplina.getAluno().getId();
		this.nome = disciplina.getAluno().getDadosPessoais().getNome();
		this.nomeDisciplina = disciplina.getNomeDisciplina().toString();
		this.notaPrimeroBimestre = disciplina.getPrimeiroBimestre();
		this.notaSegundoBimestre = disciplina.getSegundoBimestre();
		this.notaTerceiroBimestre = disciplina.getTerceiroBimestre();
		this.notaQuartoBimestre = disciplina.getQuartoBimestre();
	}

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

	public static List<AlunoDadosEscolaresDto> converter(List<Disciplina> buscarDisciplinaaPorAluno) {
		
		return buscarDisciplinaaPorAluno.stream().map(AlunoDadosEscolaresDto::new).collect(Collectors.toList());
	}
	
	
	

}
