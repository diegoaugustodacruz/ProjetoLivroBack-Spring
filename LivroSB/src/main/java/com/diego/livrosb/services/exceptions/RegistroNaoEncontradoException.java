package com.diego.livrosb.services.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public RegistroNaoEncontradoException(Object id) {
		super("Recurso nao encontrado. Id: " + id);
	}

}
