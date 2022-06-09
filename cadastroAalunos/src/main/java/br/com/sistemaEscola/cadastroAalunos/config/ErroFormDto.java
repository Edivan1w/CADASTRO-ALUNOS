package br.com.sistemaEscola.cadastroAalunos.config;

//essa classe vai representar o erro de valdação 
//e não será devolvido aquele em json erro gigante e sim um mais amigável
public class ErroFormDto {
	
	private String campo;
	private String erro;
	
	
	
	public ErroFormDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	
	

}
