DROP TRIGGER IF EXISTS sgs_proeza_db.tr_actualizar_stock_por_nueva_venta;

CREATE TRIGGER sgs_proeza_db.tr_actualizar_stock_por_nueva_venta
   AFTER INSERT
   ON sgs_proeza_db.venta_articulo
   FOR EACH ROW
BEGIN
	Call sp_quitar_del_stock(NEW.fk_articulo, NEW.cantidad);
END;
;


DROP TRIGGER IF EXISTS sgs_proeza_db.tr_actualizar_stock_por_venta_modificada;

CREATE TRIGGER sgs_proeza_db.tr_actualizar_stock_por_venta_modificada
   AFTER UPDATE
   ON sgs_proeza_db.venta_articulo
   FOR EACH ROW
BEGIN
	Call sp_quitar_del_stock(NEW.fk_articulo, NEW.cantidad - OLD.cantidad);
END;
;


DROP TRIGGER IF EXISTS sgs_proeza_db.tr_actualizar_stock_por_venta_anulada;

CREATE TRIGGER sgs_proeza_db.tr_actualizar_stock_por_venta_anulada
   AFTER DELETE 
   ON sgs_proeza_db.venta_articulo
   FOR EACH ROW
BEGIN
	Call sp_quitar_del_stock(OLD.fk_articulo, - OLD.cantidad);
END;
;

DROP TRIGGER IF EXISTS sgs_proeza_db.tr_actualizar_stock_por_nueva_compra;

CREATE TRIGGER sgs_proeza_db.tr_actualizar_stock_por_nueva_compra
   AFTER INSERT
   ON sgs_proeza_db.compra_articulo
   FOR EACH ROW
BEGIN
   CALL sp_quitar_del_stock(NEW.fk_articulo, - NEW.cantidad);
END;

;


DROP TRIGGER IF EXISTS sgs_proeza_db.tr_actualizar_stock_por_compra_modificada;

CREATE TRIGGER sgs_proeza_db.tr_actualizar_stock_por_compra_modificada
   AFTER UPDATE
   ON sgs_proeza_db.compra_articulo
   FOR EACH ROW
BEGIN
   CALL sp_quitar_del_stock(NEW.fk_articulo,  OLD.cantidad - NEW.cantidad);
END;

;


DROP TRIGGER IF EXISTS sgs_proeza_db.tr_actualizar_stock_por_compra_anulada;

CREATE TRIGGER sgs_proeza_db.tr_actualizar_stock_por_compra_anulada
   AFTER DELETE
   ON sgs_proeza_db.compra_articulo
   FOR EACH ROW
BEGIN
   CALL sp_quitar_del_stock(OLD.fk_articulo, OLD.cantidad);
END;

;