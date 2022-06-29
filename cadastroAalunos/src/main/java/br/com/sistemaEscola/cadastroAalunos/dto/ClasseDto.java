package br.com.sistemaEscola.cadastroAalunos.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.NivelClasse;

public class ClasseDto {
	
	private  Long idClasse;
	private String descricao;
	private NivelClasse nivelClasse;
	
	
	public ClasseDto(Classe classe) {
		this.idClasse = classe.getId();
		this.descricao = classe.getDescricao();
		this.nivelClasse = classe.getNivelClasse();
	}
	public Long getIdClasse() {
		return idClasse;
	}
	public String getDescricao() {
		return descricao;
	}
	public NivelClasse getNivelClasse() {
		return nivelClasse;
	}
	public static List<ClasseDto> converter(List<Classe> todos) {
		return todos.stream().map(ClasseDto::new).collect(Collectors.toList());
	}
	
	
	
	

}
