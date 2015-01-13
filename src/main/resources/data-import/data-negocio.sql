/* Rubro */
insert into sgs_proeza_db.rubro (codigo, nombre, descripcion) values ('PESCA','Pesca','Pesca');

/* Clase */
insert into sgs_proeza_db.clase (codigo, nombre, descripcion, fk_rubro) values ('REEL','Reel','Clase que abarca a todos los reeles', 1);
insert into sgs_proeza_db.clase (codigo, nombre, descripcion, fk_rubro) values ('CANIA','Caña','Clase que abarca a todas las cañas', 1);

/* Tipo */
insert into sgs_proeza_db.tipo (codigo, nombre, descripcion) values ('EMBARCADO','Embarcado','Embarcado');
insert into sgs_proeza_db.tipo (codigo, nombre, descripcion) values ('ROTATIVO','Rotativo','Rotativo');

/* Clase - Tipo */
insert into sgs_proeza_db.clase_tipo (fk_clase, fk_tipo) values (1, 1);
insert into sgs_proeza_db.clase_tipo (fk_clase, fk_tipo) values (2, 1);

/* Marca */
insert into sgs_proeza_db.marca (codigo, nombre, descripcion) values ('SURFISH','Surfish','Surfish');

/* Articulos */
insert into sgs_proeza_db.articulo (codigo, fk_rubro, fk_clase, fk_tipo, fk_marca, modelo, descripcion, costo, precio, cantidad) values ('PRRS000001', 1, 1, 2, 1, 'Ocean 1005', '3 rulemanes; antireverse instantaneo; devanador', 200, 250, 2);

insert into sgs_proeza_db.articulo (codigo, fk_rubro, fk_clase, fk_tipo, fk_marca, modelo, descripcion, costo, precio, cantidad) values ('PCES000002', 1, 2, 1, 1, 'Energhia 195', 'Pasahilo silicio; Puntero con roldana', 100, 150, 3);
