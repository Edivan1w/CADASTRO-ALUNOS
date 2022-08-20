package br.com.sistemaEscola.cadastroAalunos.config.profile;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.sistemaEscola.cadastroAalunos.model.Role;
import br.com.sistemaEscola.cadastroAalunos.model.Usuario;
import br.com.sistemaEscola.cadastroAalunos.repository.RoleRepository;
import br.com.sistemaEscola.cadastroAalunos.repository.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Bean
	@Transactional
	public void startUser() {
		Role role = new Role();
		role.setNome("LEITURA_ESCRITA");
		roleRepository.save(role);
		
		Usuario usuario = new Usuario("Joao da silva", "joao@gmail.com", "$2a$10$Pj6pJ6FvlbdZcZRK6E/FO..FoVsVHbZOloWhXoQ1O47tdWN5jHEXC");
		usuario.getRole().add(role);
		usuarioRepository.save(usuario);
		
	}
	
}
