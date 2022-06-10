package br.com.sistemaEscola.cadastroAalunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sistemaEscola.cadastroAalunos.model.Classe;
@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

}
