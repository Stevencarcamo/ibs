package com.ibs.proyecto.service;

import java.util.List;

import javax.transaction.Transactional;

import com.ibs.proyecto.repository.IVentaRepository;
import com.ibs.proyecto.repository.IClienteRepository;
import com.ibs.proyecto.repository.IProductoRepository;
import com.ibs.proyecto.model.Venta;
import com.ibs.proyecto.model.Cliente;
import com.ibs.proyecto.model.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * CompraService
 */
@Service
public class VentaService {

    @Autowired
    IProductoRepository rProducto;

    @Autowired
    IClienteRepository rCliente;

    @Autowired
    IVentaRepository rVenta;

    @Transactional
    public List<Venta> getAllVenta() {
        return (List<Venta>) rVenta.findAll();
    }

    @Transactional
    public Boolean save(Venta entity) {
        try {
            rVenta.save(entity);
            return true;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public List<Cliente> getAllClientes() {
        return (List<Cliente>) rCliente.findAll();
    }

    @Transactional
    public List<Producto> getAllProductos() {
        return (List<Producto>) rProducto.findAll();
    }

    @Transactional
    public Cliente getCliente(Long id) {
        return rCliente.findById(id).get();
    }

    @Transactional
    public Producto getProducto(Long id) {
        return rProducto.findById(id).get();
    }
}