package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ventasproductos database table.
 * 
 */
@Entity
@Table(name = "ventasproductos")
@NamedQuery(name = "Ventasproducto.findAll", query = "SELECT v FROM Ventasproducto v")
public class Ventasproducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVentasproductos;

	private Long cantidad;

	private float descuento;

	// bi-directional many-to-one association to Producto
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="idProducto") private Producto productos;
	 */

	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto productos;

	// bi-directional many-to-one association to Venta

	@JoinColumn(name= "venta_id")
	@ManyToOne(cascade = CascadeType.REMOVE, optional = false, fetch = FetchType.EAGER)
	private Venta ventas;

	public Ventasproducto() {
	}

	public Ventasproducto(Venta ventas, Producto producto, Long cantidad, float descuento) {
		this.ventas = ventas;
		this.productos = producto;
		this.cantidad = cantidad;
		this.descuento = descuento;
	}

	public Long getIdVentasproductos() {
		return this.idVentasproductos;
	}

	public void setIdVentasproductos(Long idVentasproductos) {
		this.idVentasproductos = idVentasproductos;
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public float getDescuento() {
		return this.descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public Venta getVentas() {
		return this.ventas;
	}

	public void setVentas(Venta ventas) {
		this.ventas = ventas;
	}

	public Producto getProductos() {
		return this.productos;
	}

	public void setProductos(Producto productos) {
		this.productos = productos;
	}
}