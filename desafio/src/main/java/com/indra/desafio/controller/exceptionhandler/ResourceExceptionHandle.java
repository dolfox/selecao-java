package com.indra.desafio.controller.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.indra.desafio.domain.exceptions.UsuarioExistenteException;
import com.indra.desafio.domain.exceptions.NegocioNaoEncontradoException;
import com.indra.desafio.domain.model.ErroDetalhado;

@ControllerAdvice
public class ResourceExceptionHandle extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(UsuarioExistenteException.class)
	public ResponseEntity<Object> handleUsuarioExistenteException(UsuarioExistenteException ex,
			WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		
		ErroDetalhado errorDetails = new ErroDetalhado();
		errorDetails.setStatus(status.value());
		errorDetails.setTitulo(ex.getMessage());
		errorDetails.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioNaoEncontradoException.class)
	public ResponseEntity<Object> handleUsuarioNaoEncontrado(NegocioNaoEncontradoException ex,
			WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ErroDetalhado errorDetails = new ErroDetalhado();
		errorDetails.setStatus(status.value());
		errorDetails.setTitulo(ex.getMessage());
		errorDetails.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ErroDetalhado.Campo> campos = new ArrayList<ErroDetalhado.Campo>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			campos.add(new ErroDetalhado.Campo(nome, mensagem));
		}

		ErroDetalhado errorDetails = new ErroDetalhado();
		errorDetails.setStatus(status.value());
		errorDetails.setTitulo("Um ou mais campos estão inválidos");
		errorDetails.setDataHora(OffsetDateTime.now());
		errorDetails.setCampos(campos);
		
		return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
	}
}
