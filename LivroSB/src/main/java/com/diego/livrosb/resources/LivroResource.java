package com.diego.livrosb.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diego.livrosb.entities.AnoMes;
import com.diego.livrosb.entities.Livro;
import com.diego.livrosb.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

	@Autowired
	private LivroService service;

	@GetMapping
	public ResponseEntity<List<Livro>> obterTodos() {
		List<Livro> list = service.obterTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> encontrarPeloId(@PathVariable Long id) {
		Livro obj = service.encontrarPeloId(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Livro> salvar(@RequestBody Livro obj) {
		obj = service.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluirPeloId(@PathVariable Long id) {
		service.excluirPeloId(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> Atualizar(@PathVariable Long id, @RequestBody Livro obj) {
		obj = service.atualizar(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> excluir(@RequestBody Livro obj) {
		service.excluir(obj);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/contarTodos")
	public ResponseEntity<Long> contarTodos() {
		Long cont = service.contarTodos();
		return ResponseEntity.ok().body(cont);
	}
	
	@GetMapping(value = "/estaVazio")
	public ResponseEntity<Boolean> estaVazio() {
		Boolean estaVazio = service.estaVazio();
		return ResponseEntity.ok().body(estaVazio);
	}
	
	@GetMapping(value = "/naoEstaVazio")
	public ResponseEntity<Boolean> naoEstaVazio() {
		Boolean naoEstaVazio = service.naoEstaVazio();
		return ResponseEntity.ok().body(naoEstaVazio);
	}
	
	@DeleteMapping(value = "/excluirTodos")
	public ResponseEntity<Void> excluirTodos() {
		service.excluirTodos();
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping(value = "/tituloContendo/{titulo}")
	public ResponseEntity<List<Livro>> obterTituloContendo(@PathVariable String titulo) {
		List<Livro> list = service.obterTituloContendo(titulo);
		return ResponseEntity.ok().body(list);
	}
	
//	@GetMapping(value = "/{ano}/{mes}")
//	public ResponseEntity<AnoMes> obterPublicadosEm(@PathVariable String ano, @PathVariable String mes) {
//		List<Livro> list = service.obterPublicadosEm();
//		return ResponseEntity.ok().body(list);
//	}
	
}
