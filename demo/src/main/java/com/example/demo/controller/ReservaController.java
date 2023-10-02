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

import com.example.demo.modelo.Reserva;
import com.example.demo.repositorio.ReservaRepository;

@RestController
@RequestMapping("/reservas") // Ruta base para las operaciones relacionadas con reservas
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    // Crear una nueva reserva
    @PostMapping("/")
    public void crearReserva(@RequestBody Reserva reserva) {
        reservaRepository.save(reserva);
    }

    // Actualizar una reserva por su ID
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarReserva(@PathVariable Integer id, @RequestBody Reserva reserva) {
        if (!reservaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reserva.setId(id);
        reservaRepository.save(reserva);
        return ResponseEntity.ok().build();
    }

    // Borrar una reserva por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarReserva(@PathVariable Integer id) {
        if (!reservaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reservaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Consultar todas las reservas
    @GetMapping("/")
    public List<Reserva> darReservas() {
        return reservaRepository.findAll();
    }

    // Consultar una reserva por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> darReserva(@PathVariable Integer id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        if (reservaOptional.isPresent()) {
            return ResponseEntity.ok(reservaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}