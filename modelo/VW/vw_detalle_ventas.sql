CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `vw_detalle_ventas` AS
    SELECT 
        `v`.`codigo` AS `Venta`,
        `a`.`codigo` AS `Articulo`,
        `r`.`nombre` AS `Rubro`,
        `c`.`nombre` AS `Clase`,
        `t`.`nombre` AS `Tipo`,
        `va`.`cantidad` AS `Cantidad`,
        `v`.`fecha` AS `Fecha`,
        `mp`.`nombre` AS `Medio de Pago`
    FROM
        ((((((`art_venta` `v`
        JOIN `art_venta_articulo` `va` ON ((`va`.`fk_venta` = `v`.`id`)))
        JOIN `art_articulo` `a` ON ((`a`.`id` = `va`.`fk_articulo`)))
        JOIN `cmn_medio_pago` `mp` ON ((`mp`.`id` = `v`.`fk_medio_pago`)))
        JOIN `art_clase` `c` ON ((`c`.`id` = `a`.`fk_clase`)))
        JOIN `art_tipo` `t` ON ((`t`.`id` = `a`.`fk_tipo`)))
        JOIN `art_rubro` `r` ON ((`r`.`id` = `a`.`fk_rubro`)))