DROP VIEW IF EXISTS sgs_proeza_db.vw_rubro_categoria;

CREATE VIEW sgs_proeza_db.vw_rubro_categoria
AS
     SELECT r.id as 'Id Rubro',
            r.codigo as 'Codigo Rubro',
            r.nombre 'Nombre Rubro',
            r.descripcion 'Descipcion Rubro',
            ca.id as 'Id Categoria',
            ca.codigo as 'Codigo Categoria',
            ca.nombre as 'Nombre Categoria',
            ca.descripcion  as 'Descripcion Categoria'
       FROM sgs_proeza_db.categoria ca
            CROSS JOIN rubro r
            JOIN sgs_proeza_db.rubro_categoria
               ON (    ca.id = fk_categoria
                   AND r.id = fk_rubro)
   ORDER BY ca.codigo;
