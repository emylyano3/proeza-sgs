/* Usuarios */
insert into seg_proeza_db.usuario (alias, nombre, apellido, email, password) values ('admin','admin','admin', 'admin@proeza.com.ar', 'admin');
/* Roles */
insert into seg_proeza_db.rol (codigo, nombre, descripcion) values ('ADMIN','admin','Rol de administrador del sistema (God)');
insert into seg_proeza_db.rol (codigo, nombre, descripcion) values ('USER','user','Rol de usuario básico');

/* Asociacion de Usuarios y roles */
insert into seg_proeza_db.usuario_rol (fk_rol, fk_usuario) values (1, 1);
insert into seg_proeza_db.usuario_rol (fk_rol, fk_usuario) values (2, 1);

/* Negocio */
insert into sgs_proeza_db.clase (codigo, nombre, descripcion) values ('REEL','Reel','Reeles en general');