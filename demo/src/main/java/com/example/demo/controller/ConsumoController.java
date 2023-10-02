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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Consumo;
import com.example.demo.modelo.ConsumoPK;
import com.example.demo.repositorio.ConsumoRepository;

@RestController
@RequestMapping("/consumos") // Ruta base para las operaciones relacionadas con consumos
public class ConsumoController {

    @Autowired
    private ConsumoRepository consumoRepository;

    // Crear un nuevo consumo
    @PostMapping("/")
    public void crearConsumo(@RequestBody Consumo consumo) {
        consumoRepository.save(consumo);
    }

    // Actualizar un consumo por su clave primaria compuesta
    @PutMapping("/")
    public ResponseEntity<Void> actualizarConsumo(@RequestBody Consumo consumo) {
        Optional<Consumo> consumoOptional = consumoRepository.findById(consumo.getPk());
        if (!consumoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        consumoRepository.save(consumo);
        return ResponseEntity.ok().build();
    }

    // Borrar un consumo por su clave primaria compuesta
    @DeleteMapping("/")
    public ResponseEntity<Void> borrarConsumo(@RequestBody ConsumoPK pk) {
        Optional<Consumo> consumoOptional = consumoRepository.findById(pk);
        if (!consumoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        consumoRepository.delete(consumoOptional.get());
        return ResponseEntity.ok().build();
    }

    // Consultar todos los consumos
    @GetMapping("/")
    public Collection<Consumo> darConsumos() {
        return consumoRepository.findAll();
    }

    // Consultar un consumo por su clave primaria compuesta
    @GetMapping("/")
    public ResponseEntity<Consumo> darConsumo(@RequestBody ConsumoPK pk) {
        Optional<Consumo> consumoOptional = consumoRepository.findById(pk);
        if (consumoOptional.isPresent()) {
            return ResponseEntity.ok(consumoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Consultar consumos por parte del cliente
    @GetMapping("/consultarPorCliente")
    public Collection<Consumo> consultarConsumoPorCliente(@RequestParam("nombreCliente") String nombreCliente, @RequestParam("registroConsumo") String registroConsumo) {
        return consumoRepository.consultarConsumoPorCliente(nombreCliente, registroConsumo);
    }
}
