package br.com.sistemaEscola.cadastroAalunos.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.sistemaEscola.cadastroAalunos.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServ {

	@Value("${forum.jwt.secret}")
	private String secret;

	@Value("${forum.jwt.expiration}")
	private String expiration;

	public String gerarToken(Authentication authentication) {
 
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		Date hoje = new Date();
		
		return Jwts
				.builder()
				.setIssuer("ESCOLA API")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isValido(String token) {
		
		try {
			 Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			 return true;
		} catch (Exception e) {
             return false;
		}
   }

	public Long getIdUsuario(String token) {
		// TODO Auto-generated method stub
		 Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		 return Long.parseLong(body.getSubject());
		 
	}
	
	
}
