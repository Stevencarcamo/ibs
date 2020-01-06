package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the categorias database table.
 * 
 */
@Entity
@Table(name="categorias")
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCategoria;

	private String nombreCategoria;
	
	private String codigoCategoria;

	public Categoria() {
	}

	public Categoria(String nombreCategoria, String codigoCategoria) {
		this.nombreCategoria = nombreCategoria;
		this.codigoCategoria = codigoCategoria;
	}
	
	

	public Categoria(Long idCategoria, String nombreCategoria, String codigoCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.codigoCategoria = codigoCategoria;
	}

	public Long getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCodigoCategoria() {
		return this.codigoCategoria;
	}

	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public String getNombreCategoria() {
		return this.nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

}