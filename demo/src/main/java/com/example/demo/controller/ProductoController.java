package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Producto;
import com.example.demo.repositorio.ProductoRepository;

@RestController
@RequestMapping("/productos") // Ruta base para las operaciones relacionadas con productos
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Crear un nuevo producto
    @PostMapping("/")
    public void crearProducto(@RequestBody Producto producto) {
        productoRepository.save(producto);
    }

    // Actualizar un producto por su ID
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        producto.setId(id);
        productoRepository.save(producto);
        return ResponseEntity.ok().build();
    }

    // Borrar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProducto(@PathVariable Integer id) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Consultar todos los productos
    @GetMapping("/")
    public List<Producto> darProductos() {
        return productoRepository.findAll();
    }

    // Consultar un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> darProducto(@PathVariable Integer id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok(productoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
