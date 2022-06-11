package br.com.sistemaEscola.cadastroAalunos.repository;




import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.sistemaEscola.cadastroAalunos.model.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, String>{

	

	Optional<Endereco> findByCep(String cep);

}
