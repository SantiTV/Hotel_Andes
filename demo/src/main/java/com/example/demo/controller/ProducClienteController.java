package com.example.demo.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.ProducCliente;
import com.example.demo.modelo.ProducClientePK;
import com.example.demo.repositorio.ProducClienteRepository;

@RestController
@RequestMapping("/producclientes") // Ruta base para las operaciones relacionadas con ProductosDeClientes
public class ProducClienteController {

    @Autowired
    private ProducClienteRepository producClienteRepository;

    // Crear un nuevo ProducCliente
    @PostMapping("/")
    public void crearProducCliente(@RequestBody ProducCliente producCliente) {
        producClienteRepository.save(producCliente);
    }

    // Actualizar un ProducCliente por su ID
    @PutMapping("/")
    public ResponseEntity<Void> actualizarProducCliente(@RequestBody ProducCliente producCliente) {
        ProducClientePK pk = producCliente.getPk();
        Optional<ProducCliente> producClienteOptional = producClienteRepository.findById(pk);
        if (!producClienteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        producClienteRepository.save(producCliente);
        return ResponseEntity.ok().build();
    }

    // Borrar un ProducCliente por su ID
    @DeleteMapping("/")
    public ResponseEntity<Void> borrarProducCliente(@RequestBody ProducClientePK pk) {
        Optional<ProducCliente> producClienteOptional = producClienteRepository.findById(pk);
        if (!producClienteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        producClienteRepository.delete(producClienteOptional.get());
        return ResponseEntity.ok().build();
    }

    // Consultar todos los ProducClientes
    @GetMapping("/")
    public Collection<ProducCliente> darProducClientes() {
        return producClienteRepository.findAll();
    }

    // Consultar un ProducCliente por su ID
    @GetMapping("/")
    public ResponseEntity<ProducCliente> darProducCliente(@RequestBody ProducClientePK pk) {
        Optional<ProducCliente> producClienteOptional = producClienteRepository.findById(pk);
        if (producClienteOptional.isPresent()) {
            return ResponseEntity.ok(producClienteOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}