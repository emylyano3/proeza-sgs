package proeza.mci.excel;

import com.proeza.core.datamapper.annotation.Source;
import com.proeza.core.datamapper.annotation.excel.ExcelDescription;
import com.proeza.sgs.business.entity.Rubro;

@ExcelDescription(sheetNo = 1, headerRow = 0, startAt = 0, endAt = 2)
public class RubroMCI extends DataMCI {
	@Source("Codigo")
	private String codigo;

	@Source("Nombre")
	private String nombre;

	@Source("Descripcion")
	private String descripcion;

	public Rubro getEntity () {
		Rubro rubro = new Rubro();
		rubro.setCodigo(formatCodigo(this.codigo, null));
		rubro.setNombre(formatNombre(this.nombre, null));
		rubro.setDescripcion(formatDescripcion(this.descripcion, null));
		return rubro;
	}

	public String getCodigo () {
		return this.codigo;
	}

	public void setCodigo (String codigo) {
		this.codigo = codigo;
	}

	public String getNombre () {
		return this.nombre;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion () {
		return this.descripcion;
	}

	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString () {
		return "RubroMCI [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}