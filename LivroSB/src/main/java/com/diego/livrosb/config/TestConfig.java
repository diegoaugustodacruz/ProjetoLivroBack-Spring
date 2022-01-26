package com.diego.livrosb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.diego.livrosb.entities.Livro;
import com.diego.livrosb.repositories.LivroRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date d = new Date();


	@Autowired
	private LivroRepository livroRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Livro l1 = new Livro(null, "Harry Potter", 200, sdf.parse("25/01/2000"), 200.00);
		Livro l2 = new Livro(null, "Senhor dos Anéis", 300, sdf.parse("27/04/2004"), 300.00);
		Livro l3 = new Livro(null, "Sapiens", 400, sdf.parse("05/06/2011"),400.00);
		Livro l4 = new Livro(null, "O Hobbit", 500, sdf.parse("08/09/2009"),500.00);
		Livro l5 = new Livro(null, "The Witcher", 600, sdf.parse("06/07/2015"), 600.00);

		livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
	}
	
	
	
}
