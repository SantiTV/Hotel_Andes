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

import com.example.demo.modelo.ProducServicio;
import com.example.demo.modelo.ProducServicioPK;
import com.example.demo.repositorio.ProducServicioRepository;

@RestController
@RequestMapping("/producservicios") // Ruta base para las operaciones relacionadas con ProductosDeServicios
public class ProducServicioController {

    @Autowired
    private ProducServicioRepository producServicioRepository;

    // Crear un nuevo ProducServicio
    @PostMapping("/")
    public void crearProducServicio(@RequestBody ProducServicio producServicio) {
        producServicioRepository.save(producServicio);
    }

    // Actualizar un ProducServicio por su ID
    @PutMapping("/")
    public ResponseEntity<Void> actualizarProducServicio(@RequestBody ProducServicio producServicio) {
        ProducServicioPK pk = producServicio.getPk();
        Optional<ProducServicio> producServicioOptional = producServicioRepository.findById(pk);
        if (!producServicioOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        producServicioRepository.save(producServicio);
        return ResponseEntity.ok().build();
    }

    // Borrar un ProducServicio por su ID
    @DeleteMapping("/")
    public ResponseEntity<Void> borrarProducServicio(@RequestBody ProducServicioPK pk) {
        Optional<ProducServicio> producServicioOptional = producServicioRepository.findById(pk);
        if (!producServicioOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        producServicioRepository.delete(producServicioOptional.get());
        return ResponseEntity.ok().build();
    }

    // Consultar todos los ProducServicios
    @GetMapping("/")
    public Collection<ProducServicio> darProducServicios() {
        return producServicioRepository.findAll();
    }

    // Consultar un ProducServicio por su ID
    @GetMapping("/")
    public ResponseEntity<ProducServicio> darProducServicio(@RequestBody ProducServicioPK pk) {
        Optional<ProducServicio> producServicioOptional = producServicioRepository.findById(pk);
        if (producServicioOptional.isPresent()) {
            return ResponseEntity.ok(producServicioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}