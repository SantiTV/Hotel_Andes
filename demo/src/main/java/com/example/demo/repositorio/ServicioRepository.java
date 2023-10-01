package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modelo.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer>{
    
    @Query(value = "SELECT * FROM Servicio", nativeQuery = true)
    Collection<Servicio> darServicios();
}
