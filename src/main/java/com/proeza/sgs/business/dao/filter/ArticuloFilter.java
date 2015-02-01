package com.proeza.sgs.business.dao.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.proeza.sgs.business.dao.ArticuloDao;
import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Articulo_;
import com.proeza.sgs.business.entity.Clase_;
import com.proeza.sgs.business.entity.Marca_;
import com.proeza.sgs.business.entity.Tipo_;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ArticuloFilter implements EntityFilter<Articulo> {

	private String			rawFilter;

	private String[]		rawFilterSplitted;

	private String[]		widedFilter;

	@Autowired
	private ArticuloDao		articuloDao;

	@Autowired
	private ClaseDao		claseDao;

	private CriteriaBuilder	builder;

	public ArticuloFilter () {
	}

	@Override
	public List<Articulo> doFilter () {
		if (initFilters()) {
			List<Articulo> byClase = filterByDimension(Articulo_.clase, Clase_.nombre);
			List<Articulo> byMarca = filterByDimension(Articulo_.marca, Marca_.nombre);
			List<Articulo> byTipo = filterByDimension(Articulo_.tipo, Tipo_.nombre);
			List<Articulo> intersection;
			intersection = intersect(byClase, byMarca);
			intersection = intersect(intersection, byTipo);
			List<Articulo> result = new ArrayList<>();
			for (Articulo articulo : intersection) {
				boolean contains = false;
				for (String filter : this.rawFilterSplitted) {
					contains = contains | articulo.getModelo().toLowerCase().contains(filter);
				}
				if (contains) {
					result.add(articulo);
				}
			}
//			if (!result.isEmpty()) {
//				return result;
//			}
			return result;
		} else {
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	List<Articulo> intersect (List<Articulo> a, List<Articulo> b) {
		if (!a.isEmpty() && !b.isEmpty()) {
			return new ArrayList<>(CollectionUtils.intersection(a, b));
		}
		if (a.isEmpty()) {
			return b;
		}
		return a;
	}

	private <T> List<Articulo> filterByDimension (SingularAttribute<Articulo, T> rootAtt, SingularAttribute<T, String> referencedAtt) {
		CriteriaQuery<Articulo> criteria = this.builder.createQuery(Articulo.class);
		Root<Articulo> root = criteria.from(Articulo.class);
		criteria.select(root);
		Join<Articulo, T> join = root.join(rootAtt);
		Expression<String> nombre = join.get(referencedAtt);
		Expression<String> lower = this.builder.lower(nombre);
		List<Predicate> predicates = new ArrayList<>();
		for (String element : this.widedFilter) {
			predicates.add(this.builder.like(lower, element));
		}
		Predicate or = this.builder.or(toArray(predicates));
		criteria.where(or);
		return this.articuloDao.getEntityManager().createQuery(criteria).getResultList();
	}

	private Predicate[] toArray (List<Predicate> list) {
		Predicate[] predicateArray = new Predicate[list.size()];
		for (int i = 0; i < list.size(); i++) {
			predicateArray[i] = list.get(i);
		}
		return predicateArray;
	}

	private boolean initFilters () {
		if (this.rawFilter != null && !this.rawFilter.isEmpty()) {
			this.builder = this.articuloDao.getEntityManager().getCriteriaBuilder();
			this.rawFilterSplitted = this.rawFilter.trim().split(",");
			this.widedFilter = new String[this.rawFilterSplitted.length];
			for (int i = 0; i < this.widedFilter.length; i++) {
				this.rawFilterSplitted[i] = this.rawFilterSplitted[i].trim().toLowerCase();
				this.widedFilter[i] = "%" + this.rawFilterSplitted[i] + "%";
			}
			return true;
		}
		return false;
	}

	public void setFilterString (String filter) {
		this.rawFilter = filter;
	}
}