package proeza.mci;

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
		tipo.setCodigo(formatCodigo(nombre, null));
		tipo.setNombre(formatNombre(nombre, null));
		tipo.setDescripcion(formatDescripcion(nombre, null));
		return tipo;
	}

	@Override
	protected String formatCodigo (String value, String defaultValue) {
		String code = super.formatCodigo(value, defaultValue);
		return getCodePrefix() + code;
	}

	public String getCodigoClase () {
		return rubro.substring(0, 1).toUpperCase() + super.formatCodigo(clase, null);
	}

	private String getCodePrefix () {
		return clase.substring(0, 1).toUpperCase();
	}

	public String getNombre () {
		return nombre;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	public String getClase () {
		return clase;
	}

	public void setClase (String clase) {
		this.clase = clase;
	}

	public String getRubro () {
		return rubro;
	}

	public void setRubro (String rubro) {
		this.rubro = rubro;
	}

	@Override
	public String toString () {
		return "TipoMCI [nombre=" + nombre + ", clase=" + clase + ", rubro=" + rubro + "]";
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clase == null) ? 0 : clase.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((rubro == null) ? 0 : rubro.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TipoMCI other = (TipoMCI) obj;
		if (clase == null) {
			if (other.clase != null) return false;
		} else if (!clase.equals(other.clase)) return false;
		if (nombre == null) {
			if (other.nombre != null) return false;
		} else if (!nombre.equals(other.nombre)) return false;
		if (rubro == null) {
			if (other.rubro != null) return false;
		} else if (!rubro.equals(other.rubro)) return false;
		return true;
	}
}