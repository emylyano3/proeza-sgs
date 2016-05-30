package proeza.mci.excel;

import com.proeza.core.datamapper.annotation.Source;
import com.proeza.core.datamapper.annotation.excel.ExcelDescription;
import com.proeza.sgs.business.entity.Marca;

@ExcelDescription(sheetNo = 0, headerRow = 0, startAt = 2, endAt = 2)
public class MarcaMCI extends DataMCI {

    public static final String SIN_MARCA_CODIGO = "SINMARCA";
    public static final String SIN_MARCA_NOMBRE = "Sin Marca";

    @Source("Marca")
    private String             nombre;

    public Marca getEntity() {
        Marca marca = new Marca();
        marca.setNombre(formatNombre(this.nombre, SIN_MARCA_NOMBRE));
        marca.setCodigo(formatCodigo(this.nombre, SIN_MARCA_CODIGO));
        marca.setDescripcion(marca.getNombre());
        return marca;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.nombre == null ? 0 : this.nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MarcaMCI other = (MarcaMCI) obj;
        if (this.nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MarcaMCI [nombre=" + this.nombre + "]";
    }
}