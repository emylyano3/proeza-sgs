/* Sistema */
	
	/* Pagina */
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('P_HOME','home','Inicio de la aplicación');
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('P_ADMIN','admin','Inicio de la aplicación');
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('P_REGISTER','register','Registro de usuario');
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('P_LOGIN','login','Ingreso de usuario al sistema');

	/* Menu*/
	insert into sgs_proeza_db.sys_menu(codigo, texto, tooltip, tipo, icono) values ('M_LEFT_MAIN','Menú Principal', 'Para acceder al inicio de la aplicación','SIDE_MENU_LEFT', null);
	
	/* Asociacion pagina - menu*/
	insert into sgs_proeza_db.sys_menu_pagina (id, fk_pagina, fk_menu) values (1, 1, 1);
	insert into sgs_proeza_db.sys_menu_pagina (id, fk_pagina, fk_menu) values (2, 2, 1);
	insert into sgs_proeza_db.sys_menu_pagina (id, fk_pagina, fk_menu) values (3, 3, 1);

	/* Item de Menu */
	insert into sgs_proeza_db.sys_item(codigo, texto, tooltip, link, icono) values ('MI_HOME', 'Inicio', 'Para acceder al inicio de la aplicación', 'home', 'icon-home');
	insert into sgs_proeza_db.sys_item(codigo, texto, tooltip, link, icono) values ('MI_USER_ADMIN', 'Usuarios', 'Para acceder a la gestion de usuarios','admin', 'icon icon-users');
	
	/* Menu - Item de Menu */
	insert into sgs_proeza_db.sys_menu_item(fk_menu, fk_item, index) values (1, 1, 0);
	insert into sgs_proeza_db.sys_menu_item(fk_menu, fk_item, index) values (1, 2, 1);

	/* Item de Menu - Rol*/
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (1, 2, 1);
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (2, 2, 3);

/* Sistema */