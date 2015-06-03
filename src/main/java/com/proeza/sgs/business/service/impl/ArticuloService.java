package com.proeza.sgs.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.proeza.sgs.business.dto.service.PrecioHistoryDTO;
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
    public List<PrecioHistoryDTO> priceHistory (String code) {
		List<PrecioHistoryDTO> result = new ArrayList<PrecioHistoryDTO>(0);
		List<Double> data = new ArrayList<>();
		List<Double> data2 = new ArrayList<>();
		switch (code) {
			case "1":
				data.addAll(Arrays.asList(new Double[] {28D, 48D, 40D, 19D, 86D, 27D, 90D}));
				data2.addAll(Arrays.asList(new Double[] {65D, 59D, 80D, 81D, 56D, 55D, 40D}));
				result.add(new PrecioHistoryDTO(data));
				result.add(new PrecioHistoryDTO(data2));
				break;
			case "2":
				data.addAll(Arrays.asList(new Double[] {10D, 30D, 30D, 50D, 50D, 100D, 100D}));
				data2.addAll(Arrays.asList(new Double[] {65D, 59D, 80D, 81D, 56D, 55D, 40D}));
				result.add(new PrecioHistoryDTO(data));
				result.add(new PrecioHistoryDTO(data2));
				break;
			case "3":
				data.addAll(Arrays.asList(new Double[] {28D, 48D, 40D, 19D, 86D, 27D, 90D}));
				data2.addAll(Arrays.asList(new Double[] {65D, 59D, 80D, 81D, 56D, 55D, 40D}));
				result.add(new PrecioHistoryDTO(data));
				result.add(new PrecioHistoryDTO(data2));
				break;

			default:
				break;
		}
		return result;
	}


	private List<ArticuloDTO> hideEntites (List<Articulo> articulos) {
		List<ArticuloDTO> result = new ArrayList<>(articulos.size());
		for (Articulo art : articulos) {
			result.add(new ArticuloDTO(art));
		}
		return result;
	}

}