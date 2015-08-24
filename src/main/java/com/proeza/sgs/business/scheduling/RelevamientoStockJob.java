package com.proeza.sgs.business.scheduling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proeza.core.service.IMovimientoService;
import com.proeza.core.tracking.dto.MovimientoDTO;
import com.proeza.core.tracking.entity.builder.MovimientoBuilder;
import com.proeza.core.util.date.DateUtil;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.TipoEntidad;
import com.proeza.sgs.business.entity.TipoMovimiento;

@Component
public class RelevamientoStockJob {

    @Autowired
    private IArticuloDao       articuloDao;

    @Autowired
    private IMovimientoService movimientoService;

    @Scheduled(cron = "${cron.stock.count}")
    public void countStockByBrand () {
        @SuppressWarnings("rawtypes")
        List result = this.articuloDao.getEntityManager()
            .createNamedQuery("relevamientoStock.porMarca")
            .getResultList();
        for (Object object : result) {
            Object[] item = (Object[]) object;
            Marca m = (Marca) item[0];
            createMovement(TipoEntidad.MARCA, m.getId(), "" + item[1]);
        }
    }

    @Scheduled(cron = "${cron.stock.count}")
    public void countStockByCategory () {
        @SuppressWarnings("rawtypes")
        List result = this.articuloDao.getEntityManager()
        .createNamedQuery("relevamientoStock.porRubro")
        .getResultList();
        for (Object object : result) {
            Object[] item = (Object[]) object;
            Rubro m = (Rubro) item[0];
            createMovement(TipoEntidad.RUBRO, m.getId(), "" + item[1]);
        }
    }

    private void createMovement (TipoEntidad tipoEntidad, Long idEntidad, String value) {
        this.movimientoService.create(new MovimientoDTO(
            new MovimientoBuilder()
                .withFecha(DateUtil.createNow())
                .withIdEntidad(idEntidad)
                .withTipoEntidad(tipoEntidad.getCodigo())
                .withTipoMov(TipoMovimiento.REL_STOCK.getCodigo())
                .withValorAnte(null)
                .withValorPost(value)
                .build()
            )
        );
    }
}