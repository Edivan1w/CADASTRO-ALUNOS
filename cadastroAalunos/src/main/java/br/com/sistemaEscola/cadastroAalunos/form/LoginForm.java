package br.com.sistemaEscola.cadastroAalunos.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String email;
	private String passWord;
	
	
	
	public String getEmail() {
		return email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, passWord);
	}
	
	
}
