package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Consumo;

public interface ConsumoRepository extends JpaRepository<Consumo,  Integer>{
    
}
