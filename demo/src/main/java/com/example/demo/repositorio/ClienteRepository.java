package com.example.demo.repositorio;

import java.util.Collection;
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

     

}
