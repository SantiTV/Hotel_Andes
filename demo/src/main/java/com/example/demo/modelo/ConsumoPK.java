package com.example.demo.modelo;

import java.io.Serializable;
import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class ConsumoPK implements Serializable {

    @ManyToOne
    @JoinColumn( name = "PlanDeConsumo_id_plan", referencedColumnName = "id_plan" )
    private PlanDeConsumo planDeConsumo_id_plan;

    @ManyToOne
    @JoinColumn( name = "Productos_id_productos", referencedColumnName = "id_productos")
    private Producto productos_id_productos;
    
    private String registro;

    private Date fecha;


    public ConsumoPK(){
        super();
    }


    public ConsumoPK ( PlanDeConsumo planDeConsumo_id_plan, Producto productos_id_productos, String registro, Date fecha){
        super();
        this.planDeConsumo_id_plan = planDeConsumo_id_plan;
        this.planDeConsumo_id_plan = planDeConsumo_id_plan;
        this.registro = registro;
        this.fecha = fecha;
    }


    public PlanDeConsumo getPlanDeConsumo_id_plan() {
        return planDeConsumo_id_plan;
    }


    public void setPlanDeConsumo_id_plan(PlanDeConsumo planDeConsumo_id_plan) {
        this.planDeConsumo_id_plan = planDeConsumo_id_plan;
    }


    public Producto getProductos_id_productos() {
        return productos_id_productos;
    }


    public void setProductos_id_productos(Producto productos_id_productos) {
        this.productos_id_productos = productos_id_productos;
    }


    public String getRegistro() {
        return registro;
    }


    public void setRegistro(String registro) {
        this.registro = registro;
    }


    public Date getFecha() {
        return fecha;
    }


    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    


}
    
    