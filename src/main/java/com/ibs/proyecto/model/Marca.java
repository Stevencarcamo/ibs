package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the marcas database table.
 * 
 */
@Entity
@Table(name="marcas")
@NamedQuery(name="Marca.findAll", query="SELECT m FROM Marca m")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idMarca;

	private String nombreMarca;

	public Marca() {
	}

	public Marca(String nombreMarca) {
		super();
		this.nombreMarca = nombreMarca;
	}

	public Marca(Long idMarca, String nombreMarca) {
		this.idMarca = idMarca;
		this.nombreMarca = nombreMarca;
	}

	public Long getIdMarca() {
		return this.idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public String getNombreMarca() {
		return this.nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

}