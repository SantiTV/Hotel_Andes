package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modelo.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer>{
    
    @Query(value = "SELECT * FROM Habitacion", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();
}
