package br.com.sistemaEscola.cadastroAalunos.dto;







import org.springframework.data.domain.Page;

import br.com.sistemaEscola.cadastroAalunos.model.Aluno;


public class AlunoDto {


	private Long id;
	private String nome;
	private String nomePai;
	private String nomeMae;
	private String cep;
	
	

	public AlunoDto(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getDadosPessoais().getNome();
		this.nomePai = aluno.getDadosPessoais().getNomePai();
		this.nomeMae = aluno.getDadosPessoais().getNomeMae();
		this.cep = aluno.getEndereco().getCep();
	}

	
	
	

	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	
	
	public static Page<AlunoDto> converterParaDto(Page<Aluno> alunos) {
		return alunos.map(AlunoDto::new);
	}
	

	
	
}
