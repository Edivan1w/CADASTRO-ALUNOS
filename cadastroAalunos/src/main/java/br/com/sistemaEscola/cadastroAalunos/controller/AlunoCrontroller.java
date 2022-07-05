package br.com.sistemaEscola.cadastroAalunos.controller;

import java.net.URI;

import java.util.Optional;

import javax.naming.directory.NoSuchAttributeException;
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
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.form.AlunoDadosForm;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;
import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoCrontroller {

	@Autowired
	private AlunoService alunoService;
	@Autowired
	private AlunoReposiry alunoReposiry;
	@Autowired
	private ClasseRepository classeRepository;

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
	public ResponseEntity<ClasseAlunoDto> dadosDeMatricola(@PathVariable Long idAluno){
		try {
			return ResponseEntity.ok(alunoService.buscarDadosMatricola(idAluno));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}

	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	@PostMapping("/classe-matricola/{id}")
	@Transactional
	public ResponseEntity<AlunoDto> salvarAlunoComDados(@PathVariable Long id, @RequestBody @Valid AlunoDadosForm dados,
			UriComponentsBuilder builder) {
		Optional<Classe> optional = classeRepository.findById(id);
		if (optional.isPresent()) {
			AlunoDto alunoDto = alunoService.salvarAlunoComDados(id, dados);
			Aluno aluno = alunoService.buscarPorId(alunoDto.getId());
			URI uri = builder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
			// é uma boa prática devolver o codgo 201 através do metodo created e
			// representar o que foi criado
			// devolver a uri e o corpo.
			return ResponseEntity.created(uri).body(alunoDto);
		}

		return ResponseEntity.notFound().build();
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
		Optional<Aluno> optional = alunoReposiry.findById(id);
		if (optional.isPresent()) {
			alunoService.atualizarCepEDados(id, enderecoDados);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Aluno> optional = alunoReposiry.findById(id);
		if (optional.isPresent()) {
			alunoService.deletarPorId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
