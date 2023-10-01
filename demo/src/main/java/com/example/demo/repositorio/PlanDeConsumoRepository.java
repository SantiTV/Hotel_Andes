package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.PlanDeConsumo;


public interface PlanDeConsumoRepository extends JpaRepository<PlanDeConsumo, Integer>  {
    
}
