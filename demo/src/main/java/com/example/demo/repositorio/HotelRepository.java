package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.Hotel;

import jakarta.transaction.Transactional;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{
    
     // Crear un nuevo hotel
     @Modifying
     @Transactional
     @Query(value = "INSERT INTO Hotel (nombre, cadenaHotelera, direccion, categorIa) VALUES (:nombre, :cadenaHotelera, :direccion, :categorIa)", nativeQuery = true)
     void crearHotel(@Param("nombre") String nombre, @Param("cadenaHotelera") String cadenaHotelera, @Param("direccion") String direccion, @Param("categorIa") String categorIa);

     // Actualizar un hotel por su ID
     @Modifying
     @Transactional
     @Query(value = "UPDATE Hotel SET nombre = :nombre, cadenaHotelera = :cadenaHotelera, direccion = :direccion, categorIa = :categorIa WHERE id_hotel = :id", nativeQuery = true)
     void actualizarHotel(@Param("id") Long id, @Param("nombre") String nombre, @Param("cadenaHotelera") String cadenaHotelera, @Param("direccion") String direccion, @Param("categorIa") String categorIa);
 
     // Borrar un hotel por su ID
     @Modifying
     @Transactional
     @Query(value = "DELETE FROM Hotel WHERE id_hotel = :id", nativeQuery = true)
     void borrarHotel(@Param("id") Long id);
 
     // Consultar todos los hoteles
     @Query(value = "SELECT * FROM Hotel", nativeQuery = true)
     Collection<Hotel> darHoteles();
 
     // Consultar un hotel por su ID
     @Query(value = "SELECT * FROM Hotel WHERE id_hotel = :id", nativeQuery = true)
     Hotel darHotel(@Param("id") long id);
    
}
