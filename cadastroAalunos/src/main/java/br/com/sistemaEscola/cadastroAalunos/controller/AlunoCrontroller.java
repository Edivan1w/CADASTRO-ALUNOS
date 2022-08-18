package br.com.sistemaEscola.cadastroAalunos.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.dto.ClasseAlunoDto;
import br.com.sistemaEscola.cadastroAalunos.form.AlunoDadosForm;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoCrontroller {

	@Autowired
	private AlunoService alunoService;

	// buscar alunos no banco de dados usando o nome como parametro ou sem
	// parâmetro.
	@Cacheable(value = "listaDeAlunos")
	@GetMapping // parâmetro não obrigatório
	public Page<AlunoDto> buscarAlunos(@RequestParam(required = false) String nome,
			@PageableDefault(sort = "dadosPessoais_Nome", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
		// criar um objeto do tipo pageable para paginação
		// Pageable pageable = PageRequest.of(pagina, qtd, Direction.ASC, ordenacao);
		return alunoService.buscarListaAlunos(nome, pageable);
	}

	@GetMapping("/dados-matricola/{idAluno}")
	public ResponseEntity<ClasseAlunoDto> dadosDeMatricola(@PathVariable Long idAluno) {
			return ResponseEntity.ok(alunoService.buscarDadosMatricola(idAluno));
	}

	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	@PostMapping("/classe-matricola/{idClasse}")
	@Transactional
	public ResponseEntity<AlunoDto> salvarAlunoComDados(@PathVariable Long idClasse,
			@RequestBody @Valid AlunoDadosForm dados, UriComponentsBuilder builder) {
		AlunoDto alunoDto = alunoService.salvarAlunoComDados(idClasse, dados);
		Aluno aluno = alunoService.buscarPorId(alunoDto.getId());
		URI uri = builder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();

		return ResponseEntity.created(uri).body(alunoDto);
	}

	// Este método vai atribuir um endereço a um aluno, o metodo esta usando o
	// metodo consultar cep que esta consumindo uma api externa
	// Viacep. caso o endereço exista ele irá atribuir o endereço existente no banco
	// caso não exista ele irá consumir a API.
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	@PutMapping("/atualizar/endereco-dados/{id}")
	@Transactional
	public ResponseEntity<?> atualizarEnderecoEDadosPessoaisAluno(@PathVariable Long id,
			@RequestBody AlunoDadosForm enderecoDados) {
		alunoService.buscarPorId(id);
		alunoService.atualizarCepEDados(id, enderecoDados);
		return ResponseEntity.ok().build();
	}

	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		alunoService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
}
