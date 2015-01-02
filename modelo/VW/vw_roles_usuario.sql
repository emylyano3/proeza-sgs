DROP VIEW IF EXISTS seg_proeza_db.vw_roles_usuario;

CREATE VIEW seg_proeza_db.vw_roles_usuario
AS
   SELECT u.alias, r.codigo
     FROM seg_proeza_db.usuario u
          JOIN seg_proeza_db.usuario_rol ur ON ur.fk_usuario = u.id
          JOIN seg_proeza_db.rol r ON r.id = ur.fk_rol;
