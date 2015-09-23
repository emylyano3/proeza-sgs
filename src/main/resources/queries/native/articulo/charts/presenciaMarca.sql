  SELECT m.nombre AS marca, sum(a.cantidad) AS stock
    FROM art_articulo a
         JOIN art_marca m ON m.id = a.fk_marca
GROUP BY m.nombre
ORDER BY stock DESC