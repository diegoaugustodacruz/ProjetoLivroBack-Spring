package com.diego.livrosb.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.diego.livrosb.services.exceptions.DatabaseException;
import com.diego.livrosb.services.exceptions.RegistroNaoEncontradoException;

@ControllerAdvice
public class RegistroManualException {

	@ExceptionHandler(RegistroNaoEncontradoException.class)
	public ResponseEntity<ErroPadrao> recursoNaoEncontrado(RegistroNaoEncontradoException e,
			HttpServletRequest request) {
		String error = "Recurso nao encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);

	};
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<ErroPadrao> database(DatabaseException e,
			HttpServletRequest request) {
		String error = "Erro no banco de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);

	};

}
