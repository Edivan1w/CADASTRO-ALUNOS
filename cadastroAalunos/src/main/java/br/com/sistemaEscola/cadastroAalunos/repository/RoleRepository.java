package br.com.sistemaEscola.cadastroAalunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemaEscola.cadastroAalunos.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
