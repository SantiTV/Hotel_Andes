package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.ProducServicio;

import jakarta.transaction.Transactional;

public interface ProducServicioRepository extends JpaRepository<ProducServicio, Integer> {
    
// Crear un nuevo ProducServicio
@Modifying
@Transactional
@Query(value = "INSERT INTO ProductosDeServicios (Servicio_id_servicio, Productos_id_productos) VALUES (:servicioId, :productosId)", nativeQuery = true)
void crearProducServicio(@Param("servicioId") Long servicioId, @Param("productosId") Long productosId);

// Actualizar un ProducServicio por su ID
@Modifying
@Transactional
@Query(value = "UPDATE ProductosDeServicios SET Servicio_id_servicio = :servicioId, Productos_id_productos = :productosId WHERE id = :id", nativeQuery = true)
void actualizarProducServicio(@Param("id") Long id, @Param("servicioId") Long servicioId, @Param("productosId") Long productosId);

// Borrar un ProducServicio por su ID
@Modifying
@Transactional
@Query(value = "DELETE FROM ProductosDeServicios WHERE id = :id", nativeQuery = true)
void borrarProducServicio(@Param("id") Long id);

// Consultar todos los ProducServicios
@Query(value = "SELECT * FROM ProductosDeServicios", nativeQuery = true)
Collection<ProducServicio> darProducServicios();

// Consultar un ProducServicio por su ID
@Query(value = "SELECT * FROM ProductosDeServicios WHERE id = :id", nativeQuery = true)
ProducServicio darProducServicio(@Param("id") Long id);
}

