package com.ibs.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibs.proyecto.model.Producto;
import com.ibs.proyecto.model.Proveedore;
import com.ibs.proyecto.repository.ICompraRepository;
import com.ibs.proyecto.repository.IProductoRepository;
import com.ibs.proyecto.repository.IProveedorRepository;

@Controller
@RequestMapping("/compra")
public class CompraController {

	@Autowired
	ICompraRepository cr;
	
	@Autowired
	IProductoRepository ipr;
	
	@Autowired
	IProveedorRepository pr;
	
	@GetMapping(value="/index")
	public String listarCompra(Model m) {
		
		m.addAttribute("items", cr.findAll());
		
		return new String("vistas/Compra/ListarCompra");
	}
	
	@GetMapping(value="/guardarCompra")
	public String guardarCompra(Model m) {
		List<Producto> nombreProductos = (List<Producto>) ipr.findAll();
		List<Proveedore> Proveedores = (List<Proveedore>) pr.findAll();
		m.addAttribute("nombreProductos", nombreProductos);
		m.addAttribute("Proveedores", Proveedores);
		
		return new String("vistas/Compra/AgregarCompra");
	}
}
