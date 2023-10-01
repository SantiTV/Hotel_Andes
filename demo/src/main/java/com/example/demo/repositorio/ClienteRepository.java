package com.example.demo.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,  Integer> {
    
    @Query(value = "SELECT * FROM Clientes", nativeQuery = true)
    Collection<Cliente> darClientes();
}
