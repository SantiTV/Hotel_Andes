package com.example.demo.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ProducClientePK implements Serializable {

    @ManyToOne
    @JoinColumn(name =  "Clientes_id_clientes", referencedColumnName = "id_clientes") 
    private Cliente Clientes_id_clientes;


    @ManyToOne
    @JoinColumn( name = "Productos_id_productos", referencedColumnName = "id_productos" )
    private Producto Productos_id_productos;

    public ProducClientePK(Cliente  clientes_id_clientes, Producto  productos_id_productos ){
        super();
        this.Clientes_id_clientes = clientes_id_clientes;
        this.Productos_id_productos =  productos_id_productos;

    }

    public Cliente getClientes_id_clientes() {
        return Clientes_id_clientes;
    }

    public void setClientes_id_clientes(Cliente clientes_id_clientes) {
        Clientes_id_clientes = clientes_id_clientes;
    }

    public Producto getProductos_id_productos() {
        return Productos_id_productos;
    }

    public void setProductos_id_productos(Producto productos_id_productos) {
        Productos_id_productos = productos_id_productos;
    }

    


}
