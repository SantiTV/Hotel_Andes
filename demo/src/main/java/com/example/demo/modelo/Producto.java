package com.example.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Productos")
public class Producto {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO )
    private Integer id_productos;

    private String nombre;

    private Integer costo;



    public Producto()
    {;}

    public Producto(String nombre, Integer costo){
        
        this.nombre = nombre;
        this.costo = costo;

    }

    public Integer getId() {
        return id_productos;
    }

    public void setId(Integer id) {
        this.id_productos = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Integer getCosto(){
        return costo;
    }

    public void setCosto(Integer costo){
        this.costo = costo;
    }
}
