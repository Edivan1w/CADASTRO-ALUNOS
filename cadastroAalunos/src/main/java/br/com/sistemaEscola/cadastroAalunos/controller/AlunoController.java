package br.com.sistemaEscola.cadastroAalunos.controller;

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

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;
import br.com.sistemaEscola.cadastroAalunos.service.AlunoService;
import br.com.sistemaEscola.cadastroAalunos.service.ClasseService;

@RestController
@RequestMapping("aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;
	@Autowired
	private ClasseService classeService;

	@GetMapping
	public ResponseEntity<Iterable<Aluno>> buscarTodos() {
		Iterable<Aluno> todos = alunoService.buscarTodos();
		return ResponseEntity.ok(todos);
	}

	@GetMapping("{id}")
	public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {
		Aluno aluno = alunoService.buscarPorId(id);
		return ResponseEntity.ok(aluno);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Aluno> salvar(@RequestBody DadosPessoais dadosPessoais) {

		Aluno aluno = alunoService.salvar(dadosPessoais);

		return ResponseEntity.ok(aluno);
	}
	
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @RequestBody AlunoDto dto) {
		alunoService.atualizar(id, dto);
		ResponseEntity.ok();
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		alunoService.deletar(id);
	}
	
	
	
	
}

