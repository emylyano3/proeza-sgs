DROP PROCEDURE IF EXISTS sgs_proeza_db.sp_quitar_del_stock;

CREATE PROCEDURE sgs_proeza_db.sp_quitar_del_stock (
   IN id_articulo   INT(10),
   IN cantidad      INT(6))
   BEGIN
      UPDATE articulo ar
         SET ar.cantidad = (ar.cantidad - cantidad)
       WHERE ar.id = id_articulo;
   END;
