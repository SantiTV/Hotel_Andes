package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modelo.Consumo;

public interface ConsumoRepository extends JpaRepository<Consumo,  Integer>{

    @Query(value = "SELECT * FROM Consumo", nativeQuery = true)
    Collection<Consumo> darConsumos();
}
