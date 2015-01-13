/* Sistema */
	
	/* Pagina */
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('usuario','Página Usuario','Página con la que se realiza la adminsitración de usuarios');
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('home','Página Inicio','Inicio de la aplicación');
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('register','Página de Registro', '');
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('login','Página de Ingreso', '');
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('logout','Página de Salida', '');

	/* Pagina - Rol */
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (1, 1, 11);
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (2, 1, 12);
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (3, 1, 13);

	/* Menu*/
	insert into sgs_proeza_db.sys_menu(codigo, texto, tooltip, tipo, icono) values ('M_LEFT_MAIN','Menú Principal', 'Para acceder al inicio de la aplicación','SIDE_MENU_LEFT', null);
	
	/* Asociacion pagina - menu*/
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (1, 1, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (2, 2, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (3, 3, 1);

	/* Item de Menu */
	insert into sgs_proeza_db.sys_item(codigo, texto, tooltip, link, icono) values ('MI_HOME', 'Inicio', 'Para acceder al inicio de la aplicación', 'home', 'icon-home');
	insert into sgs_proeza_db.sys_item(codigo, texto, tooltip, link, icono) values ('MI_USUARIO', 'Usuarios', 'Para acceder a la gestion de usuarios','usuario', 'icon icon-users');
	
	/* Menu - Item de Menu */
	insert into sgs_proeza_db.sys_menu_item(fk_menu, fk_item, indice) values (1, 1, 0);
	insert into sgs_proeza_db.sys_menu_item(fk_menu, fk_item, indice) values (1, 2, 1);

	/* Item de Menu - Rol*/
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (1, 2, 11);
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (2, 2, 12);
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (3, 2, 13);

/* Sistema */