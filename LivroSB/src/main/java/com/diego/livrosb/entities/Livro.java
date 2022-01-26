package com.diego.livrosb.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_livro")
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	private Integer numeroDePaginas;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
	private Date publicadoEm;

	private Double precoDeVenda;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	public Livro() {

	}

	public Livro(Long id) {
		this.id = -1L;
	}

	public Livro(Long id, String titulo, Integer numeroDePaginas, Date publicadoEm, Double precoDeVenda,
			Categoria categoria) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.numeroDePaginas = numeroDePaginas;
		this.publicadoEm = publicadoEm;
		this.precoDeVenda = precoDeVenda;
		this.categoria = categoria;
	}

	public Livro(String titulo, Integer numeroDePaginas, Date publicadoEm, Double precoDeVenda) {
		super();
		this.titulo = titulo;
		this.numeroDePaginas = numeroDePaginas;
		this.publicadoEm = publicadoEm;
		this.precoDeVenda = precoDeVenda;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

	public Date getPublicadoEm() {
		return publicadoEm;
	}

	public void setPublicadoEm(Date publicadoEm) {
		this.publicadoEm = publicadoEm;
	}

	public Double getPrecoDeVenda() {
		return precoDeVenda;
	}

	public void setPrecoDeVenda(Double precoDeVenda) {
		this.precoDeVenda = precoDeVenda;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getAnoMesDePublicacao() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		String dataFormatada = sdf.format(publicadoEm);

		String ano = dataFormatada.substring(6, 10);

		String mes = dataFormatada.substring(3, 5);

		return ano + "/" + mes;

	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}

}
