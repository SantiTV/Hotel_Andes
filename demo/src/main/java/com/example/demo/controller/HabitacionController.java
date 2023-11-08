package com.example.demo.controller;

import java.util.Collection;
import java.util.List;

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

import com.example.demo.modelo.Habitacion;
import com.example.demo.repositorio.HabitacionRepository;

@RestController
@RequestMapping("/habitaciones") // Ruta base para las operaciones relacionadas con habitaciones
public class HabitacionController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @PostMapping("/") // Crear una nueva habitación
    public void crearHabitacion(@RequestBody Habitacion habitacion) {
        habitacionRepository.save(habitacion);
    }

    @PutMapping("/{id}") // Actualizar una habitación por su ID
    public ResponseEntity<Void> actualizarHabitacion(@PathVariable Integer id, @RequestBody Habitacion habitacion) {
        if (!habitacionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        habitacion.setId(id);
        habitacionRepository.save(habitacion);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}") // Borrar una habitación por su ID
    public ResponseEntity<Void> borrarHabitacion(@PathVariable Integer id) {
        if (!habitacionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        habitacionRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/") // Consultar todas las habitaciones
    public Collection<Habitacion> darHabitaciones() {
        return habitacionRepository.darHabitaciones();
    }

    @GetMapping("/{id}") // Consultar una habitación por su ID
    public ResponseEntity<Habitacion> darHabitacion(@PathVariable Long id) {
        Habitacion habitacion = habitacionRepository.darHabitacion(id);
        if (habitacion != null) {
            return ResponseEntity.ok(habitacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultarPorTipo") // Consultar habitaciones por tipo
    public Collection<Habitacion> consultarPorTipo(@RequestParam("tipo") String tipo) {
        return habitacionRepository.consultarPorTipo(tipo);
    }

    // Mostrar el índice de ocupación de cada una de las habitaciones del hotel en el último año
    @GetMapping("/habitaciones/ocupacion")
    public List<Habitacion> mostrarIndiceOcupacionUltimoAnio() {
        return habitacionRepository.mostrarIndiceOcupacionUltimoAnio();
    }
    
    // Mostrar el dinero recolectado por servicios en cada habitación en el último año corrido
    @GetMapping("/habitaciones/dinero-recolectado")
    public List<Habitacion> mostrarDineroRecolectadoPorServiciosUltimoAnio() {
        return habitacionRepository.mostrarDineroRecolectadoPorServiciosUltimoAnio();
    }
}