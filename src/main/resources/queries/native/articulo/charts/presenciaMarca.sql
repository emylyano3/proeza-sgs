  SELECT m.nombre AS marca, sum(a.cantidad) AS stock
    FROM sgs_proeza_db.art_articulo a
         JOIN sgs_proeza_db.art_marca m ON m.id = a.fk_marca
GROUP BY m.nombre
ORDER BY stock DESC