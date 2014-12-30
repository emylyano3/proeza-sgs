/* Sistema */
	
	/* Pagina */
	insert into sgs_proeza_db.pagina (codigo, nombre, descripcion) values ('P_HOME','home','Inicio de la aplicaci�n');
	insert into sgs_proeza_db.pagina (codigo, nombre, descripcion) values ('P_ADMIN','admin','Inicio de la aplicaci�n');
	insert into sgs_proeza_db.pagina (codigo, nombre, descripcion) values ('P_REGISTER','register','Registro de usuario');
	insert into sgs_proeza_db.pagina (codigo, nombre, descripcion) values ('P_LOGIN','login','Ingreso de usuario al sistema');

	/* Menu*/
	insert into sgs_proeza_db.menu(codigo, texto, tooltip, tipo, icono) values ('M_LEFT_MAIN','Men� Principal', 'Para acceder al inicio de la aplicaci�n','SIDE_MENU_LEFT', null);
	
	/* Asociacion pagina - menu*/
	insert into sgs_proeza_db.menu_pagina (id, fk_pagina, fk_menu) values (1, 1, 1);
	insert into sgs_proeza_db.menu_pagina (id, fk_pagina, fk_menu) values (2, 2, 1);
	insert into sgs_proeza_db.menu_pagina (id, fk_pagina, fk_menu) values (3, 3, 1);

	/* Item de Menu */
	insert into sgs_proeza_db.item(codigo, texto, tooltip, link, icono) values ('MI_HOME', 'Inicio', 'Para acceder al inicio de la aplicaci�n', 'home', 'icon-home');
	insert into sgs_proeza_db.item(codigo, texto, tooltip, link, icono) values ('MI_USER_ADMIN', 'Usuarios', 'Para acceder a la gestion de usuarios','admin', 'icon icon-users');
	
	/* Menu - Item de Menu */
	insert into sgs_proeza_db.menu_item(fk_menu, fk_item, index) values (1, 1, 0);
	insert into sgs_proeza_db.menu_item(fk_menu, fk_item, index) values (1, 2, 1);

	/* Item de Menu - Rol*/
	insert into sgs_proeza_db.item_rol(id, fk_item, fk_rol) values (1, 2, 1);
	insert into sgs_proeza_db.item_rol(id, fk_item, fk_rol) values (2, 2, 3);

/* Sistema */