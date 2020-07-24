package com.indra.desafio.domain.exceptions;

public class NegocioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 4135178589974813239L;
	
	public NegocioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
