package br.com.sistemaEscola.cadastroAalunos.service.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import br.com.sistemaEscola.cadastroAalunos.form.FormPreenchimentoNota;
import br.com.sistemaEscola.cadastroAalunos.model.Disciplina;
import br.com.sistemaEscola.cadastroAalunos.model.NomeDisciplinas;

@Component
public class DisciplinaUtil {

	public Disciplina atribuirNotaSeBimestreExistir(FormPreenchimentoNota formDisciplina, Disciplina disciplina,
			Double notaDoBimestre) {
		Integer semestre = formDisciplina.getSemestreParaCadastrarNota();
		if(semestre == 1 || semestre == 2 || semestre == 3 || semestre == 4){
			return DisciplinaUtilEnum.setarNota(disciplina, formDisciplina);
		}
		throw new NotFoundException("Bimestre não encontrado");

	}
	
	
	public boolean existeNomeDaDisciplina(String nomeDisciplina) {
		NomeDisciplinas[] values = NomeDisciplinas.values();
		for(int i = 0; i <= values.length; i++) {
			if(nomeDisciplina.equalsIgnoreCase(values[i].toString())) {
				return true;
			}
		}
		return false;
	}
	
	public void setarMedia(Disciplina disciplina) {
		List<Double> notas = Arrays.asList(disciplina.getPrimeiroBimestre(), disciplina.getSegundoBimestre(),
				disciplina.getTerceiroBimestre(), disciplina.getQuartoBimestre());
		disciplina.setMedia(notas.stream().filter(n -> n != null)
				.mapToDouble(n -> Double.valueOf(n)).average().getAsDouble());
	}

}