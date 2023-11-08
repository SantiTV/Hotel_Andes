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

    // Consultar las fechas de los días de mayor ocupación para todo el tiempo de operación
    @GetMapping("/reservas/fechas-mayor-ocupacion-total")
    public List<Reserva> consultarFechasMayorOcupacionTotal() {
        return reservaRepository.consultarFechasMayorOcupacionTotal();
    }

    // Consultar las fechas de mayores ingresos para todo el tiempo de operación
    @GetMapping("/reservas/fechas-mayores-ingresos-total")
    public List<Reserva> consultarFechasMayoresIngresosTotal() {
        return reservaRepository.consultarFechasMayoresIngresosTotal();
    }

    // Consultar las fechas de menor demanda (menor ocupación) para todo el tiempo de operación
    @GetMapping("/reservas/fechas-menor-demanda-total")
    public List<Reserva> consultarFechasMenorDemandaTotal() {
        return reservaRepository.consultarFechasMenorDemandaTotal();
    }

    // Mostrar el consumo en HotelAndes por un usuario dado en un rango de fechas indicado
    @GetMapping("/reservas/consumo-por-usuario-rango-fechas")
    public List<Reserva> mostrarConsumoPorUsuarioEnRangoDeFechas(@RequestParam long id_cliente, @RequestParam String fechaInicio, @RequestParam String fechaFin) {
        return reservaRepository.mostrarConsumoPorUsuarioEnRangoDeFechas(id_cliente, fechaInicio, fechaFin);
    }
}