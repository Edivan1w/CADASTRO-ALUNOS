package br.com.sistemaEscola.cadastroAalunos.service.util;

import org.springframework.stereotype.Component;

import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.Matricola;

@Component
public class MatricolaUtil {

	public Matricola matricular(Classe classe, Aluno aluno) {
		return new Matricola(classe, aluno);
	}

}
