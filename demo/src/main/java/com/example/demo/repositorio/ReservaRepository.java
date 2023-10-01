package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modelo.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    
    @Query(value = "SELECT * FROM Reserva", nativeQuery = true)
    Collection<Reserva> darReservas();
}
