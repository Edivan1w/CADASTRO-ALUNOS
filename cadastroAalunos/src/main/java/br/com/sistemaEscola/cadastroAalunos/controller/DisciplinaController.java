package br.com.sistemaEscola.cadastroAalunos.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaEscola.cadastroAalunos.model.Disciplina;
import br.com.sistemaEscola.cadastroAalunos.model.NomeDisciplinas;
import br.com.sistemaEscola.cadastroAalunos.service.DisciplinaService;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

	
	@Autowired
	private DisciplinaService disciplinaService;

	
	@GetMapping("/{idAluno}")
	public ResponseEntity<List<Disciplina>> buscarDisciplinasPorAluno(@RequestParam Long idAluno){
		List<Disciplina> list = disciplinaService.buscarDisciplinaaPorAluno(idAluno);
		return ResponseEntity.ok(list);
	}
	
	
	@PostMapping("/{idAluno}")
	@Transactional
	public void cadastrarMateriaNoPerfilAluno(@PathVariable Long idAluno, @RequestParam String nomeDisciplina) {
		disciplinaService.incluirDisciplina(idAluno, nomeDisciplina);
	}
	
	@PutMapping("{idAluno}")
	@Transactional
	public void cadastrarOuAtualizarNotas(@RequestParam String nomeDisciplina, @PathVariable Long idAluno, @RequestParam Double nota,
			@RequestParam Integer semestreParaCadastrarNota) {
		NomeDisciplinas nome = NomeDisciplinas.valueOf(nomeDisciplina.toUpperCase());
		disciplinaService.cadastrarNotaPimeiroBimestre(nome, idAluno, nota, semestreParaCadastrarNota);
	}
	
	
}
