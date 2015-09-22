package proeza.mci;

import com.proeza.core.datamapper.annotation.Source;
import com.proeza.core.datamapper.annotation.excel.ExcelDescription;
import com.proeza.sgs.business.entity.Marca;

@ExcelDescription(sheetNo = 0, headerRow = 0, startAt = 2, endAt = 2)
public class MarcaMCI extends DataMCI {

	private static final String	SIN_MARCA_CODIGO	= "SINMARCA";
	private static final String	SIN_MARCA_NOMBRE	= "Sin Marca";

	@Source("Marca")
	private String				nombre;

	public Marca getEntity () {
		Marca marca = new Marca();
		marca.setNombre(formatNombre(this.nombre, SIN_MARCA_CODIGO));
		marca.setCodigo(formatCodigo(this.nombre, SIN_MARCA_NOMBRE));
		marca.setDescripcion(marca.getNombre());
		return marca;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		MarcaMCI other = (MarcaMCI) obj;
		if (nombre == null) {
			if (other.nombre != null) return false;
		} else if (!nombre.equals(other.nombre)) return false;
		return true;
	}

	@Override
	public String toString () {
		return "MarcaMCI [nombre=" + nombre + "]";
	}
}