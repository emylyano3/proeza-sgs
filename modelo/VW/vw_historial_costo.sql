CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `vw_historial_costo` AS
    SELECT 
        `a`.`id` AS `Id Articulo`,
        `a`.`codigo` AS `Cod Articulo`,
        `m`.`valor` AS `Cantidad`,
        `m`.`f_movimiento` AS `Fecha Modificacion`
    FROM
        ((`art_movimiento` `m`
        JOIN `art_articulo` `a` ON ((`a`.`id` = `m`.`fk_articulo`)))
        JOIN `art_tipo_movimiento` `tm` ON ((`m`.`fk_tipo` = `tm`.`id`)))
    WHERE
        (`tm`.`codigo` = 'MC')