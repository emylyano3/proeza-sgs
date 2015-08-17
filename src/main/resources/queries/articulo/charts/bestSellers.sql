select
	a.modelo as modelo,
	sum(va.cantidad) as cantidad
from sgs_proeza_db.art_venta_articulo va
join sgs_proeza_db.art_venta v on va.fk_venta = v.id
join sgs_proeza_db.art_articulo a on a.id = va.fk_articulo
group by modelo
order by cantidad desc