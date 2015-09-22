package proeza.mci;

import java.math.BigDecimal;

import com.proeza.core.datamapper.annotation.Source;
import com.proeza.core.datamapper.annotation.excel.ExcelDescription;

@ExcelDescription(sheetNo = 0, startAt = 0, endAt = 7)
public class ArticuloMCI extends DataMCI {
	@Source("Clase")
	private String		clase;

	@Source("Tipo")
	private String		tipo;

	@Source("Modelo")
	private String		modelo;

	@Source("Rubro")
	private String		rubro;

	@Source("Costo")
	private BigDecimal	costo;

	@Source("Precio")
	private BigDecimal	precio;

	@Source("Stock Real")
	private Long		stock;

	public String getClase () {
		return clase;
	}

	public void setClase (String clase) {
		this.clase = clase;
	}

	public String getTipo () {
		return tipo;
	}

	public void setTipo (String tipo) {
		this.tipo = tipo;
	}

	public String getModelo () {
		return modelo;
	}

	public void setModelo (String modelo) {
		this.modelo = modelo;
	}

	public String getRubro () {
		return rubro;
	}

	public void setRubro (String rubro) {
		this.rubro = rubro;
	}

	public BigDecimal getCosto () {
		return costo;
	}

	public void setCosto (BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getPrecio () {
		return precio;
	}

	public void setPrecio (BigDecimal precio) {
		this.precio = precio;
	}

	public Long getStock () {
		return stock;
	}

	public void setStock (Long stock) {
		this.stock = stock;
	}
}