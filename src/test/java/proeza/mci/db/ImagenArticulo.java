package proeza.mci.db;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ImagenArticulo generated by hbm2java
 */
@Entity
@Table(name = "imagen_articulo", catalog = "pescalotodo")
public class ImagenArticulo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer           id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idArticulo", nullable = false)
    private ArticuloMci          articulo;

    @Column(name = "thumb", nullable = false)
    private Blob              thumb;

    @Column(name = "image", nullable = false)
    private Blob              image;

    public ImagenArticulo() {
    }

    public ImagenArticulo(Blob thumb, Blob image) {
        this.thumb = thumb;
        this.image = image;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArticuloMci getArticulo() {
        return this.articulo;
    }

    public void setArticulo(ArticuloMci articulo) {
        this.articulo = articulo;
    }

    public Blob getThumb() {
        return this.thumb;
    }

    public void setThumb(Blob thumb) {
        this.thumb = thumb;
    }

    public Blob getImage() {
        return this.image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

}
