package br.com.sistemaEscola.cadastroAalunos.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaEscola.cadastroAalunos.model.Matricola;
import br.com.sistemaEscola.cadastroAalunos.service.MatricolaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/matricola")
public class MatricolaController {

	@Autowired
	private MatricolaService matricolaService;
	
	@GetMapping("aluno/{idAluno}")
	public ResponseEntity<List<Matricola>> findByAll(@PathVariable Long idAluno){
		return ResponseEntity.ok().body(matricolaService.findAll(idAluno));	
	}
	
	@GetMapping("/{idMatricola}")
	public ResponseEntity<Matricola> findById(@PathVariable Long idMatricola){
		return ResponseEntity.ok().body(matricolaService.findById(idMatricola));
	}
	
	@Transactional
	@PutMapping("/classe/{idClasse}/{idAluno}")
	public ResponseEntity<Matricola> matricularAluno(@PathVariable Long idClasse, @PathVariable Long idAluno){
		return ResponseEntity.ok().body(matricolaService.matricular(idClasse, idAluno));
	}
	
	@Transactional
	@DeleteMapping("/{idMatricola}")
	public ResponseEntity<Void> deletar(@PathVariable Long idMatricola){
		matricolaService.dletar(idMatricola);
		return ResponseEntity.noContent().build();
	}
	
}
