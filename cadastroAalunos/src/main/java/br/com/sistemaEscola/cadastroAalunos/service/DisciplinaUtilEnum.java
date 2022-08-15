package br.com.sistemaEscola.cadastroAalunos.service;

import br.com.sistemaEscola.cadastroAalunos.form.FormPreenchimentoNota;
import br.com.sistemaEscola.cadastroAalunos.model.Disciplina;

public enum DisciplinaUtilEnum {
	
	PRIMEIRO(1){
		@Override
		public void setarNota(Disciplina disciplina, Double nota) {
			disciplina.setPrimeiroBimestre(nota);
			
		}
	},
	SEGUNDO(2){
		@Override
		public void setarNota(Disciplina disciplina, Double nota) {
			disciplina.setSegundoBimestre(nota);
			
		}
	},
	TERCEIRO(3){
		@Override
		public void setarNota(Disciplina disciplina, Double nota) {
			disciplina.setTerceiroBimestre(nota);
			
		}
	},
	QUARTO(4){
		@Override
		public void setarNota(Disciplina disciplina, Double nota) {
			disciplina.setQuartoBimestre(nota);
			
		}
	};
	
	
	
	private Integer bimestre;
	
	DisciplinaUtilEnum(Integer bimestre) {
		this.bimestre = bimestre;
	}
	
	
	public Integer getBimestre() {
		return bimestre;
	}

	
	public abstract void setarNota(Disciplina disciplina, Double nota);


	public static Disciplina setarNota(Disciplina disciplina, FormPreenchimentoNota nota) {
		
		for (DisciplinaUtilEnum d : DisciplinaUtilEnum.values()) {
			if(nota.getSemestreParaCadastrarNota().equals(d.getBimestre())) {
				d.setarNota(disciplina, nota.getNota());
			}
		}
		return disciplina;
	}

}
