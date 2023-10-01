package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.Producto;

import jakarta.transaction.Transactional;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

    // Crear un nuevo producto
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Productos (nombre, costo) VALUES (:nombre, :costo)", nativeQuery = true)
    void crearProducto(@Param("nombre") String nombre, @Param("costo") Long costo);

    // Leer un producto por su ID
    @Query(value = "SELECT * FROM Productos WHERE id_productos = :id", nativeQuery = true)
    Producto consultarProductoPorId(@Param("id") Long id);

    // Actualizar un producto por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre = :nombre, costo = :costo WHERE id_productos = :id", nativeQuery = true)
    void actualizarProducto(@Param("id") Long id, @Param("nombre") String nombre, @Param("costo") Long costo);

    // Borrar un producto por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Productos WHERE id_productos = :id", nativeQuery = true)
    void borrarProducto(@Param("id") Long id);

    // Consultar todos los productos
    @Query(value = "SELECT * FROM Productos", nativeQuery = true)
    Collection<Producto> darProductos();

    // Consultar un producto por su ID
    @Query(value = "SELECT * FROM Productos WHERE id_productos = :id", nativeQuery = true)
    Producto darProducto(@Param("id") long id);
}
