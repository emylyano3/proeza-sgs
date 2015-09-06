package com.proeza.sgs.business.dao;

import java.util.List;

import com.proeza.core.persistence.Dao;
import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.sgs.business.entity.Articulo;

public interface IArticuloDao extends Dao<Articulo> {

    Articulo findByCode (String code);

    /**
     * Devuelve los movimientos filtrados por codigo de articulo y tipo de movimiento ordenados ascendentemente.
     *
     * @return Una lista de {@link Movimiento} con el resultado.
     */
    List<Movimiento> findMovimientosAscByDate (String codeArt, String tipoMov);
}