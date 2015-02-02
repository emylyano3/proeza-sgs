CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `vw_detalle_compras` AS
    SELECT 
        `c`.`codigo` AS `Compra`,
        `a`.`codigo` AS `Articulo`,
        `r`.`nombre` AS `Rubro`,
        `ca`.`nombre` AS `Categoria`,
        `cl`.`nombre` AS `Clase`,
        `va`.`cantidad` AS `Cantidad`,
        `c`.`fecha` AS `Fecha`,
        `mp`.`nombre` AS `Medio de Pago`
    FROM
        ((((((`art_compra` `c`
        JOIN `art_venta_articulo` `va` ON ((`va`.`fk_venta` = `c`.`id`)))
        JOIN `art_articulo` `a` ON ((`a`.`id` = `va`.`fk_articulo`)))
        JOIN `cmn_medio_pago` `mp` ON ((`mp`.`id` = `c`.`fk_medio_pago`)))
        JOIN `art_clase` `cl` ON ((`cl`.`id` = `a`.`fk_clase`)))
        JOIN `art_clase` `ca` ON ((`ca`.`id` = `a`.`fk_clase`)))
        JOIN `art_rubro` `r` ON ((`r`.`id` = `a`.`fk_rubro`)))