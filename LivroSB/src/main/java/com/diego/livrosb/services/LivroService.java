package com.diego.livrosb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.diego.livrosb.entities.AnoMes;
import com.diego.livrosb.entities.Livro;
import com.diego.livrosb.repositories.LivroRepository;
import com.diego.livrosb.services.exceptions.DatabaseException;
import com.diego.livrosb.services.exceptions.RegistroNaoEncontradoException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;

	public List<Livro> obterTodos() {
		return repository.findAll();
	}

	public Livro encontrarPeloId(Long id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new RegistroNaoEncontradoException(id));
	}

	@Transactional
	public Livro salvar(Livro obj) {
		return repository.save(obj);
	}

	@Transactional
	public void excluirPeloId(Long id) {
		try {
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public Livro atualizar(Long id, Livro obj) {
		try {
			Livro entity = repository.getOne(id);
			atualizarDados(entity, obj);
			return repository.save(entity);

		} catch (EntityNotFoundException e) {
			throw new RegistroNaoEncontradoException(id);
		}
	}
	
	@Transactional
	private void atualizarDados(Livro entity, Livro obj) {
		entity.setTitulo(obj.getTitulo());
		entity.setNumeroDePaginas(obj.getNumeroDePaginas());
		entity.setPublicadoEm(obj.getPublicadoEm());
		entity.setPrecoDeVenda(obj.getPrecoDeVenda());
	}
	
	@Transactional
	public void excluir(Livro obj) {
		try {
			repository.delete(obj);
			;

		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException(obj.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Long contarTodos() {
		return repository.count();
	}

	public Boolean estaVazio() {
		if (repository.count() >= 1) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean naoEstaVazio() {
		if (repository.count() >= 1) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public void excluirTodos() {
		repository.deleteAll();

	}

	public List<Livro> obterTituloContendo(String termo) {
		List<Livro> obj = repository.findByTituloContaining(termo);
		return obj;
	}

	public List<Livro> obterPublicadosEm(AnoMes anoMes) {

		List<Livro> banco = repository.findAll();

		List<Livro> novaLista = new ArrayList<>();

		String ano = anoMes.getAno();
		String mes = anoMes.getMes();
		String anoMesFormatado = ano + "/" + mes;

		for (Livro c : banco) {
			if (anoMesFormatado.equalsIgnoreCase(c.getAnoMesDePublicacao().toString())) {
				novaLista.add(c);
			}
		}

		return novaLista;
	}

	public List<Livro> obterPublicadosEm(AnoMes[] anoMes) {

		List<Livro> banco = repository.findAll();

		List<Livro> novaLista = new ArrayList<>();

		for(AnoMes c: anoMes) {
			
			String ano = c.getAno();
			String mes = c.getMes();
			String anoMesFormatado = ano + "/" + mes;

			for (Livro b : banco) {
				if (anoMesFormatado.equalsIgnoreCase(b.getAnoMesDePublicacao().toString())) {
					novaLista.add(b);
				}
			}
		}

		return novaLista;
	}
}
