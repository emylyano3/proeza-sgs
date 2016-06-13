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

DELETE FROM sys_item_rol WHERE fk_item in (3, 10);
DELETE FROM sys_item_subitem WHERE fk_item in (3, 10);
DELETE FROM sys_item_subitem WHERE fk_subitem in (3, 10);
DELETE FROM sys_item WHERE id in (3, 10);

ALTER TABLE `sgs_proeza_dev_db`.`sys_pagina` 
ADD COLUMN `fk_i18n_descripcion` BIGINT(20) UNSIGNED ZEROFILL NULL DEFAULT NULL AFTER `fk_i18n_subtitulo`;
ALTER TABLE `sgs_proeza_dev_db`.`sys_pagina` 
CHANGE COLUMN `fk_i18n_descripcion` `fk_i18n_descripcion` BIGINT(20) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT 'Descripcion de la pagina' ;
ALTER TABLE `sgs_proeza_dev_db`.`sys_pagina` 
DROP COLUMN `descripcion`;


update sys_pagina set fk_i18n_descripcion = id + 28 where id > 0;
insert into cmn_i18n (id) values (29),(30),(31),(32),(33),(34),(35),(36),(37),(38),(39),(40),(41),(42);

insert into cmn_traduccion (fk_i18n, locale, texto) values
(29, 'es_AR', 'Inicio del módulo de usuario'),
(29, 'en_US', 'Home for user module'),
(30, 'es_AR', 'Página de listado de usuarios'),
(30, 'en_US', 'User listing page'),
(31, 'es_AR', 'Página de administración de usuarios'),
(31, 'en_US', 'User administration page'),
(32, 'es_AR', 'Página de estadísticas de usuario'),
(32, 'en_US', 'User statistics page'),
(33, 'es_AR', 'Inicio de la aplicación'),
(33, 'en_US', 'Application home'),
(34, 'es_AR', 'Página de registro de usuario'),
(34, 'en_US', 'User registration page'),
(35, 'es_AR', 'Página de login de usuario'),
(35, 'en_US', 'User login page'),
(36, 'es_AR', 'Página de logout de usuario'),
(36, 'en_US', 'User logout page'),
(37, 'es_AR', 'Inicio del módulo de artículo'),
(37, 'en_US', 'Home for product module'),
(38, 'es_AR', 'Página de listado de artículos'),
(38, 'en_US', 'Product listing page'),
(39, 'es_AR', 'Página de estadísticas de artículos'),
(39, 'en_US', 'Product statistics page'),
(40, 'es_AR', 'Página de administración de artículos'),
(40, 'en_US', 'Product administration page'),
(41, 'es_AR', 'Página de perfil de usuario'),
(41, 'en_US', 'User profile page'),
(42, 'es_AR', 'Página de administración de accessos al sistema'),
(42, 'en_US', 'System access administration page');
