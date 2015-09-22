package proeza.mci;

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
		clase.setCodigo(formatCodigo(nombre, null));
		clase.setNombre(formatNombre(nombre, null));
		clase.setDescripcion(formatDescripcion(nombre, null));
		return clase;
	}

	@Override
	protected String formatCodigo (String value, String defaultValue) {
		String code = super.formatCodigo(value, defaultValue);
		return getCodePrefix() + code;
	}

	private String getCodePrefix () {
		return rubro.substring(0, 1).toUpperCase();
	}

	public String getNombre () {
		return nombre;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	public String getRubro () {
		return rubro;
	}

	public void setRubro (String rubro) {
		this.rubro = rubro;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((rubro == null) ? 0 : rubro.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ClaseMCI other = (ClaseMCI) obj;
		if (nombre == null) {
			if (other.nombre != null) return false;
		} else if (!nombre.equals(other.nombre)) return false;
		if (rubro == null) {
			if (other.rubro != null) return false;
		} else if (!rubro.equals(other.rubro)) return false;
		return true;
	}

	@Override
	public String toString () {
		return "ClaseMCI [nombre=" + nombre + ", rubro=" + rubro + "]";
	}
}