package br.com.sistemaEscola.cadastroAalunos.service.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import br.com.sistemaEscola.cadastroAalunos.model.Endereco;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

	@RequestMapping("/{cep}/json/")
	Endereco consultarCep(@PathVariable("cep") String endereco);
}
