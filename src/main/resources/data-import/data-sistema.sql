/* Sistema */
	
	/* Pagina */
	insert into sgs_proeza_db.sys_pagina (id, grupo, nombre, descripcion) values (1, 'usuario', 'home' ,'Página de inicio de las funcionalidades de usuario');
	insert into sgs_proeza_db.sys_pagina (id, grupo, nombre, descripcion) values (2, 'usuario', 'listado' ,'Página con la que se realiza el listado de usuarios');
	insert into sgs_proeza_db.sys_pagina (id, grupo, nombre, descripcion) values (3, 'usuario', 'admin' ,'Página con la que se realiza la adminsitración de usuarios');
	insert into sgs_proeza_db.sys_pagina (id, grupo, nombre, descripcion) values (4, 'usuario', 'estadisticas' ,'Página con la que se accede a las estadísticas de usuarios');
	insert into sgs_proeza_db.sys_pagina (id, grupo, nombre, descripcion) values (5, 'root', 'home' ,'Inicio de la aplicación');
	insert into sgs_proeza_db.sys_pagina (id, grupo, nombre, descripcion) values (6, 'root', 'register','Página de registro de usuario');
	insert into sgs_proeza_db.sys_pagina (id, grupo, nombre, descripcion) values (7, 'root', 'login','Página de Ingreso al sistema');
	insert into sgs_proeza_db.sys_pagina (id, grupo, nombre, descripcion) values (8, 'root', 'logout','Página que se muestra al usuario luego de su salida del sistema');
	insert into sgs_proeza_db.sys_pagina (id, grupo, nombre, descripcion) values (9, 'articulo', 'home' ,'Página inicial de las funcionalidades de articulo');
	insert into sgs_proeza_db.sys_pagina (id, grupo, nombre, descripcion) values (10, 'articulo', 'listado' ,'Página donde se ofrecen funcionalidades de listado de articulos');
	insert into sgs_proeza_db.sys_pagina (id, grupo, nombre, descripcion) values (11, 'articulo', 'admin' ,'Página donde se administran los articulos');

	/* Pagina - Rol */
	/* Paginas Usuario */
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (1, 1, 41);
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (2, 2, 41);
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (3, 3, 41);
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (4, 4, 41);
	
	/* Paginas Articulo */
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (5, 9, 31);
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (6, 10, 31);
	insert into sgs_proeza_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (7, 11, 31);

	/* Menu*/
	insert into sgs_proeza_db.sys_menu(id, codigo, texto, tooltip, tipo, icono) values (1, 'M_LEFT_MAIN','Menú Principal', 'Para acceder al inicio de la aplicación','SIDE_MENU_LEFT', null);
	
	/* Asociacion pagina - menu*/
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (1, 1, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (2, 2, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (3, 3, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (4, 4, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (5, 5, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (6, 6, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (7, 9, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (8, 10, 1);
	insert into sgs_proeza_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (9, 11, 1);

	/* Item de Menu */
	insert into sgs_proeza_db.sys_item(id, codigo, texto, tooltip, link, icono) values (1, 'MI_HOME', 'Inicio', 'Para acceder al inicio de la aplicación', '/home', 'fa fa-home');
	insert into sgs_proeza_db.sys_item(id, codigo, texto, tooltip, link, icono) values (2, 'MI_USUA_HOME', 'Usuario Home', 'Para acceder al home de usuario','/usuario/home', 'fa fa-home');
	insert into sgs_proeza_db.sys_item(id, codigo, texto, tooltip, link, icono) values (3, 'MI_ARTI_HOME', 'Articulo Home', 'Para acceder al home de articlo','/articulo/home', 'fa fa-home');
	insert into sgs_proeza_db.sys_item(id, codigo, texto, tooltip, link, icono) values (4, 'MI_ARTI_LIST', 'Articulo Listado', 'Para acceder al listado de articulos','/articulo/listado', 'fa fa-list');
	
	/* Menu - Item de Menu */
	insert into sgs_proeza_db.sys_menu_item(fk_menu, fk_item, indice) values (1, 1, 0);
	insert into sgs_proeza_db.sys_menu_item(fk_menu, fk_item, indice) values (1, 2, 1);
	insert into sgs_proeza_db.sys_menu_item(fk_menu, fk_item, indice) values (1, 3, 2);
	insert into sgs_proeza_db.sys_menu_item(fk_menu, fk_item, indice) values (1, 4, 3);

	/* Item de Menu - Rol*/
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (1, 2, 41);
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (2, 2, 42);
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (3, 2, 43);
	
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (4, 3, 31);
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (5, 3, 32);
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (6, 3, 33);

	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (7, 4, 31);
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (8, 4, 32);
	insert into sgs_proeza_db.sys_item_rol(id, fk_item, fk_rol) values (9, 4, 33);

/* Sistema */