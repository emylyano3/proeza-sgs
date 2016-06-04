package com.proeza.sgs.business.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.core.util.date.DateUtil;
import com.proeza.security.dao.IUsuarioDao;
import com.proeza.security.entity.Usuario;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.dao.IMedioPagoDao;
import com.proeza.sgs.business.dao.IVentaDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.MedioPago;
import com.proeza.sgs.business.entity.Venta;
import com.proeza.sgs.business.entity.builder.VentaBuilder;
import com.proeza.sgs.business.service.IVentaService;
import com.proeza.sgs.business.service.dto.QuickSaleDTO;

@Service
@Transactional
public class VentaService implements IVentaService {

    @Autowired
    private IVentaDao     ventaDao;

    @Autowired
    private IArticuloDao  articulodao;

    @Autowired
    private IMedioPagoDao medioPagoDao;

    @Autowired
    private IUsuarioDao   usuarioDao;

    @Override
    public void quickSale (QuickSaleDTO dto) {
        MedioPago pago = this.medioPagoDao.findByCode(dto.getSaleType());
        Articulo articulo = this.articulodao.findByCode(dto.getProductCode());
        Usuario user = this.usuarioDao.findByAlias(dto.getUser());
        VentaBuilder builder = new VentaBuilder();
        builder.withMedioPago(pago);
        builder.withFecha(DateUtil.createNow());
        builder.withImporte(articulo.getPrecio());
        builder.withUsuario(user);
        Venta venta = builder.build();
        venta.addArticulo(articulo, 1);
        venta.setCodigo(createSaleCode(venta, true));
        this.ventaDao.persist(venta);
    }

    @Override
    public String createSaleCode (Venta venta) {
        return createSaleCode(venta, false);
    }

    private String createSaleCode (Venta venta, boolean isQuick) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(venta.getFecha());
        String type = isQuick ? QUICK_SALE : NORMAL_SALE;
        String year = "" + calendar.get(Calendar.YEAR);
        String pago = venta.getMedioPago().getCodigo();
        String id = "" + this.ventaDao.getNextId();
        StringBuilder builder = new StringBuilder();
        builder
            .append(type)
            .append(tail(year, 2))
            .append(pago)
            .append(fill(tail(id, 4), "0", 4));
        return builder.toString();
    }

    private String tail (String token, int length) {
        if (token.length() <= length) {
            return token;
        }
        return token.substring(token.length() - length, token.length());
    }

    private String fill (String token, String padd, int length) {
        if (token.length() >= length) {
            return token;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length - token.length(); i++) {
            builder.append(padd);
        }
        builder.append(token);
        return builder.toString();
    }
}