/* Maestros */
	/* Rubro */
	insert into sgs_proeza_test_db.art_rubro (codigo, nombre, descripcion) values ('PESCA','Pesca','Pesca');
	insert into sgs_proeza_test_db.art_rubro (codigo, nombre, descripcion) values ('CAMPING','Camping','Camping');
	
	/* Clase */
	insert into sgs_proeza_test_db.art_clase (codigo, nombre, descripcion, fk_rubro) values ('REEL','Reel','Clase que abarca a todos los reeles', 1);
	insert into sgs_proeza_test_db.art_clase (codigo, nombre, descripcion, fk_rubro) values ('CANIA','Caña','Clase que abarca a todas las cañas', 1);
	insert into sgs_proeza_test_db.art_clase (codigo, nombre, descripcion, fk_rubro) values ('CARPA','Carpa','Clase que abarca a todas las carpas', 2);
	
	/* Tipo */
	insert into sgs_proeza_test_db.art_tipo (codigo, nombre, descripcion, fk_clase) values ('ROTATIVO','Rotativo','Rotativo', 1);
	insert into sgs_proeza_test_db.art_tipo (codigo, nombre, descripcion, fk_clase) values ('EMBARCADO','Embarcado','Embarcado', 2);
	insert into sgs_proeza_test_db.art_tipo (codigo, nombre, descripcion, fk_clase) values ('IGLOO','Igloo','Igloo', 3);
	insert into sgs_proeza_test_db.art_tipo (codigo, nombre, descripcion, fk_clase) values ('CANADIENSE','Canadiense','Igloo', 3);
	insert into sgs_proeza_test_db.art_tipo (codigo, nombre, descripcion, fk_clase) values ('TELESCOPICA','Telescópica','Telescópica', 2);
	
	/* Marca */
	insert into sgs_proeza_test_db.art_marca (codigo, nombre, descripcion) values ('SURFISH','Surfish','Surfish');
	insert into sgs_proeza_test_db.art_marca (codigo, nombre, descripcion) values ('WATERDOG','Waterdog','Waterdog');
	insert into sgs_proeza_test_db.art_marca (codigo, nombre, descripcion) values ('NAHUEL','Nahuel','Nahuel');

/* Data de negocio */
	/* Articulos */
	insert into sgs_proeza_test_db.art_articulo (codigo, fk_rubro, fk_clase, fk_tipo, fk_marca, modelo, descripcion, costo, precio, cantidad, habilitado) values ('PRRS000001', 1, 1, 1, 1, 'Ocean 1005', '3 rulemanes; antireverse instantaneo; devanador', 200, 250, 2, 1);
	insert into sgs_proeza_test_db.art_articulo (codigo, fk_rubro, fk_clase, fk_tipo, fk_marca, modelo, descripcion, costo, precio, cantidad, habilitado) values ('PCES000002', 1, 2, 2, 1, 'Energhia 195', 'Pasahilo silicio; Puntero con roldana', 100, 150, 3, 1);
	insert into sgs_proeza_test_db.art_articulo (codigo, fk_rubro, fk_clase, fk_tipo, fk_marca, modelo, descripcion, costo, precio, cantidad, habilitado) values ('CCCN000001', 2, 3, 4, 3, '7/8 Personas', 'Sobre techo; Mosquitero Doble', 2500, 4000, 1, 1);
	insert into sgs_proeza_test_db.art_articulo (codigo, fk_rubro, fk_clase, fk_tipo, fk_marca, modelo, descripcion, costo, precio, cantidad, habilitado) values ('CCIW000001', 2, 3, 3, 2, 'Aconcagua 4 Personas', '2000mm; Aluminizado; Con Alero', 1300, 2200, 4, 1);
	insert into sgs_proeza_test_db.art_articulo (codigo, fk_rubro, fk_clase, fk_tipo, fk_marca, modelo, descripcion, costo, precio, cantidad, habilitado) values ('PCES000003', 1, 2, 2, 2, 'Agressor 210', 'Pasahilo silicio; Puntero con roldana', 180, 260, 1, 1);
	insert into sgs_proeza_test_db.art_articulo (codigo, fk_rubro, fk_clase, fk_tipo, fk_marca, modelo, descripcion, costo, precio, cantidad, habilitado) values ('PCES000004', 1, 2, 5, 2, 'Matrix 420', 'Pasahilo silicio', 1500, 2400, 2, 1);
	
	/* Persona */
	insert into sgs_proeza_test_db.cmn_persona (id, nombre, apellido, sexo) values (1, 'Dario','Romero','M');
	insert into sgs_proeza_test_db.cmn_persona (id, nombre, apellido, sexo) values (2, 'Jose Luis','Torres','M');
	insert into sgs_proeza_test_db.cmn_persona (id, nombre, apellido, sexo) values (3, 'Ricardo','Bruzzone','M');
	
	/* Proveedor */
	insert into sgs_proeza_test_db.art_proveedor (fk_persona) values (1);
	insert into sgs_proeza_test_db.art_proveedor (fk_persona) values (2);
	
	/* Cliente */
	insert into sgs_proeza_test_db.art_cliente (fk_persona) values (3);
	
	/* Email */
	insert into sgs_proeza_test_db.cmn_email (direccion, fk_persona) values ('lalocat_25@hotmail.com', 1); 

	/* Telefono */
	insert into sgs_proeza_test_db.cmn_telefono (pref_internacional, pref_area, numero, fk_tipo, fk_persona) values ('+54', '011', '2323000', 1, 1); 
	
	/* Articulo - Proveedor */
	insert into sgs_proeza_test_db.art_articulo_proveedor(id, fk_articulo, fk_proveedor) values (1, 1, 1);
	insert into sgs_proeza_test_db.art_articulo_proveedor(id, fk_articulo, fk_proveedor) values (2, 2, 1);
	insert into sgs_proeza_test_db.art_articulo_proveedor(id, fk_articulo, fk_proveedor) values (3, 3, 2);
	insert into sgs_proeza_test_db.art_articulo_proveedor(id, fk_articulo, fk_proveedor) values (4, 4, 2);
	insert into sgs_proeza_test_db.art_articulo_proveedor(id, fk_articulo, fk_proveedor) values (5, 5, 2);
	insert into sgs_proeza_test_db.art_articulo_proveedor(id, fk_articulo, fk_proveedor) values (6, 6, 2);
	
	/* Articulo - Cliente */
	insert into sgs_proeza_test_db.art_articulo_cliente(id, fk_articulo, fk_cliente) values (1, 1, 3);
	
	/* Venta */
	insert into sgs_proeza_test_db.art_venta (codigo, fecha, importe, fk_medio_pago, fk_cliente, fk_usuario) values ('QEFT20150215', '2015-02-15', 1500, 1, 3, 104);
	insert into sgs_proeza_test_db.art_venta (codigo, fecha, importe, fk_medio_pago, fk_cliente, fk_usuario) values ('QEFT20150217', '2015-02-17', 2500, 1, null, 104);
	insert into sgs_proeza_test_db.art_venta (codigo, fecha, importe, fk_medio_pago, fk_cliente, fk_usuario) values ('QEFT20150221', '2015-02-21', 400, 3, null, 104);
	insert into sgs_proeza_test_db.art_venta (codigo, fecha, importe, fk_medio_pago, fk_cliente, fk_usuario) values ('QEFT20150301', '2015-03-01', 735, 1, null, 104);
	insert into sgs_proeza_test_db.art_venta (codigo, fecha, importe, fk_medio_pago, fk_cliente, fk_usuario) values ('QEFT20150303', '2015-03-03', 2730, 2, null, 104);
	insert into sgs_proeza_test_db.art_venta (codigo, fecha, importe, fk_medio_pago, fk_cliente, fk_usuario) values ('NEFT20150217', '2015-02-17', 2500, 1, null, 104);
	insert into sgs_proeza_test_db.art_venta (codigo, fecha, importe, fk_medio_pago, fk_cliente, fk_usuario) values ('NEFT20150221', '2015-02-21', 400, 3, null, 104);
	insert into sgs_proeza_test_db.art_venta (codigo, fecha, importe, fk_medio_pago, fk_cliente, fk_usuario) values ('NEFT20150301', '2015-03-01', 735, 1, null, 104);
	insert into sgs_proeza_test_db.art_venta (codigo, fecha, importe, fk_medio_pago, fk_cliente, fk_usuario) values ('NEFT20150303', '2015-03-03', 2730, 2, null, 104);
	insert into sgs_proeza_test_db.art_venta (codigo, fecha, importe, fk_medio_pago, fk_cliente, fk_usuario) values ('NEFT20150305', '2015-03-05', 2730, 2, null, 104);
		
	/* Asociacion de venta con articulo */
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (1, 1, 1);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (2, 2, 1);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (2, 5, 2);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (2, 6, 3);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (3, 3, 9);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (3, 4, 1);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (4, 5, 4);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (4, 2, 1);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (5, 5, 2);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (5, 3, 1);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (6, 3, 1);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (7, 4, 1);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (8, 6, 1);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (8, 6, 2);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (8, 1, 1);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (9, 3, 1);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (9, 1, 2);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (10, 4, 1);
	insert into sgs_proeza_test_db.art_venta_articulo (fk_venta, fk_articulo, cantidad) values (10, 2, 1);
	
	/* Movimiento sobre articulos */ 	
	insert into sgs_proeza_test_db.cmn_movimiento (id, tipo_mov, fk_entidad, tipo_entidad, fecha, valor_ant, valor_post) values (100, 'MOD_PRECIO', 1, 'ARTICULO', '2015-01-12 11:34:31.923', '250', '270');
	insert into sgs_proeza_test_db.cmn_movimiento (id, tipo_mov, fk_entidad, tipo_entidad, fecha, valor_ant, valor_post) values (101, 'MOD_PRECIO', 1, 'ARTICULO', '2015-02-10 11:34:31.923', '270', '275');
	insert into sgs_proeza_test_db.cmn_movimiento (id, tipo_mov, fk_entidad, tipo_entidad, fecha, valor_ant, valor_post) values (102, 'MOD_PRECIO', 1, 'ARTICULO', '2015-03-19 11:34:31.923', '275', '290');
	insert into sgs_proeza_test_db.cmn_movimiento (id, tipo_mov, fk_entidad, tipo_entidad, fecha, valor_ant, valor_post) values (103, 'MOD_PRECIO', 1, 'ARTICULO', '2015-04-13 11:34:31.923', '290', '305');
	insert into sgs_proeza_test_db.cmn_movimiento (id, tipo_mov, fk_entidad, tipo_entidad, fecha, valor_ant, valor_post) values (104, 'MOD_PRECIO', 1, 'ARTICULO', '2015-05-12 11:34:31.923', '305', '325');
	
	update sgs_proeza_test_db.art_articulo set precio = 325 where id = 1;