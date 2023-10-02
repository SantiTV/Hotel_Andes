package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.PlanDeConsumo;

import jakarta.transaction.Transactional;


public interface PlanDeConsumoRepository extends JpaRepository<PlanDeConsumo, Integer>  {
    
// Crear un nuevo PlanDeConsumo
@Modifying
@Transactional
@Query(value = "INSERT INTO PlanDeConsumo (id_plan, nombre, descripcion, descuento, inclusiones, id_Reserva, Clientes_id_clientes, Hotel_id_hotel, Servicio_id_servicio) VALUES (:idPlan, :nombre, :descripcion, :descuento, :inclusiones, :idReserva, :clientesId, :hotelId, :servicioId)", nativeQuery = true)
void crearPlanDeConsumo(@Param("idPlan") Long idPlan, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("descuento") Double descuento, @Param("inclusiones") String inclusiones, @Param("idReserva") Long idReserva, @Param("clientesId") Long clientesId, @Param("hotelId") Long hotelId, @Param("servicioId") Long servicioId);

// Actualizar un PlanDeConsumo por su ID
@Modifying
@Transactional
@Query(value = "UPDATE PlanDeConsumo SET nombre = :nombre, descripcion = :descripcion, descuento = :descuento, inclusiones = :inclusiones, id_Reserva = :idReserva, Clientes_id_clientes = :clientesId, Hotel_id_hotel = :hotelId, Servicio_id_servicio = :servicioId WHERE id_plan = :id", nativeQuery = true)
void actualizarPlanDeConsumo(@Param("id") Long id, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("descuento") Double descuento, @Param("inclusiones") String inclusiones, @Param("idReserva") Long idReserva, @Param("clientesId") Long clientesId, @Param("hotelId") Long hotelId, @Param("servicioId") Long servicioId);

// Borrar un PlanDeConsumo por su ID
@Modifying
@Transactional
@Query(value = "DELETE FROM PlanDeConsumo WHERE id_plan = :id", nativeQuery = true)
void borrarPlanDeConsumo(@Param("id") Long id);

// Consultar todos los PlanesDeConsumo
@Query(value = "SELECT * FROM PlanDeConsumo", nativeQuery = true)
Collection<PlanDeConsumo> darPlanesDeConsumo();

// Consultar un PlanDeConsumo por su ID
@Query(value = "SELECT * FROM PlanDeConsumo WHERE id_plan = :id", nativeQuery = true)
PlanDeConsumo darPlanDeConsumo(@Param("id") Long id);
}
