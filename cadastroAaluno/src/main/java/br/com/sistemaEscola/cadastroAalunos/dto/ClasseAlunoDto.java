package br.com.sistemaEscola.cadastroAalunos.dto;

public class ClasseAlunoDto {
	
	private Long idClasse;
	private Long idAluno;
	
	public ClasseAlunoDto() {}
	
	public ClasseAlunoDto(Long idClasse, Long idAluno) {
		super();
		this.idClasse = idClasse;
		this.idAluno = idAluno;
	}

	public Long getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(Long idClasse) {
		this.idClasse = idClasse;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	
	
	
}
