/* Seguridad */

	/* Usuarios */
	insert into seg_proeza_db.usuario (alias, nombre, apellido, email, password) values ('admin','admin','admin', 'admin@proeza.com.ar', 'admin');
	insert into seg_proeza_db.usuario (alias, nombre, apellido, email, password) values ('user','user','user', 'user@proeza.com.ar', 'user');
	
	/* Roles */
	insert into seg_proeza_db.rol (codigo, nombre, descripcion) values ('ROLE_ADMIN','admin','Rol de administrador del sistema (God)');
	insert into seg_proeza_db.rol (codigo, nombre, descripcion) values ('ROLE_USER','user','Rol de usuario básico');
	insert into seg_proeza_db.rol (codigo, nombre, descripcion) values ('ROLE_USER_ADMIN','user_admin','Rol de administrador de usuarios');
	
	/* Asociacion de Usuarios y roles */
	insert into seg_proeza_db.usuario_rol (id, fk_rol, fk_usuario) values (1, 1, 1);
	insert into seg_proeza_db.usuario_rol (id, fk_rol, fk_usuario) values (2, 2, 1);
	insert into seg_proeza_db.usuario_rol (id, fk_rol, fk_usuario) values (3, 3, 1);
	insert into seg_proeza_db.usuario_rol (id, fk_rol, fk_usuario) values (4, 2, 2);
	
/* Seguridad */

/* Sistema */
	
	/* Pagina */
	insert into sgs_proeza_db.pagina (codigo, nombre, descripcion) values ('P_HOME','home','Inicio de la aplicación');
	insert into sgs_proeza_db.pagina (codigo, nombre, descripcion) values ('P_ADMIN','admin','Inicio de la aplicación');
	insert into sgs_proeza_db.pagina (codigo, nombre, descripcion) values ('P_REGISTER','register','Registro de usuario');
	insert into sgs_proeza_db.pagina (codigo, nombre, descripcion) values ('P_LOGIN','login','Ingreso de usuario al sistema');

	/* Menu*/
	insert into sgs_proeza_db.menu(codigo, texto, tooltip, tipo, icono) values ('M_LEFT_MAIN','Menú Principal', 'Para acceder al inicio de la aplicación','SIDE_MENU_LEFT', null);
	
	/* Asociacion pagina - menu*/
	insert into sgs_proeza_db.menu_pagina (id, fk_pagina, fk_menu) values (1, 1, 1);
	insert into sgs_proeza_db.menu_pagina (id, fk_pagina, fk_menu) values (2, 2, 1);
	insert into sgs_proeza_db.menu_pagina (id, fk_pagina, fk_menu) values (3, 3, 1);

	/* Item de Menu */
	insert into sgs_proeza_db.item(codigo, texto, tooltip, link, icono) values ('MI_HOME', 'Inicio', 'Para acceder al inicio de la aplicación', 'home', 'icon-home');
	insert into sgs_proeza_db.item(codigo, texto, tooltip, link, icono) values ('MI_USER_ADMIN', 'Usuarios', 'Para acceder a la gestion de usuarios','admin', 'icon icon-users');
	
	/* Menu - Item de Menu */
	insert into sgs_proeza_db.menu_item(fk_menu, fk_item, index) values (1, 1, 0);
	insert into sgs_proeza_db.menu_item(fk_menu, fk_item, index) values (1, 2, 1);

	/* Item de Menu - Rol*/
	insert into sgs_proeza_db.item_rol(id, fk_item, fk_rol) values (1, 2, 1);
	insert into sgs_proeza_db.item_rol(id, fk_item, fk_rol) values (2, 2, 3);

/* Sistema */

/* Negocio */

	insert into sgs_proeza_db.clase (codigo, nombre, descripcion) values ('REEL','Reel','Reeles en general');
	
/* Negocio */