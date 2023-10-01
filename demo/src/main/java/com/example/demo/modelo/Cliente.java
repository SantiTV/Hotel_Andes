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
    private Integer id_clientes;

    private String nombre;
    private Integer reservas;
    private Integer comsumos;
    private String tipoDePlan;



    public Cliente()
    {;}

    public Cliente(String nombre, Integer reservas, Integer comsumos, String tipoDePlan)
    {
        this.nombre = nombre;
    
    }


    public Integer getId() {
        return id_clientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Integer id_clientes) {
        this.id_clientes = id_clientes;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


        public Integer getReservas() {
        return reservas;
    }

    public void setReservas(Integer reservas) {
        this.reservas = reservas;
    }

    public Integer getComsumos() {
        return comsumos;
    }

    public void setComsumos(Integer comsumos) {
        this.comsumos = comsumos;
    }

    public String getTipoDePlan() {
        return tipoDePlan;
    }

    public void setTipoDePlan(String tipoDePlan) {
        this.tipoDePlan = tipoDePlan;
    }



}
