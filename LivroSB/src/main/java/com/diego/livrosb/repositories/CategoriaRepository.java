package com.diego.livrosb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.livrosb.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
