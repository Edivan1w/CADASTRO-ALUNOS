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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaEscola.cadastroAalunos.dto.ClasseAlunoDto;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;
import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.service.ClasseService;


@RestController
@RequestMapping("classe")
public class ClasseController {
	
	@Autowired
	private ClasseService service;
	@Autowired
	private AlunoReposiry alunoReposiry;
	@Autowired
	private ClasseRepository classeRepository;
	
	@GetMapping
	public ResponseEntity<Iterable<Classe>> buscarPorTodasClasses(){
		Iterable<Classe> todos = service.buscarPorTodos();
		return ResponseEntity.ok(todos);

	}
	
	@PostMapping
	@Transactional
	public void salvar(@RequestBody String descricao) {
		Classe classe = new Classe();
		classe.setDescricao(descricao);
		service.salvar(classe);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Classe> buscarPorId(@PathVariable Long id){
		Classe classe = service.buscarPorId(id);
		return ResponseEntity.ok(classe);
	}

	@PutMapping("/{id}")
	@Transactional
	public void atualizar(@PathVariable Long id, @RequestBody String descricao) {
		Classe classe = service.buscarPorId(id);
		classe.setDescricao(descricao);
		service.salvar(classe);
		ResponseEntity.ok(classe);
		
	}
	
//	@PostMapping("/matricula")
//	public void matricularAluno(@RequestParam Long idClasse, @RequestParam Long idAluno) {
//		
//		service.matricularAluno(idClasse, idAluno);
//		ResponseEntity.ok();
//	}
	
	
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
		ResponseEntity.ok().build();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
