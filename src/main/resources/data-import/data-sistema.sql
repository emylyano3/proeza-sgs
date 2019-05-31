/* Pagina */
	insert into sgs_proeza_test_db.sys_pagina (id, grupo, nombre, fk_i18n_descripcion, fk_i18n_titulo, fk_i18n_subtitulo) values (3, 'usuario', 'admin', 24, 15, 20);
	insert into sgs_proeza_test_db.sys_pagina (id, grupo, nombre, fk_i18n_descripcion, fk_i18n_titulo, fk_i18n_subtitulo) values (4, 'usuario', 'estadisticas', 25, 15, 21);
	insert into sgs_proeza_test_db.sys_pagina (id, grupo, nombre, fk_i18n_descripcion, fk_i18n_titulo, fk_i18n_subtitulo) values (5, 'root', 'home', 26, 16, null);
	insert into sgs_proeza_test_db.sys_pagina (id, grupo, nombre, fk_i18n_descripcion, fk_i18n_titulo, fk_i18n_subtitulo) values (6, 'root', 'register', 27, null, null);
	insert into sgs_proeza_test_db.sys_pagina (id, grupo, nombre, fk_i18n_descripcion, fk_i18n_titulo, fk_i18n_subtitulo) values (7, 'root', 'login', 28, null, null);
	insert into sgs_proeza_test_db.sys_pagina (id, grupo, nombre, fk_i18n_descripcion, fk_i18n_titulo, fk_i18n_subtitulo) values (8, 'root', 'logout', 29, null, null);
	insert into sgs_proeza_test_db.sys_pagina (id, grupo, nombre, fk_i18n_descripcion, fk_i18n_titulo, fk_i18n_subtitulo) values (2, 'articulo', 'listado', 31, 17, 19);
	insert into sgs_proeza_test_db.sys_pagina (id, grupo, nombre, fk_i18n_descripcion, fk_i18n_titulo, fk_i18n_subtitulo) values (1, 'articulo', 'admin', 33, 17, 20);
	insert into sgs_proeza_test_db.sys_pagina (id, grupo, nombre, fk_i18n_descripcion, fk_i18n_titulo, fk_i18n_subtitulo) values (9, 'articulo', 'stats', 32, 17, 21);
/* Pagina */

/* Pagina - Rol */
	/* Paginas Usuario */
	insert into sgs_proeza_test_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (1, 3, 41);
	insert into sgs_proeza_test_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (2, 4, 41);
	
	/* Paginas Articulo */
	insert into sgs_proeza_test_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (3, 2, 31);
	insert into sgs_proeza_test_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (4, 9, 31);
	insert into sgs_proeza_test_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (5, 1, 31);

	insert into sgs_proeza_test_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (6, 2, 33);
	insert into sgs_proeza_test_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (7, 9, 33);

	insert into sgs_proeza_test_db.sys_pagina_rol (id, fk_pagina, fk_rol) values (8, 2, 32);
/* Pagina - Rol */

/* Menu */
	insert into sgs_proeza_test_db.sys_menu(id, codigo, texto, tooltip, tipo, icono) values (1, 'MAIN','Menú Principal', 'Para acceder al inicio de la aplicación','SIDE_LEFT', null);
	insert into sgs_proeza_test_db.sys_menu(id, codigo, texto, tooltip, tipo, icono) values (2, 'PROD_LIST_GRID', null, null, 'CONTEXT', null);
/* Menu */

/* Asociacion pagina - menu */
	insert into sgs_proeza_test_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (1, 3, 1);
	insert into sgs_proeza_test_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (2, 4, 1);
	insert into sgs_proeza_test_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (3, 5, 1);
	insert into sgs_proeza_test_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (4, 6, 1);
	insert into sgs_proeza_test_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (5, 2, 1);
	insert into sgs_proeza_test_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (6, 9, 1);
	insert into sgs_proeza_test_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (7, 1, 1);

	insert into sgs_proeza_test_db.sys_pagina_menu (id, fk_pagina, fk_menu) values (8, 2, 2);
/* Asociacion pagina - menu */

/* Item de Menu */
	/* Root */
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (1, 'MI_HOME', 1, 6, '/home', 'fa fa-home');
	
	/* Usuario */
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (2, 'MI_USUA', 2, null,'#', 'fa fa-users');
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (9, 'MI_USUA_ADMIN', 3, 8,'/usuario/admin', 'fa fa-home');
	
	/* Articulo */
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (4, 'MI_ARTI', 14, null,'#', 'fa fa-barcode');
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (6, 'MI_ARTI_LIST', 4, 11,'/articulo/listado', '');
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (7, 'MI_ARTI_STATS', 5, 12,'/articulo/stats', '');
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (8, 'MI_ARTI_ADMIN', 3, 13,'/articulo/admin', '');
	
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (11, 'CM_PLG_SALES', '34', null, null, null);
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (12, 'CM_PLG_QCASH_SALE', '35', null, null, null);
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (13, 'CM_PLG_QCRED_SALE', '36', null, null, null);
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (14, 'CM_PLG_QDEBI_SALE', '37', null, null, null);
	insert into sgs_proeza_test_db.sys_item(id, codigo, fk_i18n_texto, fk_i18n_tooltip, link, icono) values (15, 'CM_PLG_SEE_IMAGES', '38', null, null, null);

/* Item de Menu */

/* Item - Sub Item */
insert into sgs_proeza_test_db.sys_item_subitem(id, fk_item, fk_subitem, indice) values (6, 2, 9, 1);

insert into sgs_proeza_test_db.sys_item_subitem(id, fk_item, fk_subitem, indice) values (3, 4, 6, 1);
insert into sgs_proeza_test_db.sys_item_subitem(id, fk_item, fk_subitem, indice) values (4, 4, 7, 2);
insert into sgs_proeza_test_db.sys_item_subitem(id, fk_item, fk_subitem, indice) values (5, 4, 8, 3);

insert into sgs_proeza_test_db.sys_item_subitem(id, fk_item, fk_subitem, indice) values (8, 11, 12, 1);
insert into sgs_proeza_test_db.sys_item_subitem(id, fk_item, fk_subitem, indice) values (9, 11, 13, 2);
insert into sgs_proeza_test_db.sys_item_subitem(id, fk_item, fk_subitem, indice) values (10, 11, 14, 3);

/* Menu - Item de Menu - Sólo aplican los items 'padre' (el nivel 0 de la jerarquia) */
insert into sgs_proeza_test_db.sys_menu_item(fk_menu, fk_item, indice) values (1, 1, 0);
insert into sgs_proeza_test_db.sys_menu_item(fk_menu, fk_item, indice) values (1, 2, 1);
insert into sgs_proeza_test_db.sys_menu_item(fk_menu, fk_item, indice) values (1, 4, 2);

insert into sgs_proeza_test_db.sys_menu_item(fk_menu, fk_item, indice) values (2, 15, 0);
insert into sgs_proeza_test_db.sys_menu_item(fk_menu, fk_item, indice) values (2, 11, 1);

/* Item de Menu - Rol */
	/* Usuario */
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (1, 2, 41);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (2, 2, 42);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (3, 2, 43);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (19, 9, 41);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (20, 9, 42);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (21, 9, 43);
	
	
	/* Articulo */
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (7, 4, 31);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (8, 4, 32);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (9, 4, 33);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (13, 6, 31);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (14, 6, 32);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (15, 6, 33);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (16, 7, 31);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (17, 7, 33);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (18, 8, 31);

	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (22, 11, 51);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (23, 12, 51);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (24, 13, 51);
	insert into sgs_proeza_test_db.sys_item_rol(id, fk_item, fk_rol) values (25, 14, 51);
/* Item de Menu - Rol */