package com.example.demo.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Consumo")
public class Consumo {
    

    @EmbeddedId
    private ConsumoPK pk;

    public Consumo() 
    {;}

    public Consumo(PlanDeConsumo PlanDeConsumo_id_plan, Producto Productos_id_productos, String registro, Date fecha) {
       this.pk = new ConsumoPK(PlanDeConsumo_id_plan, Productos_id_productos, registro, fecha);

    }

    public ConsumoPK getPk() {
        return pk;
    }

    public void setPk(ConsumoPK pk) {
        this.pk = pk;
    }

    


}

