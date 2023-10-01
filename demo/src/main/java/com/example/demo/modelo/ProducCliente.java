package com.example.demo.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProductosDeClientes")
public class ProducCliente {
    
    @EmbeddedId
    private ProducClientePK pk;

    public ProducCliente()
    {;}

    public ProducCliente( Cliente Clientes_id_clientes, Producto Productos_id_productos){
        this.pk = new ProducClientePK(Clientes_id_clientes, Productos_id_productos);
    }

    public ProducClientePK getPk(){
        return pk;
    }

    public void setPk(ProducClientePK pk){
        this.pk = pk;
    }
}
