package br.com.sistemaEscola.cadastroAalunos;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@EnableFeignClients
@EnableSpringDataWebSupport
@SpringBootApplication
@EnableCaching
public class CadastroAalunosApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(CadastroAalunosApplication.class, args);
		
        
		
	}

}
