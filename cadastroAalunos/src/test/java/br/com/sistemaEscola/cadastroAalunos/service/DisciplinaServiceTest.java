package br.com.sistemaEscola.cadastroAalunos.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import br.com.sistemaEscola.cadastroAalunos.dto.ClasseDto;
import br.com.sistemaEscola.cadastroAalunos.form.ClasseForm;
import br.com.sistemaEscola.cadastroAalunos.repository.ClasseRepository;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("disciplina")
class DisciplinaServiceTest {
	
	@MockBean
	private ClasseRepository classeRepository;
	
	@Autowired
	private ClasseService classeService;

	
	
	@Test
	public void deveSalvarEDevolverUmaClasseDto() {
		ClasseForm classeForm = new ClasseForm("Primeiro ano", "");
		ClasseDto dto = classeService.salvar(classeForm);
		Mockito.when(classeService.salvar(classeForm)).thenReturn(dto);
		
	}

}
