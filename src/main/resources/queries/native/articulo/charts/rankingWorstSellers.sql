  SELECT a.modelo AS modelo, sum(va.cantidad) AS cantidad
    FROM art_venta_articulo va
         JOIN art_venta v ON va.fk_venta = v.id
         JOIN art_articulo a ON a.id = va.fk_articulo
GROUP BY modelo
ORDER BY cantidad ASC