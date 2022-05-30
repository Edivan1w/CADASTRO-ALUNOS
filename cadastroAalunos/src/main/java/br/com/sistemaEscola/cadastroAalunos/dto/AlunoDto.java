package br.com.sistemaEscola.cadastroAalunos.dto;



import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.model.Classe;
import br.com.sistemaEscola.cadastroAalunos.model.DadosPessoais;
import br.com.sistemaEscola.cadastroAalunos.model.Endereco;
import br.com.sistemaEscola.cadastroAalunos.repository.AlunoReposiry;

public class AlunoDto {

	private Classe classe;
	
	private DadosPessoais dadosPessoais;
	
	private Endereco endereco;
	
	
	
	public AlunoDto() {}
	

	public AlunoDto(Aluno aluno) {
		super();
		this.classe = aluno.getClasse();
		this.dadosPessoais = aluno.getDadosPessoais();
		this.endereco = aluno.getEndereco();
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Aluno atualizarAluno(Long id, AlunoReposiry alunoReposiry) {
		Aluno aluno = alunoReposiry.findById(id).get();
		aluno.setClasse(this.classe);
		aluno.setDadosPessoais(this.dadosPessoais);
		aluno.setEndereco(this.endereco);
		return aluno;
	}
	
}
