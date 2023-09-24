package com.example.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Hoteles")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private String cadenaHotelera;
    private String direccion;
    private String categoria;

    public Hotel() {
        // Constructor por defecto
    }

    public Hotel(String nombre, String cadenaHotelera, String direccion, String categoria) {
        this.nombre = nombre;
        this.cadenaHotelera = cadenaHotelera;
        this.direccion = direccion;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCadenaHotelera() {
        return cadenaHotelera;
    }

    public void setCadenaHotelera(String cadenaHotelera) {
        this.cadenaHotelera = cadenaHotelera;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
