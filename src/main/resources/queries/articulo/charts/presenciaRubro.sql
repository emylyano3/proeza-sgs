  SELECT r.nombre AS rubro, sum(a.cantidad) AS stock
    FROM sgs_proeza_db.art_articulo a
         JOIN sgs_proeza_db.art_rubro r ON r.id = a.fk_rubro
GROUP BY r.nombre
ORDER BY stock DESC