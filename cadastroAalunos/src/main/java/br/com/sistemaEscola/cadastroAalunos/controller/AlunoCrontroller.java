package br.com.sistemaEscola.cadastroAalunos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import br.com.sistemaEscola.cadastroAalunos.form.AlunoDadosParaCadastrar;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;

import br.com.sistemaEscola.cadastroAalunos.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoCrontroller {

	@Autowired
	private AlunoService alunoService;

	// buscar alunos no banco de dados usando o nome como parametro ou sem
	// parâmetro.
	@GetMapping
	public ResponseEntity<List<AlunoDto>> buscarAlunos(String nome) {
		if (nome == null) {
			List<AlunoDto> list = alunoService.buscarListaAlunos();
			return ResponseEntity.ok(list);
		} else {
			List<AlunoDto> list = alunoService.buscarPorNome(nome);
			return ResponseEntity.ok(list);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(alunoService.buscarPorId(id));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AlunoDto> salvarAlunoComDados(@RequestParam Long id,
 //com o valid spring reconhecer e gerar um erro. O spring vai varrer as anotações do Bean validation e gerar o erro 400.
			// o spring irá ijetar
			@RequestBody @Valid AlunoDadosParaCadastrar dados, UriComponentsBuilder builder) {
		return alunoService.salvarAlunoComDados(id, dados, builder);

	}

	@PutMapping(value = "{id}")
	public void atualizarDadosPessoais(@PathVariable Long id, @RequestBody DadosPessoais dadosPessoais) {
		alunoService.atualizarDadosPessoais(id, dadosPessoais);
	}

	// Este método vai atribuir um endereço a um aluno, o metodo esta usando o
	// metodo consultar cep que esta consumindo uma api externa
	// Viacep. caso o endereço exista ele irá atribuir o endereço existente no banco
	// caso não exista ele irá consumir a API.
	@PutMapping("/atualizar/endereco")
	@Transactional
	public ResponseEntity<AlunoDto> atualizarEnderecoAluno(@RequestParam Long id,
			@RequestBody AlunoDadosParaCadastrar enderecoCep) {
		return alunoService.atualizarEnderecoAluno(id, enderecoCep);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deletar(@PathVariable Long id) {
		alunoService.deletarPorId(id);
		ResponseEntity.ok().build();
	}

}
