package com.diego.livrosb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.livrosb.entities.Categoria;
import com.diego.livrosb.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> obterTodos(){
		return repository.findAll();
	}
	
	public Categoria encontrarPeloId(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.get();
	}
	
}
