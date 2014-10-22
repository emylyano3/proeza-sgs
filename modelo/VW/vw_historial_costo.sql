DROP VIEW IF EXISTS sgs_proeza_db.vw_historial_costo;

CREATE VIEW sgs_proeza_db.vw_historial_costo
AS
   SELECT a.id AS 'Id Articulo',
          a.codigo as 'Cod Articulo', 
          m.valor AS Cantidad,
          m.f_movimiento AS 'Fecha Modificacion'
     FROM movimiento m
          JOIN articulo a ON a.id = m.fk_articulo
          JOIN tipo_movimiento tm ON m.fk_tipo = tm.id
    WHERE tm.codigo = 'MC';
