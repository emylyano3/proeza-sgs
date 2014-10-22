DROP VIEW IF EXISTS  sgs_proeza_db.vw_detalle_ventas;
CREATE VIEW sgs_proeza_db.vw_detalle_ventas
AS
   SELECT v.codigo AS Venta,
          a.codigo AS Articulo,
          r.nombre AS Rubro,
          ca.nombre AS Categoria,
          cl.nombre AS Clase,
          va.cantidad AS Cantidad,
          v.fecha AS Fecha,
          mp.nombre AS 'Medio de Pago'
     FROM venta v
          JOIN venta_articulo va ON va.fk_venta = v.id
          JOIN articulo a ON a.id = va.fk_articulo
          JOIN medio_pago mp ON mp.id = v.fk_medio_pago
          JOIN clase cl ON cl.id = a.fk_clase
          JOIN categoria ca ON ca.id = a.fk_categoria
          JOIN rubro r ON r.id = a.fk_rubro;
