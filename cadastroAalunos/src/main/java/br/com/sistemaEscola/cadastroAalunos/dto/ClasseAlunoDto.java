package br.com.sistemaEscola.cadastroAalunos.dto;

import java.time.LocalDate;

import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.StatusDaMatricola;

public class ClasseAlunoDto {
	
	private Long idClasse;
	private String descricao;
	private String nivelEscolar;
	private Long idAluno;
	private String nomeAluno;
	private LocalDate dataMatricola;
	
	
	public ClasseAlunoDto() {}
	
	public ClasseAlunoDto(Classe classe, Aluno aluno) {
		this.idClasse = classe.getId();
		this.descricao = classe.getDescricao();
		this.nivelEscolar = classe.getNivelClasse().toString();
		this.idAluno = aluno.getId();
		this.nomeAluno = aluno.getDadosPessoais().getNome();
		this.dataMatricola = aluno.getDataMatricola();
	}

	public Long getIdClasse() {
		return idClasse;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNivelEscolar() {
		return nivelEscolar;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public LocalDate getDataMatricola() {
		return dataMatricola;
	}

	
	
	
	
}
