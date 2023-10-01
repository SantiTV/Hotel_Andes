package com.example.demo.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.Consumo;

import jakarta.transaction.Transactional;

public interface ConsumoRepository extends JpaRepository<Consumo,  Integer>{

    // Crear un nuevo consumo
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Consumo (PlanDeConsumo_id_plan, Productos_id_productos, registro, fecha) VALUES (:planId, :productoId, :registro, :fecha)", nativeQuery = true)
    void crearConsumo(@Param("planId") Long planId, @Param("productoId") Long productoId, @Param("registro") String registro, @Param("fecha") Date fecha);

    // Actualizar un consumo por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE Consumo SET PlanDeConsumo_id_plan = :planId, Productos_id_productos = :productoId, registro = :registro, fecha = :fecha WHERE id_consumo = :id", nativeQuery = true)
    void actualizarConsumo(@Param("id") Long id, @Param("planId") Long planId, @Param("productoId") Long productoId, @Param("registro") String registro, @Param("fecha") Date fecha);

    // Borrar un consumo por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Consumo WHERE id_consumo = :id", nativeQuery = true)
    void borrarConsumo(@Param("id") Long id);

    // Consultar todos los consumos
    @Query(value = "SELECT * FROM Consumo", nativeQuery = true)
    Collection<Consumo> darConsumos();

    // Consultar un consumo por su ID
    @Query(value = "SELECT * FROM Consumo WHERE id_consumo = :id", nativeQuery = true)
    Consumo darConsumo(@Param("id") long id);
}
