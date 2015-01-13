CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `vw_detalle_articulos` AS
    SELECT 
        `a`.`codigo` AS `Codigo`,
        `r`.`nombre` AS `Rubro`,
        `c`.`nombre` AS `Clase`,
        `t`.`nombre` AS `Tipo`,
        `m`.`nombre` AS `Marca`,
        `a`.`modelo` AS `Modelo`,
        `a`.`descripcion` AS `Descripcion`,
        `a`.`costo` AS `Costo`,
        `a`.`precio` AS `Precio`,
        `a`.`cantidad` AS `Cantidad`
    FROM
        ((((`articulo` `a`
        JOIN `rubro` `r` ON ((`r`.`id` = `a`.`fk_rubro`)))
        JOIN `clase` `c` ON ((`c`.`id` = `a`.`fk_clase`)))
        JOIN `tipo` `t` ON ((`t`.`id` = `a`.`fk_tipo`)))
        LEFT JOIN `marca` `m` ON ((`m`.`id` = `a`.`fk_marca`)))