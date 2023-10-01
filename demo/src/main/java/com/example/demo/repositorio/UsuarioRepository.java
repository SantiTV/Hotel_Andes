package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.Usuario;

import jakarta.transaction.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario,  Integer> {
    
// Crear un nuevo usuario
@Modifying
@Transactional
@Query(value = "INSERT INTO Usuarios (TipoDocumento, tipo, hotel_id_hotel, numeroDocumento, nombre, correElectronico) VALUES (:tipoDocumento, :tipo, :hotelId, :numeroDocumento, :nombre, :correoElectronico)", nativeQuery = true)
void crearUsuario(@Param("tipoDocumento") String tipoDocumento, @Param("tipo") String tipo, @Param("hotelId") Long hotelId, @Param("numeroDocumento") Long numeroDocumento, @Param("nombre") String nombre, @Param("correoElectronico") String correoElectronico);

// Actualizar un usuario por su ID
@Modifying
@Transactional
@Query(value = "UPDATE Usuarios SET TipoDocumento = :tipoDocumento, tipo = :tipo, hotel_id_hotel = :hotelId, numeroDocumento = :numeroDocumento, nombre = :nombre, correElectronico = :correoElectronico WHERE id_usuario = :id", nativeQuery = true)
void actualizarUsuario(@Param("id") Long id, @Param("tipoDocumento") String tipoDocumento, @Param("tipo") String tipo, @Param("hotelId") Long hotelId, @Param("numeroDocumento") Long numeroDocumento, @Param("nombre") String nombre, @Param("correoElectronico") String correoElectronico);

// Borrar un usuario por su ID
@Modifying
@Transactional
@Query(value = "DELETE FROM Usuarios WHERE id_usuario = :id", nativeQuery = true)
void borrarUsuario(@Param("id") Long id);

// Consultar usuarios por tipo
@Query(value = "SELECT * FROM Usuarios WHERE tipo = :tipo", nativeQuery = true)
Collection<Usuario> consultarUsuariosPorTipo(@Param("tipo") String tipo);

// Consultar todos los usuarios
@Query(value = "SELECT * FROM Usuarios", nativeQuery = true)
Collection<Usuario> darUsuarios();

// Consultar un usuario por su ID
@Query(value = "SELECT * FROM Usuarios WHERE id_usuario = :id", nativeQuery = true)
Usuario darUsuario(@Param("id") Long id);

}







