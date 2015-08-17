select
	m.nombre as marca,
	sum(a.cantidad) as stock
FROM art_articulo a
join art_marca m on m.id = a.fk_marca
group by marca
order by stock desc