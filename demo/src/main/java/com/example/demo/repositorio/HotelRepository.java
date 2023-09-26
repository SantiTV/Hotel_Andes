package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{
    
}
