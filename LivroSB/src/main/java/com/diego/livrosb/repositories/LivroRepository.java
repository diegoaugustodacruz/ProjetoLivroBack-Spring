package com.diego.livrosb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.livrosb.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
