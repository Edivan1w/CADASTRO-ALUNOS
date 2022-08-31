package br.com.sistemaEscola.cadastroAalunos.config.profile.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;
import br.com.sistemaEscola.cadastroAalunos.model.Disciplina;
import br.com.sistemaEscola.cadastroAalunos.model.Endereco;
import br.com.sistemaEscola.cadastroAalunos.model.Matricola;
import br.com.sistemaEscola.cadastroAalunos.model.NivelClasse;
import br.com.sistemaEscola.cadastroAalunos.model.NomeDisciplinas;
import br.com.sistemaEscola.cadastroAalunos.model.Role;
import br.com.sistemaEscola.cadastroAalunos.model.Usuario;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;
import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;
import br.com.sistemaEscola.cadastroAalunos.repository.DisciplinasRepository;
import br.com.sistemaEscola.cadastroAalunos.repository.EnderecoRepository;
import br.com.sistemaEscola.cadastroAalunos.repository.MatricolaRepository;
import br.com.sistemaEscola.cadastroAalunos.repository.RoleRepository;
import br.com.sistemaEscola.cadastroAalunos.repository.UsuarioRepository;

@Service
public class TestConfigService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AlunoReposiry alunoReposiry;
	@Autowired
	private ClasseRepository classeRepository;
	@Autowired
	private DisciplinasRepository disciplinasRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private MatricolaRepository matricolaRepository;
	
	public void startUser() {
		Role role = new Role();
		role.setNome("LEITURA_ESCRITA");
		roleRepository.save(role);
		
		Usuario usuario = new Usuario("Joao da silva", "joao@gmail.com", "$2a$10$Pj6pJ6FvlbdZcZRK6E/FO..FoVsVHbZOloWhXoQ1O47tdWN5jHEXC");
		usuario.getRole().add(role);
		usuarioRepository.save(usuario);		
	}
	public void startAluno() {
		Disciplina disciplina =disciplinasRepository.save( new Disciplina(NomeDisciplinas.GEOGRAFIA, null, null, null, null, null, null));
		
		DadosPessoais dadosPessoais = new DadosPessoais();
		dadosPessoais.setNome("Marcos Almeida");
		dadosPessoais.setCpf("655.552.580-03");
		dadosPessoais.setNomePai("Osvaldo Almeida");
		dadosPessoais.setNomeMae("Leticia Almeida");
		
		Endereco endereco = enderecoRepository.save(new Endereco("76919000", "Rua Oscar", "Norte", "Alvorada", "Cacoal", "RO", null, null, "69", null));
		
		Classe classe = classeRepository.save(new Classe("Primeiro ano 'c'", NivelClasse.PRIMEIRO_ANO_ENSINO_MEDIO));
		
		Aluno aluno = new Aluno(classe, dadosPessoais, endereco, Arrays.asList(disciplina));
		disciplina.setAluno(alunoReposiry.save(aluno));
		
		matricolaRepository.save(new Matricola(classe, aluno));
		
	}
}
