DROP TRIGGER IF EXISTS sgs_proeza_db.tr_registrar_movimiento_articulo;

CREATE TRIGGER sgs_proeza_db.tr_registrar_movimiento_articulo
   AFTER UPDATE
   ON sgs_proeza_db.articulo
   FOR EACH ROW
BEGIN
   DECLARE id_tipo_mov   INT;
   DECLARE nuevo_valor   VARCHAR(100);

   IF NEW.cantidad <> OLD.cantidad
   THEN
      /* Busco el tipo de movimiento para la modificacion de stock */
      SELECT id
        INTO id_tipo_mov
        FROM tipo_movimiento tm
       WHERE tm.codigo = 'MS';

      /* Obtengo el nuevo valor del stock */
      SET nuevo_valor = NEW.cantidad;

      /* Inserto el movimiento */
      INSERT INTO movimiento(fk_tipo,
                             fk_articulo,
                             f_movimiento,
                             valor)
           VALUES (id_tipo_mov,
                   NEW.id,
                   sysdate(),
                   nuevo_valor);
   END IF;

   IF NEW.costo <> OLD.costo
   THEN
      /* Busco el tipo de movimiento para la modificacion de costo */
      SELECT id
        INTO id_tipo_mov
        FROM tipo_movimiento tm
       WHERE tm.codigo = 'MC';

      /* Obtengo el nuevo valor del costo */
      SET nuevo_valor = NEW.costo;

      /* Inserto el movimiento */
      INSERT INTO movimiento(fk_tipo,
                             fk_articulo,
                             f_movimiento,
                             valor)
           VALUES (id_tipo_mov,
                   NEW.id,
                   sysdate(),
                   nuevo_valor);
   END IF;

   IF NEW.precio <> OLD.precio
   THEN
      /* Busco el tipo de movimiento para la modificacion de precio */
      SELECT id
        INTO id_tipo_mov
        FROM tipo_movimiento tm
       WHERE tm.codigo = 'MP';

      /* Obtengo el nuevo valor del precio */
      SET nuevo_valor = NEW.precio;

      /* Inserto el movimiento */
      INSERT INTO movimiento(fk_tipo,
                             fk_articulo,
                             f_movimiento,
                             valor)
           VALUES (id_tipo_mov,
                   NEW.id,
                   sysdate(),
                   nuevo_valor);
   END IF;
END;
