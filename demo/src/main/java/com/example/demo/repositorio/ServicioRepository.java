package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.Servicio;

import jakarta.transaction.Transactional;

public interface ServicioRepository extends JpaRepository<Servicio, Integer>{
    
    // Crear un nuevo servicio
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Servicio (nombre, descripción, costoAdicional, horario, disponibilidad, Hotel_id_hotel) VALUES (:nombre, :descripcion, :costoAdicional, :horario, :disponibilidad, :hotelId)", nativeQuery = true)
    void crearServicio(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("costoAdicional") Long costoAdicional, @Param("horario") Long horario, @Param("disponibilidad") Long disponibilidad, @Param("hotelId") Long hotelId);

    // Leer un servicio por su ID
    @Query(value = "SELECT * FROM Servicio WHERE id_servicio = :id", nativeQuery = true)
    Servicio consultarServicioPorId(@Param("id") Long id);

    // Actualizar un servicio por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE Servicio SET nombre = :nombre, descripción = :descripcion, costoAdicional = :costoAdicional, horario = :horario, disponibilidad = :disponibilidad, Hotel_id_hotel = :hotelId WHERE id_servicio = :id", nativeQuery = true)
    void actualizarServicio(@Param("id") Long id, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("costoAdicional") Long costoAdicional, @Param("horario") Long horario, @Param("disponibilidad") Long disponibilidad, @Param("hotelId") Long hotelId);

    // Borrar un servicio por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Servicio WHERE id_servicio = :id", nativeQuery = true)
    void borrarServicio(@Param("id") Long id);

    // Consultar todos los servicios
    @Query(value = "SELECT * FROM Servicio", nativeQuery = true)
    Collection<Servicio> darServicios();

    // Consultar un servicio por su ID
    @Query(value = "SELECT * FROM Servicio WHERE id_servicio = :id", nativeQuery = true)
    Servicio darServicio(@Param("id") long id);
}
