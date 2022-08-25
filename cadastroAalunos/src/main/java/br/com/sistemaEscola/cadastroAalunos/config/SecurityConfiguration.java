package br.com.sistemaEscola.cadastroAalunos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.sistemaEscola.cadastroAalunos.repository.UsuarioRepository;
import br.com.sistemaEscola.cadastroAalunos.service.TokenServ;
import br.com.sistemaEscola.cadastroAalunos.service.UsuarioService;

@Configuration
@EnableWebSecurity
@Profile(value = {"prod","test"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TokenServ tokenServ;
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		                        //qualquer requisição
		http
		.authorizeRequests()
		.antMatchers("/aluno/**").hasAuthority("LEITURA_ESCRITA")
		.antMatchers(HttpMethod.POST ,"/auth").permitAll()
		.antMatchers(HttpMethod.GET ,"/actuator/**").permitAll()
		.antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
		.antMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
		.antMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
		.anyRequest()
		//que está autenticada
		.authenticated()
		.and()                     //gaudar nenhum estatdo
		.csrf().disable()
		.addFilterBefore(new JWTAutenticacaoFilter( tokenServ, usuarioRepository), UsernamePasswordAuthenticationFilter.class)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}

	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}
	
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("123456"));
//	}
	
}
