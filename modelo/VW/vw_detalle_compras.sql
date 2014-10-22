DROP VIEW IF EXISTS  sgs_proeza_db.vw_detalle_compras;
CREATE VIEW sgs_proeza_db.vw_detalle_compras
AS
   SELECT c.codigo AS Compra,
          a.codigo AS Articulo,
          r.nombre AS Rubro,
          ca.nombre AS Categoria,
          cl.nombre AS Clase,
          va.cantidad AS Cantidad,
          c.fecha AS Fecha,
          mp.nombre AS 'Medio de Pago'
     FROM compra c
          JOIN venta_articulo va ON va.fk_venta = c.id
          JOIN articulo a ON a.id = va.fk_articulo
          JOIN medio_pago mp ON mp.id = c.fk_medio_pago
          JOIN clase cl ON cl.id = a.fk_clase
          JOIN categoria ca ON ca.id = a.fk_categoria
          JOIN rubro r ON r.id = a.fk_rubro;
