package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.Habitacion;

import jakarta.transaction.Transactional;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer>{
    // Crear una nueva habitación
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Habitacion (tipo, capacidad, costo, cuenta, id_reserva, Hotel_id_hotel) VALUES (:tipo, :capacidad, :costo, :cuenta, :idReserva, :hotelId)", nativeQuery = true)
    void crearHabitacion(@Param("tipo") String tipo, @Param("capacidad") Long capacidad, @Param("costo") Long costo, @Param("cuenta") Long cuenta, @Param("idReserva") Long idReserva, @Param("hotelId") Long hotelId);

    // Actualizar una habitación por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE Habitacion SET tipo = :tipo, capacidad = :capacidad, costo = :costo, cuenta = :cuenta, id_reserva = :idReserva, Hotel_id_hotel = :hotelId WHERE id_habitacion = :id", nativeQuery = true)
    void actualizarHabitacion(@Param("id") Long id, @Param("tipo") String tipo, @Param("capacidad") Long capacidad, @Param("costo") Long costo, @Param("cuenta") Long cuenta, @Param("idReserva") Long idReserva, @Param("hotelId") Long hotelId);

    // Borrar una habitación por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Habitacion WHERE id_habitacion = :id", nativeQuery = true)
    void borrarHabitacion(@Param("id") Long id);

    // Consultar habitaciones por tipo
    @Query(value = "SELECT * FROM Usuarios WHERE tipo = :tipo", nativeQuery = true)
    Collection<Habitacion> consultarPorTipo(@Param("tipo") String tipo);

    // Consultar todas las habitaciones
    @Query(value = "SELECT * FROM Habitacion", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();

    // Consultar una habitación por su ID
    @Query(value = "SELECT * FROM Habitacion WHERE id_habitacion = :id", nativeQuery = true)
    Habitacion darHabitacion(@Param("id") long id);
}
