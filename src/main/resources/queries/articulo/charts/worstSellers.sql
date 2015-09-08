  SELECT a.modelo AS modelo, sum(va.cantidad) AS cantidad
    FROM sgs_proeza_db.art_venta_articulo va
         JOIN sgs_proeza_db.art_venta v ON va.fk_venta = v.id
         JOIN sgs_proeza_db.art_articulo a ON a.id = va.fk_articulo
GROUP BY modelo
ORDER BY cantidad ASC