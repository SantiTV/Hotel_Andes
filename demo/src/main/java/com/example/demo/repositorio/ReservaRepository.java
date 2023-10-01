package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.Reserva;

import jakarta.transaction.Transactional;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    
    // Crear una nueva reserva
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reserva (fechaEntrada, fechaSalida, numPersonas, Habitacion_id_habitacion, PlanDeConsumo_id_plan, Clientes_id_clientes, Id_hotel) VALUES (:fechaEntrada, :fechaSalida, :numPersonas, :habitacionId, :planDeConsumoId, :clientesId, :hotelId)", nativeQuery = true)
    void crearReserva(@Param("fechaEntrada") String fechaEntrada, @Param("fechaSalida") String fechaSalida, @Param("numPersonas") Long numPersonas, @Param("habitacionId") Long habitacionId, @Param("planDeConsumoId") Long planDeConsumoId, @Param("clientesId") Long clientesId, @Param("hotelId") Long hotelId);

    // Actualizar una reserva por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE Reserva SET fechaEntrada = :fechaEntrada, fechaSalida = :fechaSalida, numPersonas = :numPersonas, Habitacion_id_habitacion = :habitacionId, PlanDeConsumo_id_plan = :planDeConsumoId, Clientes_id_clientes = :clientesId, Id_hotel = :hotelId WHERE Id_Reserva = :id", nativeQuery = true)
    void actualizarReserva(@Param("id") Long id, @Param("fechaEntrada") String fechaEntrada, @Param("fechaSalida") String fechaSalida, @Param("numPersonas") Long numPersonas, @Param("habitacionId") Long habitacionId, @Param("planDeConsumoId") Long planDeConsumoId, @Param("clientesId") Long clientesId, @Param("hotelId") Long hotelId);

    // Borrar una reserva por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reserva WHERE Id_Reserva = :id", nativeQuery = true)
    void borrarReserva(@Param("id") Long id);

    // Consultar todas las reservas
    @Query(value = "SELECT * FROM Reserva", nativeQuery = true)
    Collection<Reserva> darReservas();

    // Consultar una reserva por su ID
    @Query(value = "SELECT * FROM Reserva WHERE Id_Reserva = :id", nativeQuery = true)
    Reserva darReserva(@Param("id") long id);
}
