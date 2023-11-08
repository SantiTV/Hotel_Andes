package com.example.demo.repositorio;

import java.util.Collection;
import java.util.List;

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

    // Mostrar el índice de ocupación de cada una de las habitaciones del hotel en el último año
    @Query(value = "SELECT h.id_habitacion, " +
            "(COUNT(r.id_Reserva) / (365 * (SELECT COUNT(*) FROM habitacion))) * 100 AS indice_ocupacion " +
            "FROM habitacion h " +
            "LEFT JOIN reserva r ON h.id_habitacion = r.Habitacion_id_habitacion " +
            "WHERE r.fechaEntrada >= DATE_SUB(NOW(), INTERVAL 1 YEAR) " +
            "GROUP BY h.id_habitacion", nativeQuery = true)
    List<Habitacion> mostrarIndiceOcupacionUltimoAnio();

    // Mostrar el dinero recolectado por servicios en cada habitación en el último año corrido
    @Query(value = "SELECT h.id_habitacion, SUM(s.costoAdicional) as dinero_recolectado " +
            "FROM habitacion h " +
            "LEFT JOIN reserva r ON h.id_habitacion = r.Habitacion_id_habitacion" +
            "LEFT JOIN planDeConsumo pc ON r.id_Reserva = pc.id_Reserva " +
            "LEFT JOIN servicio s ON pc.Servicio_id_servicio = s.id_servicio " +
            "WHERE r.fechaEntrada >= DATE_SUB(NOW(), INTERVAL 1 YEAR) " +
            "GROUP BY h.id_habitacion", nativeQuery = true)
    List<Habitacion> mostrarDineroRecolectadoPorServiciosUltimoAnio();

    // Habitación más solicitada
    @Query(value = "SELECT h.nombre, COUNT(*) as solicitudes " +
        "FROM habitacion h " +
        "JOIN reserva r ON h.id_habitacion = r.id_habitacion " +
        "GROUP BY h.nombre " +
        "ORDER BY solicitudes DESC " +
        "FETCH FIRST 1 ROW ONLY", nativeQuery = true)
    List<Habitacion> habitacionMasSolicitada();

    // Habitación menos solicitada
   @Query(value = "SELECT h.nombre, COUNT(*) as solicitudes " +
        "FROM habitacion h " +
        "JOIN reserva r ON h.id_habitacion = r.id_habitacion " +
        "GROUP BY h.nombre " +
        "ORDER BY solicitudes ASC " +
        "FETCH FIRST 1 ROW ONLY", nativeQuery = true)
   List<Habitacion> habitacionMenosSolicitada();
}
