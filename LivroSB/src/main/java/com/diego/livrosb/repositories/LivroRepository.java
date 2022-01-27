package com.diego.livrosb.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.livrosb.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	List<Livro> findByTituloContaining(String termo);


}
