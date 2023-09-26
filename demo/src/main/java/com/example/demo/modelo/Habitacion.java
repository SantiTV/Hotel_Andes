package com.example.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="bares")
public class Habitacion {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)    

    private Integer id;
    private String tipo;
    private int capacidad;
    private int dotacion;
    private int costo;
    private int cuenta;



    public Habitacion() 
    {;}

    public Habitacion(String tipo, int capacidad, int dotacion, int costo, int cuenta) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.dotacion = dotacion;
        this.costo = costo;
        this.cuenta = cuenta;
        
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getDotacion() {
        return dotacion;
    }

    public void setDotacion(int dotacion) {
        this.dotacion = dotacion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

}
