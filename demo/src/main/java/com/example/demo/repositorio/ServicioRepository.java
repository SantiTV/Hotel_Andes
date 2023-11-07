package com.example.demo.repositorio;

import java.util.Collection;
import java.util.List;

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

    // Consultar la reserva de un servicio por parte de un cliente
    @Query(value = "SELECT DISTINCT s.* " +
    "FROM Clientes c " +
    "JOIN PlanDeConsumo pc ON c.id_clientes = pc.Clientes_id_clientes " +
    "JOIN Servicio s ON pc.Servicio_id_servicio = s.id_servicio " +
    "WHERE c.nombre = :nombreCliente AND s.nombre = :nombreServicio", nativeQuery = true)
    List<Servicio> consultarReservaDeServicioPorCliente(@Param("nombreCliente") String nombreCliente, @Param("nombreServicio") String nombreServicio);

    // MOSTRAR LOS 20 SERVICIOS MÁS POPULARES.
    @Query(value = "SELECT s.nombre AS nombre_servicio, COUNT(s.id_servicio) AS veces_consumido " +
            "FROM servicio s " +
            "JOIN PlanDeConsumo p ON s.id_servicio = p.Servicio_id_servicio " +
            "JOIN reserva r ON p.id_plan = r.PlanDeConsumo_id_plan " +
            "WHERE r.fecha_de_entrada >= :fechaInicio " +
            "AND r.fecha_de_salida <= :fechaFin " +
            "GROUP BY s.nombre " +
            "ORDER BY veces_consumido DESC " +
            "LIMIT 20", nativeQuery = true)
    List<Servicio> consultarServiciosPopularesEnPeriodo(
            @Param("fechaInicio") String fechaInicio,
            @Param("fechaFin") String fechaFin
    );

     // Consultar los servicios con poca demanda (menos de 3 veces semanales al año) durante el último año de operación
    @Query(value = "SELECT s.nombre AS nombre_servicio, COUNT(s.id_servicio) as frecuencia" +
            "FROM servicio s " +
            "JOIN PlanDeConsumo p ON s.id_servicio = p.Servicio_id_servicio " +
            "JOIN reserva r ON p.id_Reserva= r.id_Reserva " +
            "WHERE r.fechaEntrada >= DATE_SUB(NOW(), INTERVAL 1 YEAR) " +
            "GROUP BY s.id_servicio " +
            "HAVING frecuencia < 3 * 52", nativeQuery = true)
    List<Servicio> consultarServiciosConPocaDemandaUltimoAnio();

    // Mostrar los servicios que cumplen con ciertas características
    @Query(value = "SELECT * " +
            "FROM servicio s " +
            "JOIN planDeConsumo pc ON s.id_servicio = pc.Servicio_id_servicio " +
            "JOIN reserva r ON pc.id_Reserva = r.id_Reserva " +
            "WHERE s.costoAdicional BETWEEN :costoMin AND :costoMax " +
            "AND s.disponibilidad BETWEEN :fechaInicio AND :fechaFin " +
            "AND s.tipoServicio = :tipoServicio", nativeQuery = true)
    List<Servicio> mostrarServiciosConCaracteristicas(@Param("costoMin") double costoMin, @Param("costoMax") double costoMax, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin, @Param("tipoServicio") String tipoServicio);
}
