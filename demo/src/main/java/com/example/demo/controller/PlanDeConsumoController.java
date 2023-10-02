package com.example.demo.controller;

import java.util.Collection;

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

import com.example.demo.modelo.PlanDeConsumo;
import com.example.demo.repositorio.PlanDeConsumoRepository;

@RestController
@RequestMapping("/planesDeConsumo") // Ruta base para las operaciones relacionadas con planes de consumo
public class PlanDeConsumoController {

    @Autowired
    private PlanDeConsumoRepository planDeConsumoRepository;

    @PostMapping("/") // Crear un nuevo PlanDeConsumo
    public void crearPlanDeConsumo(@RequestBody PlanDeConsumo planDeConsumo) {
        planDeConsumoRepository.save(planDeConsumo);
    }

    @PutMapping("/{id}") // Actualizar un PlanDeConsumo por su ID
    public ResponseEntity<Void> actualizarPlanDeConsumo(@PathVariable Integer id, @RequestBody PlanDeConsumo planDeConsumo) {
        if (!planDeConsumoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        planDeConsumo.setId(id);
        planDeConsumoRepository.save(planDeConsumo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}") // Borrar un PlanDeConsumo por su ID
    public ResponseEntity<Void> borrarPlanDeConsumo(@PathVariable Integer id) {
        if (!planDeConsumoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        planDeConsumoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/") // Consultar todos los PlanesDeConsumo
    public Collection<PlanDeConsumo> darPlanesDeConsumo() {
        return planDeConsumoRepository.findAll();
    }

    @GetMapping("/{id}") // Consultar un PlanDeConsumo por su ID
    public ResponseEntity<PlanDeConsumo> darPlanDeConsumo(@PathVariable Integer id) {
        return planDeConsumoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
