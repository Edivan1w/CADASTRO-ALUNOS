package br.com.sistemaEscola.cadastroAalunos.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.sistemaEscola.cadastroAalunos.model.Usuario;

public class UserDetail implements UserDetails{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	public UserDetail(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.usuario.getRole();
	}

	@Override
	public String getPassword() {
		return usuario.getPassWord();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
