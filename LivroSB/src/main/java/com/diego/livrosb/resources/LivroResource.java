package com.diego.livrosb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.livrosb.entities.Livro;
import com.diego.livrosb.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {
	
	@Autowired
	private LivroService service;
	
	@GetMapping
	public ResponseEntity<List<Livro>> obterTodos(){
		List<Livro> list = service.obterTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> encontrarPeloId(@PathVariable Long id){
		Livro obj = service.encontrarPeloId(id);
		return ResponseEntity.ok().body(obj);
	}
}
