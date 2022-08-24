package br.com.sistemaEscola.cadastroAalunos.config.profile;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.sistemaEscola.cadastroAalunos.config.profile.service.TestConfigService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private TestConfigService testConfigService;
	
	@Bean
	@Transactional
	public void start() {
		this.testConfigService.startUser();
		this.testConfigService.startAluno();
	}
}
