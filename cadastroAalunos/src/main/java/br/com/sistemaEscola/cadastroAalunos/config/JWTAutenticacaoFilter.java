package br.com.sistemaEscola.cadastroAalunos.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sistemaEscola.cadastroAalunos.model.Usuario;
import br.com.sistemaEscola.cadastroAalunos.repository.UsuarioRepository;
import br.com.sistemaEscola.cadastroAalunos.service.TokenServ;

public class JWTAutenticacaoFilter extends OncePerRequestFilter{


	private TokenServ tokenServ;
	private UsuarioRepository usuarioRepository;
	
	public JWTAutenticacaoFilter(TokenServ tokenServ, UsuarioRepository usuarioRepository) {
		this.tokenServ = tokenServ;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);

		boolean valido = tokenServ.isValido(token); 
		if(valido) {
			autenticarCliete(token);
		}
		filterChain.doFilter(request, response);
		
	}

	private void autenticarCliete(String token) {
		
        Long idUsuario = tokenServ.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authorization = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authorization);
		
	}

	private String recuperarToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
			return null;
		}
		return header.substring(7, header.length());
	}
	
	
	
	
}
