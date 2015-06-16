/* Maestros */
	/* Rubro */
	insert into sgs_proeza_db.art_rubro (codigo, nombre, descripcion) values ('PESCA','Pesca','Pesca');
	
	/* Clase */
	insert into sgs_proeza_db.art_clase (codigo, nombre, descripcion, fk_rubro) values ('REEL','Reel','Clase que abarca a todos los reeles', 1);
	insert into sgs_proeza_db.art_clase (codigo, nombre, descripcion, fk_rubro) values ('CANIA','Caña','Clase que abarca a todas las cañas', 1);
	
	/* Tipo */
	insert into sgs_proeza_db.art_tipo (codigo, nombre, descripcion, fk_clase) values ('ROTATIVO','Rotativo','Rotativo', 1);
	insert into sgs_proeza_db.art_tipo (codigo, nombre, descripcion, fk_clase) values ('EMBARCADO','Embarcado','Embarcado', 2);
	
	/* Marca */
	insert into sgs_proeza_db.art_marca (codigo, nombre, descripcion) values ('SURFISH','Surfish','Surfish');

/* Data de negocio */
	/* Articulos */
	insert into sgs_proeza_db.art_articulo (codigo, fk_rubro, fk_clase, fk_tipo, fk_marca, modelo, descripcion, costo, precio, cantidad) values ('PRRS000001', 1, 1, 1, 1, 'Ocean 1005', '3 rulemanes; antireverse instantaneo; devanador', 200, 250, 2);
	insert into sgs_proeza_db.art_articulo (codigo, fk_rubro, fk_clase, fk_tipo, fk_marca, modelo, descripcion, costo, precio, cantidad) values ('PCES000002', 1, 2, 2, 1, 'Energhia 195', 'Pasahilo silicio; Puntero con roldana', 100, 150, 3);
	
	/* Persona */
	insert into sgs_proeza_db.cmn_persona (id, nombre, apellido, sexo) values (1, 'Dario','Romero','M');
	insert into sgs_proeza_db.cmn_persona (id, nombre, apellido, sexo) values (2, 'Paula','Silveyra','F');
	
	/* Proveedor */
	insert into sgs_proeza_db.art_proveedor (fk_persona) values (1);
	
	/* Cliente */
	insert into sgs_proeza_db.art_cliente (fk_persona) values (2);
	
	/* Email */
	insert into sgs_proeza_db.cmn_email (direccion, fk_persona) values ('lalocat_25@hotmail.com', 1); 

	/* Telefono */
	insert into sgs_proeza_db.cmn_telefono (pref_internacional, pref_area, numero, fk_tipo, fk_persona) values ('+54', '011', '2323000', 1, 1); 
	
	/* Articulo - Proveedor */
	insert into sgs_proeza_db.art_articulo_proveedor(id, fk_articulo, fk_proveedor) values (1, 1, 1);
	insert into sgs_proeza_db.art_articulo_proveedor(id, fk_articulo, fk_proveedor) values (2, 2, 1);
	
	/* Articulo - Cliente */
	insert into sgs_proeza_db.art_articulo_cliente(id, fk_articulo, fk_cliente) values (1, 1, 2);
	
	/* Venta */
	insert into sgs_proeza_db.art_venta (codigo, fecha, importe, fk_medio_pago, fk_cliente) values ('EFT20150215', '02/15/2015', 1500, 1, 2);
	
	/* Asociacion de venta con articulo */
	insert into sgs_proeza_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (1, 1, 1);
	
	/* Movimiento sobre articulos */ 	
	insert into sgs_proeza_db.cmn_movimiento (id, tipo_mov, fk_entidad, tipo_entidad, f_movimiento, valor_ant, valor_post) values (100, 'MOD_PRECIO', 1, 'ARTICULO', '2015-01-12 11:34:31.923', '250', '270');
	insert into sgs_proeza_db.cmn_movimiento (id, tipo_mov, fk_entidad, tipo_entidad, f_movimiento, valor_ant, valor_post) values (101, 'MOD_PRECIO', 1, 'ARTICULO', '2015-02-10 11:34:31.923', '270', '275');
	insert into sgs_proeza_db.cmn_movimiento (id, tipo_mov, fk_entidad, tipo_entidad, f_movimiento, valor_ant, valor_post) values (102, 'MOD_PRECIO', 1, 'ARTICULO', '2015-03-19 11:34:31.923', '275', '290');
	insert into sgs_proeza_db.cmn_movimiento (id, tipo_mov, fk_entidad, tipo_entidad, f_movimiento, valor_ant, valor_post) values (103, 'MOD_PRECIO', 1, 'ARTICULO', '2015-04-13 11:34:31.923', '290', '305');
	insert into sgs_proeza_db.cmn_movimiento (id, tipo_mov, fk_entidad, tipo_entidad, f_movimiento, valor_ant, valor_post) values (104, 'MOD_PRECIO', 1, 'ARTICULO', '2015-05-12 11:34:31.923', '305', '325');
	
	update sgs_proeza_db.art_articulo set precio = 325 where id = 1;