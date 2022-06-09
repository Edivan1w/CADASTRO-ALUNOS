package br.com.sistemaEscola.cadastroAalunos.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Vai servir para todos os controllers
@RestControllerAdvice
public class ValidacaoHandler {
	
	//esta classe injeta a mensagem, e pega a mensagemm de erro de acordo com o idioma.
	private MessageSource messageSource;
	
	
	//para dizer para o spring para chamar esse método quando houver uma exeção dentro de algum controller
	//e dizer qula o tipo de exeção, no caso a de formulário. o metodo irá receber a classe como parâmetro
	//por padrao irá devolver o status 200 e por isso tem que anotar para receber o 400.
	//o método tem que devolver uma lista de erro.
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormDto> handle(MethodArgumentNotValidException exception) {
		List<ErroFormDto> dto = new ArrayList<>();
		//é o resultado das valições e pegar todos os eros de formulários
		List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
		fieldError.forEach(e -> {                               //para gerar a mesagem de acordo com o local
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroFormDto erro = new ErroFormDto(e.getField(), mensagem);
			dto.add(erro);
		});
		 return dto;
	}

}
