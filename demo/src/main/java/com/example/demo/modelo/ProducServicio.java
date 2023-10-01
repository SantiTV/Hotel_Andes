package com.example.demo.modelo;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table( name = "ProductosDeServicios" )
public class ProducServicio {
    
    @EmbeddedId
    private ProducServicioPK pk;

    public ProducServicio(){
        // Constructor por defecto
    }

    public ProducServicio(Servicio Servicio_id_servicio, Producto Productos_id_productos){
        super();
        this.pk = new ProducServicioPK(Servicio_id_servicio, Productos_id_productos);
    }

    public ProducServicioPK getPk() {
        return pk;
    }

    public void setPk(ProducServicioPK pk) {
        this.pk = pk;
    }

    
}
