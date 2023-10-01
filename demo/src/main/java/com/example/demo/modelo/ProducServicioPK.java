package com.example.demo.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class ProducServicioPK implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "Servicio_id_servicio", referencedColumnName = "id_servicio")
    private Servicio servicio_id_servicio;

    @ManyToOne
    @JoinColumn( name= "Productos_id_productos", referencedColumnName =  "id_productos")
    private Producto productos_id_productos;

    public ProducServicioPK(){
        super();
    }

    public ProducServicioPK(Servicio servicio_id_servicio, Producto productos_id_productos){
        super();
        this.servicio_id_servicio = servicio_id_servicio;
        this.productos_id_productos = productos_id_productos;
    
    }

    public Servicio getServicio_id_servicio() {
        return servicio_id_servicio;
    }

    public void setServicio_id_servicio(Servicio servicio_id_servicio) {
        this.servicio_id_servicio = servicio_id_servicio;
    }

    public Producto getProductos_id_productos() {
        return productos_id_productos;
    }

    public void setProductos_id_productos(Producto productos_id_productos) {
        this.productos_id_productos = productos_id_productos;
    }


    

}
