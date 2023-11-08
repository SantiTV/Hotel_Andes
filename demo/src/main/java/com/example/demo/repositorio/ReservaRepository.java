package com.example.demo.repositorio;

import java.util.Collection;
import java.util.List;

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
    

    // Consultar las fechas de los días de mayor ocupación para todo el tiempo de operación
    @Query(value = "SELECT r.fechaEntrada " +
            "FROM reserva r " +
            "GROUP BY r.fechaEntrada " +
            "ORDER BY COUNT(*) DESC LIMIT 1", nativeQuery = true)
    List<Reserva> consultarFechasMayorOcupacionTotal();

    // Consultar las fechas de mayores ingresos para todo el tiempo de operación
    @Query(value = "SELECT r.fechaEntrada " +
            "FROM reserva r " +
            "JOIN habitacion h ON r.id_Reserva = h.id_Reserva " +
            "JOIN planDeConsumo pc ON r.id_Reserva = pc.id_Reserva " +
            "JOIN servicio s ON pc.Servicio_id_servicio = s.id_servicio " +
            "GROUP BY r.fechaEntrada " +
            "ORDER BY (SUM(h.costo) + SUM(s.costoAdicional)) DESC LIMIT 1", nativeQuery = true)
    List<Reserva> consultarFechasMayoresIngresosTotal();

    // Consultar las fechas de menor demanda (menor ocupación) para todo el tiempo de operación
    @Query(value = "SELECT r.fechaEntrada " +
            "FROM reserva r " +
            "GROUP BY r.fechaEntrada " +
            "ORDER BY COUNT(*) ASC LIMIT 1", nativeQuery = true)
    List<Reserva> consultarFechasMenorDemandaTotal();

    // Mostrar el consumo en HotelAndes por un usuario dado en un rango de fechas indicado
    @Query(value = "SELECT r.fechaEntrada, h.costo as costo_habitacion, s.costoAdicional as costo_servicio " +
            "FROM reserva r " +
            "JOIN habitacion h ON r.id_Reserva = h.id_Reserva " +
            "JOIN planDeConsumo pc ON r.id_Reserva = pc.id_Reserva " +
            "JOIN servicio s ON pc.Servicio_id_servicio = s.id_servicio " +
            "WHERE r.Clientes_id_clientes = :id_cliente " +
            "AND r.fechaEntrada BETWEEN :fechaInicio AND :fechaFin", nativeQuery = true)
    List<Reserva> mostrarConsumoPorUsuarioEnRangoDeFechas(@Param("id_cliente") long id_cliente, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
}
