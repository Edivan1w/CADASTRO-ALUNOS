package br.com.sistemaEscola.cadastroAalunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemaEscola.cadastroAalunos.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
