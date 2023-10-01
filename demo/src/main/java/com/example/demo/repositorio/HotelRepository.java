package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modelo.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{
    
    @Query(value = "SELECT * FROM Hotel", nativeQuery = true)
    Collection<Hotel> darHotel();
    
}
