package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    
}
