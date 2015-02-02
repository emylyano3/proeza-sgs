CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `vw_rubro_clase` AS
    SELECT 
        `r`.`id` AS `Id Rubro`,
        `r`.`codigo` AS `Codigo Rubro`,
        `r`.`nombre` AS `Nombre Rubro`,
        `r`.`descripcion` AS `Descipcion Rubro`,
        `c`.`id` AS `Id Clase`,
        `c`.`codigo` AS `Codigo Clase`,
        `c`.`nombre` AS `Nombre Clase`,
        `c`.`descripcion` AS `Descripcion Clase`
    FROM
        (`art_clase` `c`
        JOIN `art_rubro` `r` ON ((`r`.`id` = `c`.`fk_rubro`)))
    ORDER BY `c`.`codigo`