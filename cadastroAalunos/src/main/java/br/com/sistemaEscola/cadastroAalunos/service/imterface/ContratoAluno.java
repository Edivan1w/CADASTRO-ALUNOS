package br.com.sistemaEscola.cadastroAalunos.service.imterface;





import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sistemaEscola.cadastroAalunos.dto.AlunoDto;
import br.com.sistemaEscola.cadastroAalunos.model.Aluno;
import br.com.sistemaEscola.cadastroAalunos.form.AlunoDadosForm;



public interface ContratoAluno {

	public Page<AlunoDto> buscarListaAlunos(String nome, Pageable pageable);
	public Aluno buscarPorId(Long id);
	public AlunoDto salvarAlunoComDados(Long id, AlunoDadosForm dados);
	public void atualizarCepEDados(Long id, AlunoDadosForm enderecoDados);
	public void deletarPorId(Long id);
}
