package br.com.sistemaEscola.cadastroAalunos.controller;


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
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.AlunoDadosParaCadastrar;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;
import br.com.sistemaEscola.cadastroAalunos.model.Endereco;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;
import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.repository.EnderecoRepository;
import br.com.sistemaEscola.cadastroAalunos.service.AlunoService;
import br.com.sistemaEscola.cadastroAalunos.service.impl.ViaCepService;


@RestController
@RequestMapping("/aluno")
public class AlunoCrontroller {
	
	@Autowired
	private AlunoReposiry alunoReposiry;
	@Autowired
    private ClasseRepository classeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService cepService;
	@Autowired
	private AlunoService alunoService;
	
	//buscar alunos no banco de dados usando o nome como parametro ou sem parâmetro.
	@GetMapping
	public ResponseEntity<List<AlunoDto>>buscarAlunos(String nome){
		if(nome == null) {
			List<AlunoDto> list = alunoService.buscarListaAlunos();
			return ResponseEntity.ok(list);
		}else {
			List<AlunoDto> list = alunoService.buscarPorNome(nome);
			return ResponseEntity.ok(list);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Aluno>buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(alunoReposiry.findById(id).get());
	}
	
	@PostMapping
	@Transactional
	public void salvarAluno(@RequestParam Long id, @RequestBody DadosPessoais dadosPessoais) {
		Classe classe = classeRepository.findById(id).get();
		Aluno aluno = new Aluno();
	    
	    aluno.setDadosPessoais(dadosPessoais);
		aluno.setClasse(classe);
		
		classeRepository.save(classe);
		alunoReposiry.save(aluno);
	}
	
//	@PostMapping
//	@Transactional
//	public void salvarAlunoComDados(@RequestParam Long id, @RequestBody AlunoDadosParaCadastrar dados) {
//		Classe classe = classeRepository.findById(id).get();
//		String cep = dados.getCep();
//		
//		Aluno aluno = new Aluno();
//		
//		aluno.setClasse(classe);
//	    List<Aluno> alunos = classe.getAlunos();
//		alunos.add(aluno);
//		classeRepository.save(classe);
//		alunoReposiry.save(aluno);
//	}
//	
	
	
	@PutMapping(value = "{id}")
	public void atualizarDadosPessoais(@PathVariable Long id, @RequestBody DadosPessoais dadosPessoais) {
		Aluno aluno = alunoReposiry.findById(id).get();
		aluno.setDadosPessoais(dadosPessoais);
	}

	
	//Este método vai atribuir um endereço a um aluno, o metodo esta usando o metodo consultar cep que esta consumindo uma api externa
	// Viacep. caso o endereço exista ele irá atribuir o endereço existente no banco caso não exista ele irá consumir a API.
	@PostMapping("/endereco")
    @Transactional
	public void salvarEndereco(@RequestParam Long id, @RequestBody Endereco endereco) {
		
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		
		System.out.println(endereco.getCep());
		
		
		if(enderecoRepository.existsById(endereco.getCep())) {
			
			System.out.println("1");
			salvarComEnderecoExistete(id, endereco);
			ResponseEntity.ok();
			return;
		}else {
			System.out.println("2");
			
			salvarComEnderecoNaoExistete(id, endereco);
			ResponseEntity.ok();
			return;
		}
	}	
	

	public void salvarComEnderecoExistete(Long id, Endereco endereco) {
		Aluno aluno = alunoReposiry.findById(id).get();
		Endereco endereco2 = enderecoRepository.findById(endereco.getCep()).get();
		aluno.setEndereco(endereco2);

	}
	
	public void salvarComEnderecoNaoExistete(Long id, Endereco endereco) {
		Aluno aluno = alunoReposiry.findById(id).get();
	
		String cep = endereco.getCep();
		Endereco novoEndereco = cepService.consultarCep(cep);
		
		System.out.println(novoEndereco.getCep());
		
		aluno.setEndereco(novoEndereco);
		
	    
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deletar(@PathVariable Long id) {
		alunoService.deletarPorId(id);
		ResponseEntity.ok().build();
	}

	
		

}



















