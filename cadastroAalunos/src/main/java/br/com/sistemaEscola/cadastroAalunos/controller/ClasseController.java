package br.com.sistemaEscola.cadastroAalunos.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sistemaEscola.cadastroAalunos.dto.ClasseDto;
import br.com.sistemaEscola.cadastroAalunos.form.ClasseForm;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.service.ClasseService;

@RestController
@RequestMapping("/classe")
public class ClasseController {

	@Autowired
	private ClasseService service;

	@GetMapping
	public ResponseEntity<List<ClasseDto>> buscarPorTodasClasses() {

		return ResponseEntity.ok(service.buscarPorTodos());
	}

	@PostMapping
	public ResponseEntity<ClasseDto> salvar(@RequestBody @Valid ClasseForm classeForm, UriComponentsBuilder builder) {
		try {
			ClasseDto dto = service.salvar(classeForm);
			System.out.println("chegou aqui");
			URI uri = builder.path("/classe/{id}").buildAndExpand(dto.getIdClasse()).toUri();
			return ResponseEntity.created(uri).body(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
			}
		
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Classe> buscarPorId(@PathVariable Long id) {
		Classe classe = service.buscarPorId(id);
		return ResponseEntity.ok(classe);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Classe> atualizar(@PathVariable Long id, @RequestBody String descricao) {
		Classe classe = service.buscarPorId(id);
		classe.setDescricao(descricao);
		return ResponseEntity.ok(classe);

	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
		ResponseEntity.ok().build();

	}

}
