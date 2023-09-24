package com.example.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO )
    private Integer id;

    private String nombre;



    public Cliente()
    {;}

    public Cliente(String nombre)
    {
        this.nombre = nombre;
    
    }


    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }




}
