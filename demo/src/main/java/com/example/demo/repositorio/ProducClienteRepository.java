package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.ProducCliente;

import jakarta.transaction.Transactional;

public interface ProducClienteRepository extends JpaRepository<ProducCliente, Integer> {
    
 // Crear un nuevo registro ProductosDeClientes
 @Modifying
 @Transactional
 @Query(value = "INSERT INTO ProductosDeClientes (Clientes_id_clientes, Productos_id_productos) VALUES (:clientesId, :productosId)", nativeQuery = true)
 void crearRegistroProductosDeClientes(@Param("clientesId") Long clientesId, @Param("productosId") Long productosId);

 // Borrar un registro ProductosDeClientes por IDs de Clientes y Productos
 @Modifying
 @Transactional
 @Query(value = "DELETE FROM ProductosDeClientes WHERE Clientes_id_clientes = :clientesId AND Productos_id_productos = :productosId", nativeQuery = true)
 void borrarRegistroProductosDeClientes(@Param("clientesId") Long clientesId, @Param("productosId") Long productosId);

 // Consultar todos los registros ProductosDeClientes
 @Query(value = "SELECT * FROM ProductosDeClientes", nativeQuery = true)
 Collection<ProducCliente> darRegistrosProductosDeClientes();

 // Consultar un registro ProductosDeClientes por IDs de Clientes y Productos
 @Query(value = "SELECT * FROM ProductosDeClientes WHERE Clientes_id_clientes = :clientesId AND Productos_id_productos = :productosId", nativeQuery = true)
 ProducCliente darRegistroProductosDeClientes(@Param("clientesId") Long clientesId, @Param("productosId") Long productosId);
}


