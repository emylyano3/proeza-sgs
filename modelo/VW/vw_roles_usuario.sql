DROP VIEW IF EXISTS sgs_proeza_db.vw_roles_usuario;

CREATE VIEW sgs_proeza_db.vw_roles_usuario
AS
   SELECT u.alias, r.codigo
     FROM sgs_proeza_db.usuario u
          JOIN sgs_proeza_db.usuario_rol ur ON ur.fk_usuario = u.id
          JOIN sgs_proeza_db.rol r ON r.id = ur.fk_rol;
