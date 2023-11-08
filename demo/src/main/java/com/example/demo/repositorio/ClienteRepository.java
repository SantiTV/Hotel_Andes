package com.example.demo.repositorio;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.Cliente;

import jakarta.transaction.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente,  Integer> {
    
    // Crear un nuevo cliente
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Clientes (nombre, reservas, consumos, tipoDePlan) VALUES (:nombre, :reservas, :consumos, :tipoDePlan)", nativeQuery = true)
    void crearCliente(@Param("nombre") String nombre, @Param("reservas") Long reservas, @Param("consumos") Long consumes, @Param("tipoDePlan") String tipoDePlan);

    // Actualizar un cliente por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE Clientes SET nombre = :nombre, reservas = :reservas, consumos = :consumos, tipoDePlan = :tipoDePlan WHERE id_clientes = :id", nativeQuery = true)
    void actualizarCliente(@Param("id") Long id, @Param("nombre") String nombre, @Param("reservas") Long reservas, @Param("consumos") Long consumes, @Param("tipoDePlan") String tipoDePlan);

    // Borrar un cliente por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Clientes WHERE id_clientes = :id", nativeQuery = true)
    void borrarCliente(@Param("id") Long id);
    
    // Consultar todos los clientes
    @Query(value = "SELECT * FROM Clientes", nativeQuery = true)
    Collection<Cliente> darClientes();

    // Consultar un cliente por su ID
    @Query(value = "SELECT * FROM Clientes WHERE id_clientes = :id", nativeQuery = true)
    Cliente darCliente(@Param("id") long id);

    // Consultar la llegada de un cliente al hotel
     @Query(value = "SELECT c.* " +
     "FROM Clientes c " +
     "JOIN Reserva r ON c.id_clientes = r.Clientes_id_clientes " +
     "WHERE r.fechaEntrada = :fechaEntrada", nativeQuery = true)
      List<Cliente> consultarLlegadaClienteAlHotel(@Param("fechaEntrada") String fechaEntrada);
    
    // Consultar la salida de un cliente al hotel 
    @Query(value = "SELECT c.* " +
    "FROM Clientes c " +
    "JOIN Reserva r ON c.id_clientes = r.Clientes_id_clientes " +
    "WHERE r.fechaSalida = :fechaSalida", nativeQuery = true)
     List<Cliente> consultarSalidaClienteAlHotel(@Param("fechaSalida") String fechaSalida);


    // Consultar los buenos clientes según los criterios especificados
    @Query(value = "SELECT c.nombre AS nombre_cliente, " +
            "CASE " +
            "WHEN IFNULL(SUM(DATEDIFF(r.fechaSalida, r.fechaEntrada)), 0) >= 14 OR " +
            "SUM(r.costoAdicional + h.costo) > 15000000 " +
            "THEN 'Buen cliente' " +
            "ELSE 'Cliente regular' " +
            "END AS tipo_cliente " +
            "FROM Clientes c " +
            "JOIN Reserva r ON c.id_clientes = r.Clientes_id_clientes " +
            "JOIN Habitacion h ON r.id_Reserva = h.id_Reserva " +
            "WHERE r.fechaEntrada >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) " +
            "GROUP BY c.id_clientes", nativeQuery = true)
    List<Cliente> consultarBuenosClientesUltimoAnio();

    //Consultar consumo en HotelAndes
    @Query(value = "SELECT clientes.id_clientes, clientes.nombre, servicio.nombre AS servicio_nombre " +
        "FROM clientes " +
        "JOIN productoCliente ON clientes.id_clientes = productoCliente.id_clientes " +
        "JOIN productos ON productoCliente.id_productos = productos.id_productos " +
        "JOIN productoServicio ON productos.id_productos = productoServicio.id_productos " +
        "JOIN servicio ON productoServicio.id_servicio = servicio.id_servicio " +
        "WHERE servicio.id_servicio = ID_SERVICIO AND servicio.horario BETWEEN HORARIO_INICIO AND HORARIO_FINAL", nativeQuery = true)
    List<Cliente> consultarClientesPorServicioHorario(@Param("ID_SERVICIO") Long idServicio, @Param("HORARIO_INICIO") Date horarioInicio, @Param("HORARIO_FINAL") Date horarioFinal);
    
    //Consultar consumo en HotelAndes V2
    @Query(value = "SELECT c.id_clientes, c.nombre " +
        "FROM clientes c " +
        "LEFT JOIN ( " +
        "SELECT hc.id_clientes, hs.id_servicio " +
        "FROM habitacion_cliente hc " +
        "JOIN habitacion h ON hc.id_habitacion = h.id_habitacion " +
        "JOIN servicio hs ON h.servicioHotel = hs.id_servicio " +
        "WHERE hs.id_servicio = 'ID_SERVICIO' AND hc.fecha >= 'FECHA_INICIAL' AND hc.fecha <= 'FECHA_FINAL') t " +
        "ON c.id_clientes = t.id_clientes " +
        "WHERE t.id_clientes IS NULL", nativeQuery = true)
    List<Cliente> consultarClientesSinServicioEnPeriodo(@Param("ID_SERVICIO") Long idServicio, @Param("FECHA_INICIAL") Date fechaInicial, @Param("FECHA_FINAL") Date fechaFinal);

    //Consultar los clientes excelentes 
    @Query(value = "SELECT clientes.id_clientes, clientes.nombre, clientes.apellido, clientes.telefono, clientes.email, hotelAndes.id_hotel, hotelAndes.nombre AS nombreHotel, servicio.id_servicio, servicio.nombre AS nombreServicio, servicio.descripcion, servicio.costoadicional, servicio.horario, servicio.disponibilidad, consumo.id_plandeconsumo, consumo.id_productos, productos.nombre AS nombreProducto, productos.costo " +
        "FROM clientes " +
        "INNER JOIN hotelAndes ON clientes.habHotel = hotelAndes.id_hotel " +
        "INNER JOIN servicio ON clientes.id_clientes = servicio.cliente " +
        "INNER JOIN consumo ON clientes.id_clientes = consumo.id_clientes " +
        "INNER JOIN productos ON consumo.id_productos = productos.id_productos " +
        "WHERE clientes.id_clientes IN ( " +
        "  SELECT clientes.id_clientes " +
        "  FROM clientes " +
        "  INNER JOIN reserva ON clientes.id_clientes = reserva.cliente " +
        "  WHERE DATEDIFF(MONTH, reserva.check_in, GETDATE()) < 3 AND ( clientes.id_clientes IN ( " +
        "    SELECT clientes.id_clientes " +
        "    FROM clientes " +
        "    INNER JOIN consumo ON clientes.id_clientes = consumo.id_clientes " +
        "    INNER JOIN servicio ON consumo.id_servicio = servicio.id_servicio " +
        "    WHERE servicio.costoadicional > 30)))", nativeQuery = true)
    List<Cliente> obtenerDetallesClientesConConsumoAdicional();
}
