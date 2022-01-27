package com.diego.livrosb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.diego.livrosb.entities.Livro;
import com.diego.livrosb.repositories.LivroRepository;
import com.diego.livrosb.services.exceptions.DatabaseException;
import com.diego.livrosb.services.exceptions.RegistroNaoEncontradoException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;
	
	public List<Livro> obterTodos(){
		return repository.findAll();
	}
	
	public Livro encontrarPeloId(Long id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new RegistroNaoEncontradoException(id));
	}
	
	public Livro salvar(Livro obj) {
		return repository.save(obj);
	}
	
	public void excluirPeloId(Long id) {
		try {
			repository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Livro atualizar(Long id, Livro obj) {
		Livro entity = repository.getOne(id);
		atualizarDados(entity, obj);
		return repository.save(entity);
	}

	private void atualizarDados(Livro entity, Livro obj) {
		entity.setTitulo(obj.getTitulo());
		entity.setNumeroDePaginas(obj.getNumeroDePaginas());
		entity.setPublicadoEm(obj.getPublicadoEm());
		entity.setPrecoDeVenda(obj.getPrecoDeVenda());
	}
	
}
