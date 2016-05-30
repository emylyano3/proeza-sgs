package proeza.mci.excel;

import com.proeza.core.datamapper.annotation.Source;
import com.proeza.core.datamapper.annotation.excel.ExcelDescription;
import com.proeza.sgs.business.entity.Clase;

@ExcelDescription(sheetNo = 0, startAt = 0, endAt = 4)
public class ClaseMCI extends DataMCI {
    @Source("Clase")
    private String	nombre;

    @Source("Rubro")
    private String	rubro;

    public Clase getEntity () {
        Clase clase = new Clase();
        clase.setCodigo(formatCodigo(this.nombre, null));
        clase.setNombre(formatNombre(this.nombre, null));
        clase.setDescripcion(formatDescripcion(this.nombre, null));
        return clase;
    }

    @Override
    protected String formatCodigo (String value, String defaultValue) {
        String code = super.formatCodigo(value, defaultValue);
        return getCodePrefix() + code;
    }

    private String getCodePrefix () {
        return this.rubro.substring(0, 1).toUpperCase();
    }

    public String getNombre () {
        return this.nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public String getRubro () {
        return this.rubro.trim();
    }

    public void setRubro (String rubro) {
        this.rubro = rubro;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
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
        ClaseMCI other = (ClaseMCI) obj;
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

    @Override
    public String toString () {
        return "ClaseMCI [nombre=" + this.nombre + ", rubro=" + this.rubro + "]";
    }
}