package proeza.mci.excel;

import com.proeza.core.datamapper.annotation.Source;
import com.proeza.core.datamapper.annotation.excel.ExcelDescription;
import com.proeza.sgs.business.entity.Tipo;

@ExcelDescription(sheetNo = 0, startAt = 0, endAt = 4)
public class TipoMCI extends DataMCI {
    @Source("Tipo")
    private String	nombre;

    @Source("Clase")
    private String	clase;

    @Source("Rubro")
    private String	rubro;

    public Tipo getEntity () {
        Tipo tipo = new Tipo();
        tipo.setCodigo(formatCodigo(this.nombre, null));
        tipo.setNombre(formatNombre(this.nombre, null));
        tipo.setDescripcion(formatDescripcion(this.nombre, null));
        return tipo;
    }

    @Override
    protected String formatCodigo (String value, String defaultValue) {
        String code = super.formatCodigo(value, defaultValue);
        return getCodePrefix() + code;
    }

    public String getCodigoClase () {
        return this.rubro.substring(0, 1).toUpperCase() + super.formatCodigo(this.clase, null);
    }

    private String getCodePrefix () {
        return this.clase.substring(0, 1).toUpperCase();
    }

    public String getNombre () {
        return this.nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public String getClase () {
        return this.clase;
    }

    public void setClase (String clase) {
        this.clase = clase;
    }

    public String getRubro () {
        return this.rubro;
    }

    public void setRubro (String rubro) {
        this.rubro = rubro;
    }

    @Override
    public String toString () {
        return "TipoMCI [nombre=" + this.nombre + ", clase=" + this.clase + ", rubro=" + this.rubro + "]";
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.clase == null ? 0 : this.clase.hashCode());
        result = prime * result + (this.nombre == null ? 0 : this.nombre.hashCode());
        result = prime * result + (this.rubro == null ? 0 : this.rubro.hashCode());
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TipoMCI other = (TipoMCI) obj;
        if (this.clase == null) {
            if (other.clase != null) {
                return false;
            }
        } else if (!this.clase.equals(other.clase)) {
            return false;
        }
        if (this.nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!this.nombre.equals(other.nombre)) {
            return false;
        }
        if (this.rubro == null) {
            if (other.rubro != null) {
                return false;
            }
        } else if (!this.rubro.equals(other.rubro)) {
            return false;
        }
        return true;
    }
}