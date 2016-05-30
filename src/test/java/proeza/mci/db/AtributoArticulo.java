package proeza.mci.db;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AtributoArticulo generated by hbm2java
 */
@Entity
@Table(name = "atributo_articulo", catalog = "pescalotodo")
public class AtributoArticulo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer           id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String            nombre;

    @Column(name = "descripcion", length = 200)
    private String            descripcion;

    public AtributoArticulo() {
    }

    public AtributoArticulo(String nombre) {
        this.nombre = nombre;
    }

    public AtributoArticulo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}