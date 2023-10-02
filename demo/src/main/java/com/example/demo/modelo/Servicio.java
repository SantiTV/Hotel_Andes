package com.example.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Servicios")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_servicio;

    private String nombre;
    private String descripcion;
    private Integer costoAdicional;
    private Integer horario;
    private Integer disponibilidad;

    public Servicio() {
        // Constructor por defecto
    }

    public Servicio(String nombre, String descripcion, Integer costoAdicional, Integer horario, Integer disponibilidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoAdicional = costoAdicional;
        this.horario = horario;
        this.disponibilidad = disponibilidad;
    }

    public Integer getId() {
        return id_servicio;
    }

    public void setId(Integer id) {
        this.id_servicio = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCostoAdicional() {
        return costoAdicional;
    }

    public void setCostoAdicional(Integer costoAdicional) {
        this.costoAdicional = costoAdicional;
    }

    public Integer getHorario() {
        return horario;
    }

    public void setHorario(Integer horario) {
        this.horario = horario;
    }

    public Integer getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
