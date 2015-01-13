/* Sistema */
	
	/* Pagina */
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('usuario','P�gina Usuario','P�gina con la que se realiza la adminsitraci�n de usuarios');
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('home','P�gina Inicio','Inicio de la aplicaci�n');
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('register','P�gina de Registro', '');
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('login','P�gina de Ingreso', '');
	insert into sgs_proeza_db.sys_pagina (codigo, nombre, descripcion) values ('logout','P�gina de Salida', '');

	/* Pagina - Rol */
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (1, 1, 11);
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (2, 1, 12);
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (3, 1, 13);

	/* Menu*/
	insert into sgs_proeza_db.sys_menu(codigo, texto, tooltip, tipo, icono) values ('M_LEFT_MAIN','Men� Principal', 'Para acceder al inicio de la aplicaci�n','SIDE_MENU_LEFT', null);
	
	/* Asociacion pagina - menu*/
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (1, 1, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (2, 2, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (3, 3, 1);

	/* Item de Menu */
	insert into sgs_proeza_db.sys_item(codigo, texto, tooltip, link, icono) values ('MI_HOME', 'Inicio', 'Para acceder al inicio de la aplicaci�n', 'home', 'icon-home');
	insert into sgs_proeza_db.sys_item(codigo, texto, tooltip, link, icono) values ('MI_USUARIO', 'Usuarios', 'Para acceder a la gestion de usuarios','usuario', 'icon icon-users');
	
	/* Menu - Item de Menu */
	insert into sgs_proeza_db.sys_menu_item(fk_menu, fk_item, indice) values (1, 1, 0);
	insert into sgs_proeza_db.sys_menu_item(fk_menu, fk_item, indice) values (1, 2, 1);

	/* Item de Menu - Rol*/
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (1, 2, 11);
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (2, 2, 12);
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (3, 2, 13);

/* Sistema */