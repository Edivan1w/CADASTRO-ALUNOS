package br.com.sistemaEscola.cadastroAalunos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{idAluno}")
	public ResponseEntity<Matricola> findById(@PathVariable Long idAluno){
		return ResponseEntity.ok().body(matricolaService.findById(idAluno));	
	}
	
	
	
}
