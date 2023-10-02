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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Servicio;
import com.example.demo.repositorio.ServicioRepository;

@RestController
@RequestMapping("/servicios") // Ruta base para las operaciones relacionadas con servicios
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @PostMapping("/") // Crear un nuevo servicio
    public void crearServicio(@RequestBody Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @PutMapping("/{id}") // Actualizar un servicio por su ID
    public ResponseEntity<Void> actualizarServicio(@PathVariable Integer id, @RequestBody Servicio servicio) {
        if (!servicioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        servicio.setId(id);
        servicioRepository.save(servicio);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}") // Borrar un servicio por su ID
    public ResponseEntity<Void> borrarServicio(@PathVariable Integer id) {
        if (!servicioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        servicioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/") // Consultar todos los servicios
    public List<Servicio> darServicios() {
        return servicioRepository.findAll();
    }

    @GetMapping("/{id}") // Consultar un servicio por su ID
    public ResponseEntity<Servicio> darServicio(@PathVariable Integer id) {
        Optional<Servicio> servicioOptional = servicioRepository.findById(id);
        if (servicioOptional.isPresent()) {
            return ResponseEntity.ok(servicioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultarReservaPorCliente") // Consultar la reserva de un servicio por parte de un cliente
    public List<Servicio> consultarReservaDeServicioPorCliente(@RequestParam("nombreCliente") String nombreCliente, @RequestParam("nombreServicio") String nombreServicio) {
        List<Servicio> serviciosReservados = servicioRepository.consultarReservaDeServicioPorCliente(nombreCliente, nombreServicio);
        return serviciosReservados;
    }
}