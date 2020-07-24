package com.indra.desafio.domain.exceptions;

public class UsuarioExistenteException extends RuntimeException {

	private static final long serialVersionUID = 9194043626684081918L;
	
	public UsuarioExistenteException(String mensagem) {
		super(mensagem);
	}

}
