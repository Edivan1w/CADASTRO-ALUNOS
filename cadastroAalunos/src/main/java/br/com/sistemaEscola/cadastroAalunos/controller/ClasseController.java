package br.com.sistemaEscola.cadastroAalunos.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaEscola.cadastroAalunos.dto.ClasseAlunoDto;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;

import br.com.sistemaEscola.cadastroAalunos.service.ClasseService;


@RestController
@RequestMapping("classe")
public class ClasseController {
	
	@Autowired
	private ClasseService service;
	
	@GetMapping
	public ResponseEntity<Iterable<Classe>> buscarPorTodasClasses(){
		Iterable<Classe> todos = service.buscarPorTodos();
		return ResponseEntity.ok(todos);

	}
	
	@PostMapping
	@Transactional
	public void salvar(@RequestBody String descricao) {
		Classe classe = new Classe();
		classe.setDescriçao(descricao);
		service.salvar(classe);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Classe> buscarPorId(@PathVariable Long id){
		Classe classe = service.buscarPorId(id);
		return ResponseEntity.ok(classe);
	}

	@PutMapping("{id}")
	@Transactional
	public void atualizar(@PathVariable Long id, @RequestBody String descricao) {
		Classe classe = service.buscarPorId(id);
		classe.setDescriçao(descricao);
		service.salvar(classe);
		ResponseEntity.ok(classe);
		
	}
	
	@PutMapping
	@Transactional
	public void inserirAluno(@RequestBody ClasseAlunoDto  dto) {
	    
		service.adicionarAluno(dto.getIdClasse(), dto.getIdAluno());
		ResponseEntity.ok();
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
		ResponseEntity.ok().build();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
