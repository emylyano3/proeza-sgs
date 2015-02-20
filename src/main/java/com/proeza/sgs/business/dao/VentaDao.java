package com.proeza.sgs.business.dao;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.business.entity.Venta;

@Repository
public class VentaDao extends BaseDao<Venta> {

//	@Override
//	public Venta persist (Venta entity) {
//		if (entity.getId() != 0) {
//			entity = actualizarStockPorVentaModificada(entity);
//		} else {
//			entity = actualizarStockPorNuevaVenta(entity);
//		}
//		return super.persist(entity);
//	}
//
//	@Override
//	public void delete (Venta entity) {
//		entity = actualizarStockPorVentaEliminada(entity);
//		super.delete(entity);
//	}
//
//	/**
//	 * Recorre los articulos asociados a la venta y actualiza el stock de acuerdo a la cantidad vendida
//	 */
//	private Venta actualizarStockPorNuevaVenta (Venta venta) {
//		for (VentaArticulo va : venta.getArticulos()) {
//			Articulo a = va.getArticulo();
//			int cantidad = a.getCantidad() - va.getCantidad();
//			a.setCantidad(cantidad);
//		}
//		return venta;
//	}
//
//	private Venta actualizarStockPorVentaModificada (Venta entity) {
//		// Quito la entidad del persistent context
//		this.entityManager.detach(entity);
//		// La traigo desde la base datos
//		Venta aux = this.entityManager.find(Venta.class, entity.getId());
//		for (VentaArticulo va : aux.getArticulos()) {
//			va.getCantidad();
//		}
//		return entity;
//	}
//
//	private Venta actualizarStockPorVentaEliminada (Venta entity) {
//		return entity;
//	}
}