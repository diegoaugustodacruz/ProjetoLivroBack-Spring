package com.diego.livrosb.resources;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.livrosb.entities.Livro;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {
	
	@GetMapping
	public ResponseEntity<Livro> buscarTodos(){
		Livro l = new Livro(1L, "Harry Potter", 200, new Date(), 200.00);
		return ResponseEntity.ok().body(l);
	}

}
