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
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Disciplina;
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
	public ResponseEntity<AlunoDadosEscolaresDto> cadastrarOuAtualizarNotas(@PathVariable Long idAluno, @RequestBody FormPreenchimentoNota formDisciplina) {
		AlunoDadosEscolaresDto dto = new AlunoDadosEscolaresDto(alunoService.buscarPorId(idAluno),
				disciplinaService.cadastrarNotaPimeiroBimestre(idAluno, formDisciplina));
		return ResponseEntity.ok(dto);
	}
	
	
}
