package com.proeza.sgs.business.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.business.dao.IArticuloDao;
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
import com.proeza.sgs.business.entity.TipoMovimiento;
import com.proeza.sgs.business.service.IArticuloService;

import static javax.xml.bind.DatatypeConverter.*;

@Service
@Transactional
public class ArticuloService implements IArticuloService {

	public static final Logger	  log	= Logger.getLogger(ArticuloService.class);

	@Autowired
	private IArticuloDao	      articuloDao;

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
	public PrecioHistoryDTO priceHistory (String code) {
		List<Movimiento> movs = this.articuloDao.findMovimientosAsc(code, TipoMovimiento.MOD_PRECIO.getCodigo());
		return new PrecioHistoryDTO(buildData(movs), buildLabels(movs));
	}

	private List<Double> buildData (List<Movimiento> movs) {
	    List<Double> data = new ArrayList<>(movs.size());
		for (Iterator<Movimiento> it = movs.iterator(); it.hasNext();) {
			Movimiento mov = it.next();
			data.add(parseDouble(mov.getValorAnte()));
			if (!it.hasNext()) {
				data.add(parseDouble(mov.getValorPost()));
			}
		}
	    return data;
    }

	private List<String>  buildLabels (List<Movimiento> movs) {
		List<String> labels = new ArrayList<String>();
		DateFormat pattern = new SimpleDateFormat("MMMMM yyyy");
		for (Movimiento mov : movs) {
			labels.add(pattern.format(mov.getFechaMovimiento()));
        }
		return labels;
	}

	private List<ArticuloDTO> hideEntites (List<Articulo> articulos) {
		List<ArticuloDTO> result = new ArrayList<>(articulos.size());
		for (Articulo art : articulos) {
			result.add(new ArticuloDTO(art));
		}
		return result;
	}

}