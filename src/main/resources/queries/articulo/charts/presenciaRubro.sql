 select
	r.nombre as rubro,
	sum(a.cantidad) as stock
FROM art_articulo a
join art_rubro r on r.id = a.fk_rubro
group by rubro
order by stock desc