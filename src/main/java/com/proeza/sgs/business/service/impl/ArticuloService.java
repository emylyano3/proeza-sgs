package com.proeza.sgs.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proeza.sgs.business.dao.ArticuloDao;
import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.business.dao.MarcaDao;
import com.proeza.sgs.business.dao.RubroDao;
import com.proeza.sgs.business.dao.TipoDao;
import com.proeza.sgs.business.dao.filter.ArticuloFilterFactory;
import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.dto.service.PrecioDTO;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.business.service.IArticuloService;

@Service
@Transactional
public class ArticuloService implements IArticuloService {

	public static final Logger	  log	= Logger.getLogger(ArticuloService.class);

	@Autowired
	private ArticuloDao	          articuloDao;

	@Autowired
	private ClaseDao	          claseDao;

	@Autowired
	private RubroDao	          rubroDao;

	@Autowired
	private MarcaDao	          marcaDao;

	@Autowired
	private TipoDao	              tipoDao;

	@Autowired
	private ArticuloFilterFactory	filterFactory;

	@Override
	public List<ArticuloDTO> findAll () {
		return hideEntites(this.articuloDao.findAll());
	}

	@Override
	public List<ArticuloDTO> findByStringFilter (String filter) {
		long init = System.currentTimeMillis();
		List<ArticuloDTO> data = hideEntites(this.filterFactory.create(filter).doFilter());
		long time = System.currentTimeMillis() - init;
		log.info("findByStringFilter - Tiempo insumido: " + time + "ms.");
		return data;
	}

	@Override
	public void update (ArticuloDTO dto) {
		Articulo art = this.articuloDao.findByCode(dto.getCodigo());
		Tipo tipo = this.tipoDao.findByCode(dto.getCodTipo());
		Rubro rubro = this.rubroDao.findByCode(dto.getCodRubro());
		Marca marca = this.marcaDao.findByCode(dto.getCodMarca());
		Clase clase = this.claseDao.findByCode(dto.getCodClase());
		art.setMarca(marca);
		art.setTipo(tipo);
		art.setRubro(rubro);
		art.setClase(clase);
		art.setCosto(BigDecimal.valueOf(dto.getCosto()));
		art.setPrecio(BigDecimal.valueOf(dto.getPrecio()));
		art.setModelo(dto.getModelo());
		art.setDescripcion(dto.getDescripcion());
	}

	@Override
    public List<PrecioDTO> priceHistory (String code) {
		List<PrecioDTO> result = new ArrayList<PrecioDTO>(0);
		switch (code) {
			case "1":
				result.add(new PrecioDTO(12.3, getDate(1,1,2015)));
				result.add(new PrecioDTO(13.0, getDate(2,2,2015)));
				result.add(new PrecioDTO(13.2, getDate(6,3,2015)));
				result.add(new PrecioDTO(14.0, getDate(2,4,2015)));
				result.add(new PrecioDTO(14.0, getDate(4,5,2015)));
				result.add(new PrecioDTO(13.9, getDate(9,6,2015)));
				break;
			case "2":
				result.add(new PrecioDTO(0.21, getDate(1,1,2015)));
				result.add(new PrecioDTO(0.31, getDate(2,1,2015)));
				result.add(new PrecioDTO(0.38, getDate(6,1,2015)));
				result.add(new PrecioDTO(0.39, getDate(2,1,2015)));
				result.add(new PrecioDTO(0.45, getDate(4,1,2015)));
				result.add(new PrecioDTO(0.50, getDate(9,1,2015)));
				break;
			case "3":
				result.add(new PrecioDTO(12201, getDate(1,1,2015)));
				result.add(new PrecioDTO(12291, getDate(2,2,2015)));
				result.add(new PrecioDTO(12308, getDate(6,3,2015)));
				result.add(new PrecioDTO(12419, getDate(2,4,2015)));
				result.add(new PrecioDTO(12555, getDate(4,5,2015)));
				result.add(new PrecioDTO(12600, getDate(9,6,2015)));
				break;

			default:
				break;
		}
		return result;
	}

	private String getDate (int day, int month, int year) {
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.DATE, day);
//		c.set(Calendar.MONTH, month);
//		c.set(Calendar.YEAR, year);
		return day + "/" + month + "/" + year;
	}

	private List<ArticuloDTO> hideEntites (List<Articulo> articulos) {
		List<ArticuloDTO> result = new ArrayList<>(articulos.size());
		for (Articulo art : articulos) {
			result.add(new ArticuloDTO(art));
		}
		return result;
	}

}