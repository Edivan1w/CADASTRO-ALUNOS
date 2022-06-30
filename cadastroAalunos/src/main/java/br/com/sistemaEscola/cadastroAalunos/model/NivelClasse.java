package br.com.sistemaEscola.cadastroAalunos.model;

import org.webjars.NotFoundException;

public enum NivelClasse {
	
	PRIMEIRO_ANO_ENSINO_MEDIO,
	SEGUNDO_ANO_ENSINO_MEDIO,
	TERCEIRO_ANO_ENSINO_MEDIO;

	
	public static NivelClasse verificarSeExisteNivel(String nome) {
		NivelClasse[] values = NivelClasse.values();
		for (NivelClasse nivelClasse : values) {
			if(nome.toUpperCase().subSequence(0, 4) == nivelClasse.toString().subSequence(0, 4)) {
				return nivelClasse;
			}
		}
		throw new NotFoundException("Nivel escolar não encontrado") ;
	}
	
}

