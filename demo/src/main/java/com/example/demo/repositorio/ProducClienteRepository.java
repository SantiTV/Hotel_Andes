package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.ProducCliente;

import jakarta.transaction.Transactional;

public interface ProducClienteRepository extends JpaRepository<ProducCliente, Integer> {
    
// Crear un nuevo ProducCliente
@Modifying
@Transactional
@Query(value = "INSERT INTO ProductosDeClientes (Clientes_id_clientes, Productos_id_productos) VALUES (:clientesId, :productosId)", nativeQuery = true)
void crearProducCliente(@Param("clientesId") Long clientesId, @Param("productosId") Long productosId);

// Actualizar un ProducCliente por su ID
@Modifying
@Transactional
@Query(value = "UPDATE ProductosDeClientes SET Clientes_id_clientes = :clientesId, Productos_id_productos = :productosId WHERE id = :id", nativeQuery = true)
void actualizarProducCliente(@Param("id") Long id, @Param("clientesId") Long clientesId, @Param("productosId") Long productosId);

// Borrar un ProducCliente por su ID
@Modifying
@Transactional
@Query(value = "DELETE FROM ProductosDeClientes WHERE id = :id", nativeQuery = true)
void borrarProducCliente(@Param("id") Long id);

// Consultar todos los ProducClientes
@Query(value = "SELECT * FROM ProductosDeClientes", nativeQuery = true)
Collection<ProducCliente> darProducClientes();

// Consultar un ProducCliente por su ID
@Query(value = "SELECT * FROM ProductosDeClientes WHERE id = :id", nativeQuery = true)
ProducCliente darProducCliente(@Param("id") Long id);
}

