package br.com.sistemaEscola.cadastroAalunos.teste;

import java.util.List;

import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;

public class Teste {

	public static void main(String[] args) {
		Classe classe = new Classe();
		List<Aluno> alunos = classe.getAlunos();
		Aluno aluno = new Aluno();
		alunos.add(aluno);
		alunos.forEach(System.out::println);
	}
}
