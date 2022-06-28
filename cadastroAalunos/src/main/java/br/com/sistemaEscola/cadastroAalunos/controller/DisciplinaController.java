package br.com.sistemaEscola.cadastroAalunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDadosEscolaresDto;
import br.com.sistemaEscola.cadastroAalunos.form.FormPreenchimentoNota;
import br.com.sistemaEscola.cadastroAalunos.service.AlunoService;
import br.com.sistemaEscola.cadastroAalunos.service.DisciplinaService;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

	@Autowired
	private DisciplinaService disciplinaService;
	@Autowired
	private AlunoService alunoService;

	@GetMapping("/{idAluno}")
	public ResponseEntity<List<AlunoDadosEscolaresDto>> buscarDisciplinasPorAluno(@PathVariable Long idAluno) {
		if (!alunoService.bucarOptionalAluno(idAluno).isPresent()) {
               return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(disciplinaService.buscarDisciplinaaPorAluno(idAluno));
	}

	@PostMapping("/{idAluno}")
	@Transactional
	public ResponseEntity<AlunoDadosEscolaresDto> cadastrarMateriaNoPerfilAluno(@PathVariable Long idAluno, @RequestParam String nomeDisciplina) {
		try {
			return ResponseEntity.ok(disciplinaService.incluirDisciplina(idAluno, nomeDisciplina));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{idAluno}")
	@Transactional
	public ResponseEntity<AlunoDadosEscolaresDto> cadastrarOuAtualizarNotas(@PathVariable Long idAluno,
			@RequestBody FormPreenchimentoNota formDisciplina) {
		try {
			AlunoDadosEscolaresDto dto = new AlunoDadosEscolaresDto(alunoService.buscarPorId(idAluno),
					disciplinaService.cadastrarNotaBimestre(idAluno, formDisciplina));
			return ResponseEntity.ok(dto);
			
		} catch (Exception e) {
		    return ResponseEntity.badRequest().build();
		}
		
	}

}
