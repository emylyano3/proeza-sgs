package proeza.mci.excel;

import java.math.BigDecimal;
import com.proeza.core.datamapper.annotation.Source;
import com.proeza.core.datamapper.annotation.excel.ExcelDescription;
import com.proeza.sgs.business.entity.Articulo;

@ExcelDescription(sheetNo = 0, startAt = 0, endAt = 8)
public class ArticuloMCI extends DataMCI {
    @Source("Clase")
    private String     clase;

    @Source("Tipo")
    private String     tipo;

    @Source("Modelo")
    private String     modelo;

    @Source("Rubro")
    private String     rubro;

    @Source("Marca")
    private String     marca;

    @Source("Descripcion")
    private String     descipcion;

    @Source("Costo")
    private BigDecimal costo;

    @Source("Precio")
    private BigDecimal precio;

    @Source("Stock")
    private Long       stock;

    public Articulo getEntity() {
        Articulo articulo = new Articulo();
        articulo.setCodigo(formatCodigo());
        articulo.setCosto(this.costo == null ? BigDecimal.ZERO : this.costo);
        articulo.setPrecio(this.precio == null ? BigDecimal.ZERO : this.precio);
        articulo.setStock(this.stock == null ? 0 : this.stock.intValue());
        articulo.setDescripcion(formatDescripcion(this.descipcion, null));
        articulo.setModelo(formatDescripcion(this.modelo, null));
        return articulo;
    }

    private String formatCodigo() {
        StringBuilder builder = new StringBuilder();
        builder
        .append(randomChar())
        .append(randomChar())
        .append(randomChar());
        if (this.modelo != null) {
            int length = Math.min(5, this.modelo.length());
            String modelo = this.modelo != null ? this.modelo.substring(0, length) : null;
            return formatCodigo(modelo + builder, "" + builder);
        }
        return formatCodigo("" + builder, "" + builder);
    }

    public String getCodigoClase() {
        return this.rubro.substring(0, 1).toUpperCase() + super.formatCodigo(this.clase, null);
    }

    @Override
    protected String formatCodigo(String value, String defaultValue) {
        String code = super.formatCodigo(value, defaultValue);
        return getCodePrefix() + code;
    }

    public RubroMCI getRubroMCI() {
        RubroMCI rubro = new RubroMCI();
        rubro.setNombre(this.rubro);
        return rubro;
    }

    public MarcaMCI getMarcaMCI() {
        MarcaMCI marca = new MarcaMCI();
        marca.setNombre(this.marca);
        return marca;
    }

    public ClaseMCI getClaseMCI() {
        ClaseMCI clase = new ClaseMCI();
        clase.setNombre(this.clase);
        clase.setRubro(this.rubro);
        return clase;
    }

    public TipoMCI getTipoMCI() {
        TipoMCI tipo = new TipoMCI();
        tipo.setNombre(this.tipo);
        tipo.setRubro(this.rubro);
        tipo.setClase(this.clase);
        return tipo;
    }

    private String getCodePrefix() {
        StringBuilder builder = new StringBuilder();
        builder
        .append(cut(this.rubro, 1).toUpperCase())
        .append(cut(this.clase, 2).toUpperCase())
        .append(cut(this.tipo, 2).toUpperCase())
        .append(cut(this.marca, 2).toUpperCase());
        return builder.toString();
    }

    private String cut(Object o, int size) {
        if (o == null) {
            return "";
        }
        String s = o.toString();
        if (s.length() < size) {
            return s;
        }
        return s.substring(0, size);
    }

    public String getClase() {
        return this.clase != null ? null : this.clase.trim();
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getTipo() {
        return this.tipo == null ? null : this.tipo.trim();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getRubro() {
        return this.rubro == null ? null : this.rubro.trim();
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public BigDecimal getCosto() {
        return this.costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigDecimal getPrecio() {
        return this.precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Long getStock() {
        return this.stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getDescipcion() {
        return this.descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public String getMarca() {
        return this.marca == null ? MarcaMCI.SIN_MARCA_CODIGO : this.marca.trim();
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "ArticuloMCI [clase=" + this.clase + ", tipo=" + this.tipo + ", modelo=" + this.modelo + ", rubro=" + this.rubro + ", marca=" + this.marca + "]";
    }
}