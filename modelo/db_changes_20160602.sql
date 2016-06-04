INSERT INTO `cmn_i18n` (`id`) VALUES (00000000000000000027);
INSERT INTO `cmn_i18n` (`id`) VALUES (00000000000000000028);
INSERT INTO `cmn_traduccion` (`id`,`fk_i18n`,`locale`,`texto`) VALUES (00000000000000000054,00000000000000000027,'en_US','Application');
INSERT INTO `cmn_traduccion` (`id`,`fk_i18n`,`locale`,`texto`) VALUES (00000000000000000053,00000000000000000027,'es_AR','Aplicación');
INSERT INTO `cmn_traduccion` (`id`,`fk_i18n`,`locale`,`texto`) VALUES (00000000000000000056,00000000000000000028,'en_US','Menúes');
INSERT INTO `cmn_traduccion` (`id`,`fk_i18n`,`locale`,`texto`) VALUES (00000000000000000055,00000000000000000028,'es_AR','Menus');
INSERT INTO `seg_rol` (`id`,`codigo`,`nombre`,`descripcion`) VALUES (00000000000000000044,'ROLE_APP_ADMIN','Aplicación Administrador','Administrador de la configuración del sistema');
INSERT INTO `sys_item` (`id`,`codigo`,`fk_i18n_texto`,`fk_i18n_tooltip`,`icono`,`link`) VALUES (00000000000000000016,'MI_APP',27,NULL,NULL,'#');
INSERT INTO `sys_item` (`id`,`codigo`,`fk_i18n_texto`,`fk_i18n_tooltip`,`icono`,`link`) VALUES (00000000000000000017,'MI_APP_ACCESS',28,NULL,'fa fa-barcode','/application/access');
INSERT INTO `sys_item_rol` (`id`,`fk_item`,`fk_rol`) VALUES (00000000000000000029,00000000000000000016,00000000000000000044);
INSERT INTO `sys_item_rol` (`id`,`fk_item`,`fk_rol`) VALUES (00000000000000000030,00000000000000000017,00000000000000000044);
INSERT INTO `sys_item_subitem` (`id`,`fk_item`,`fk_subitem`,`indice`) VALUES (00000000000000000011,00000000000000000016,00000000000000000017,0);
INSERT INTO `sys_menu_item` (`id`,`fk_menu`,`fk_item`,`indice`) VALUES (00000000000000000006,00000000000000000001,00000000000000000016,3);
INSERT INTO `seg_usuario_rol` (`id`,`fk_usuario`,`fk_rol`) VALUES (00000000000000000022,00000000000000000104,00000000000000000044);

/*Creacion de la pagina*/
INSERT INTO sys_pagina (grupo, nombre, descripcion, fk_i18n_titulo, fk_i18n_subtitulo) VALUES ('application/admin', 'menu', 'Página de edición de menúes de acceso al sistema', 27, 28); 
INSERT INTO `sys_pagina_rol` (`id`,`fk_pagina`,`fk_rol`) VALUES (00000000000000000038,00000000000000000014,00000000000000000044);
INSERT INTO `sys_pagina_menu` (`id`,`fk_pagina`,`fk_menu`) VALUES (00000000000000000013,00000000000000000014,00000000000000000001);