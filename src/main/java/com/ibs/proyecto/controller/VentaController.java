package com.ibs.proyecto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibs.proyecto.model.Cliente;
import com.ibs.proyecto.model.Producto;
import com.ibs.proyecto.model.Venta;
import com.ibs.proyecto.model.Ventasproducto;
import com.ibs.proyecto.repository.IClienteRepository;
import com.ibs.proyecto.repository.IProductoRepository;
import com.ibs.proyecto.service.VentaService;

@Controller
@RequestMapping("/venta")
@CrossOrigin
public class VentaController {

	@Autowired
	VentaService sVenta;

	@Autowired
	IProductoRepository ipr;

	@Autowired
	IClienteRepository icr;

	public static List<Ventasproducto> detalles = new ArrayList<Ventasproducto>();

	@GetMapping(value = "/index")
	public String listarVenta(/* final Model m */) {

		// m.addAttribute("items", sVenta.getAllVenta().get();

		return new String("vistas/Venta/ListarVenta");
	}

	@GetMapping(value = "/guardar")
	public String guardarMostrar(final Model model, @RequestParam(required = false) final Cliente cliente) {
		model.addAttribute("productos", sVenta.getAllProductos());
		model.addAttribute("cliente", sVenta.getAllClientes());
		// model.addAttribute("proveedor", proveedor);
		detalles = new ArrayList<Ventasproducto>();

		return new String("vistas/Venta/agregar");
	}

	@PostMapping(value = "cargarCliente")
	public String cargarProveedor(@RequestParam final Long id, final Model model) {
		model.addAttribute("cliente", sVenta.getCliente(id));
		// model.addAttribute("productos", sVenta.getAllProductos());
		// model.addAttribute("clientes", sVenta.getAllClientes());
		return "redirect:/venta/guardar";
	}

	@GetMapping(value = "/api/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Venta> indexJSON() {
		return sVenta.getAllVenta();
	}

	@GetMapping(value = "api/productos")
	@ResponseBody
	public List<Producto> productosJSON() {
		return sVenta.getAllProductos();
	}

	@GetMapping(value = "getClientes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getCliente() {
		final List<HashMap<String, Object>> registros = new ArrayList<>();
		final List<Cliente> l = (List<Cliente>) icr.findAll();

		for (final Cliente cliente : l) {
			final HashMap<String, Object> object = new HashMap<>();

			object.put("id", cliente.getIdCliente());
			object.put("nombre", cliente.getNombreCliente());
			object.put("apellido", cliente.getApellidoCliente());
			object.put("direccion", cliente.getDireccionCliente());
			object.put("ncr", cliente.getNrc());
			object.put("tipo", cliente.getTipoCliente());
			object.put("giro", cliente.getGiro());
			object.put("telefono", cliente.getTelefonoCliente());
			object.put("operacion",
					"<button type='button' class= 'btn btn-primary agregarCliente' data-dismiss='modal'>agregar</button>");
			registros.add(object);
		}
		return Collections.singletonMap("data", registros);

	}

	@GetMapping(value = "getProductos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getProducto() {
		final List<HashMap<String, Object>> registros = new ArrayList<>();
		final List<Producto> l = (List<Producto>) ipr.findAll();

		for (final Producto producto : l) {
			final HashMap<String, Object> object = new HashMap<>();

			object.put("id", producto.getIdProducto());
			object.put("codigo", producto.getCodigoProducto());
			object.put("nombreProducto", producto.getNombreProducto());
			object.put("presentacion", producto.getPresentacion());
			object.put("existencias", producto.getExistencia());
			object.put("categoria", producto.getCategorias().getNombreCategoria());
			object.put("marca", producto.getMarcas().getNombreMarca());
			object.put("operacion",
					"<button type='button' class= 'btn btn-primary agregarProducto' data-dismiss='modal'>agregar</button>");
			registros.add(object);
		}
		return Collections.singletonMap("data", registros);
	}

	@GetMapping(value = "agregarDetalle")
	@ResponseBody
	@CrossOrigin
	public Ventasproducto agregarDetalle(@RequestParam Long cantidad, @RequestParam Float descuento,
			@RequestParam Long idProducto) {
		final Ventasproducto vp = new Ventasproducto();
		vp.setCantidad(cantidad);
		vp.setDescuento(descuento);
		vp.setProductos(sVenta.getProducto(idProducto));
		detalles.add(vp);
		return vp;
	}

	@GetMapping(value = "allDetalles", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public List<Ventasproducto> getDetallesMemoria() {
		return detalles;
	}

	@GetMapping(value = "detalles", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getDetalles() {
		return detalles;
	}

	@GetMapping(value = "resetDetalles", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object resetDetalles() {
		detalles = new ArrayList<>();
		// detalles.clear();
		return "lista reseteada";
	}

	@GetMapping(value = "save")
	@ResponseBody
	@CrossOrigin
	public Boolean save(@RequestParam Long numeroFactura, @RequestParam String tipoVenta,
			@RequestParam Long idCliente) {

		Venta entity = new Venta();
		entity.setNumeroFactura(numeroFactura);
		entity.setTipoVenta(tipoVenta);
		entity.setCliente(sVenta.getCliente(idCliente));

		for (Ventasproducto detalleVenta : detalles) {
			detalleVenta.setVentas(entity);
		}
		System.out.println(detalles + "" + idCliente);
		System.out.println(detalles.toString());
		entity.setVentasProducto(detalles);

		try {
			sVenta.save(entity);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
