package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the ventas database table.
 * 
 */
@Entity
@Table(name = "ventas")
@NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String estado;

	private Timestamp fechaVenta;

	private String nombre;

	private Long numeroFactura;

	private String tipoVenta;

	private float totalVenta;

	// bi-directional many-to-one association to Cliente
	@JoinColumn(name = "idCliente")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Cliente cliente;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ventas")
	private List<Ventasproducto> ventasproductos;

	public Venta() {
	}

	public Venta(Timestamp fecha, Cliente cliente) {
		this.fechaVenta = fecha;
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaVenta() {
		return this.fechaVenta;
	}

	public void setFechaVenta(Timestamp fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getNumeroFactura() {
		return this.numeroFactura;
	}

	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getTipoVenta() {
		return this.tipoVenta;
	}

	public void setTipoVenta(String tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	public float getTotalVenta() {
		return this.totalVenta;
	}

	public void setTotalVenta(float totalVenta) {
		this.totalVenta = totalVenta;
	}

	public void setVentasProducto(List<Ventasproducto> ventasproductos) {
		this.ventasproductos = ventasproductos;
	}

	/**
	 * @return the detalles_venta
	 */
	public List<Ventasproducto> getVentasProducto() {
		return ventasproductos;
	}
}