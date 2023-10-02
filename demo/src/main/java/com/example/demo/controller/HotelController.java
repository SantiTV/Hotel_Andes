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

import com.example.demo.modelo.Hotel;
import com.example.demo.repositorio.HotelRepository;

@RestController
@RequestMapping("/hoteles") // Ruta base para las operaciones relacionadas con hoteles
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping("/") // Crear un nuevo hotel
    public void crearHotel(@RequestBody Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @PutMapping("/{id}") // Actualizar un hotel por su ID
    public ResponseEntity<Void> actualizarHotel(@PathVariable Integer id, @RequestBody Hotel hotel) {
        if (!hotelRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        hotel.setId(id);
        hotelRepository.save(hotel);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}") // Borrar un hotel por su ID
    public ResponseEntity<Void> borrarHotel(@PathVariable Integer id) {
        if (!hotelRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        hotelRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/") // Consultar todos los hoteles
    public Collection<Hotel> darHoteles() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}") // Consultar un hotel por su ID
    public ResponseEntity<Hotel> darHotel(@PathVariable Integer id) {
        return hotelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}