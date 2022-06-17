package br.com.sistemaEscola.cadastroAalunos.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaEscola.cadastroAalunos.model.Endereco;
import br.com.sistemaEscola.cadastroAalunos.service.imterface.ViaCepService;

@RestController("/endereco")
public class EnderecoService implements ViaCepService {

	@Override
	@GetMapping("")
	public Endereco consultarCep(@RequestBody String endereco) {
		return null;
	}

}
