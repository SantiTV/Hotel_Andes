import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PlanesDeConsumo")
public class PlanDeConsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private String descripcion;
    private Integer descuento;
    private String inclusiones;

    public PlanDeConsumo() {
        // Constructor por defecto
    }

    public PlanDeConsumo(String nombre, String descripcion, Integer descuento, String inclusiones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.inclusiones = inclusiones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public String getInclusiones() {
        return inclusiones;
    }

    public void setInclusiones(String inclusiones) {
        this.inclusiones = inclusiones;
    }
}
