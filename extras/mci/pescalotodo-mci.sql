/* Migracion de marcas */
	
	/* Deteccion de repetidos */
	select * from pescalotodo.marcas where descripcion in (
		select descripcion from 
		pescalotodo.marcas
		group by descripcion
		having count(*) > 1
	);
	
	/* Migracion de datos */
	insert into sgs_proeza_db.art_marca (id, codigo, nombre, descripcion) 
	select
		idMarca,
		upper(replace(descripcion, ' ', '')),
		descripcion,
		descripcion
	from 
	pescalotodo.marcas;
	
/* Migracion de marcas */
	
/* Migracion de rubros */

	insert into sgs_proeza_db.art_rubro (id, codigo, nombre, descripcion) 
	select
		idCat,
		upper(replace(descripcion, ' ', '')),
		descripcion,
		descripcion
	from 
	pescalotodo.categorias;
	
/* Migracion de rubros */

/* Migracion de clases */

	insert into sgs_proeza_db.art_clase (id, codigo, nombre, descripcion) 
	select
		idTipo,
		upper(concat(substr(replace(descripcion, ' ', ''), 1, 10), idTipo)) as codigo, 
		descripcion,
		descripcion
	from 
	pescalotodo.tipoarticulo;
	
/* Migracion de clases */

/* Migracion de articulos */

	insert into sgs_proeza_db.art_articulo (id, codigo, modelo, descripcion, fk_rubro, fk_clase, fk_marca, precio) 
	select
		idArt,
		upper(
			concat(
				concat(
					substr(replace(c.descripcion, ' ', ''), 1, 2), -- Dos digitos de la clase
					concat(
						substr(replace(ta.descripcion, ' ', ''), 1, 2), -- Dos digitos del tipo de articulo
						substr(replace(m.descripcion, ' ', ''), 1, 2) -- Dos digitos de la marca
					)
				),
	            a.idArt -- El id del articulo
			)
		) as codigo, 
		substr(a.descripcion, 1, 50),
		a.descripcion,
	    a.idCategoria,
	    a.idTipo,
	    a.idMarca,
	    a.precio
	from
	pescalotodo.articulos a
	join tipoArticulo ta on ta.idTipo = a.idTipo
	join marcas m on m.idMarca = a.idMarca
	join categorias c on c.idCategoria = a.idCategoria;
	
/* Migracion de articulos */