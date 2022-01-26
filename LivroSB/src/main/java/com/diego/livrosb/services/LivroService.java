package com.diego.livrosb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.livrosb.entities.Livro;
import com.diego.livrosb.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;
	
	public List<Livro> obterTodos(){
		return repository.findAll();
	}
	
	public Livro encontrarPeloId(Long id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.get();
	}
	
	public Livro salvar(Livro obj) {
		return repository.save(obj);
	}
	
}
