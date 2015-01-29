package com.proeza.sgs.business.dao.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

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
import com.proeza.sgs.business.entity.Rubro_;
import com.proeza.sgs.business.entity.Tipo_;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ArticuloFilter implements EntityFilter<Articulo> {

	private String			rawFilter;

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
		initFilters();
		this.builder = this.articuloDao.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Articulo> criteria = this.builder.createQuery(Articulo.class);
		Root<Articulo> root = criteria.from(Articulo.class);
		criteria.select(root);
		List<Predicate> predicates = new ArrayList<>(0);
		addJoinPredicate(root, predicates, Articulo_.clase, Clase_.nombre);
		addJoinPredicate(root, predicates, Articulo_.rubro, Rubro_.nombre);
		addJoinPredicate(root, predicates, Articulo_.marca, Marca_.nombre);
		addJoinPredicate(root, predicates, Articulo_.tipo, Tipo_.nombre);
		addWherePredicates(root, predicates);

		Predicate orPredicate = this.builder.or(predicates.toArray(new Predicate[0]));
		criteria.where(orPredicate);
		return this.articuloDao.getEntityManager().createQuery(criteria).getResultList();
	}

	private <T> void addWherePredicates (Root<Articulo> root, List<Predicate> predicates) {
		for (String element : this.widedFilter) {
			predicates.add(this.builder.like(this.builder.lower(root.get(Articulo_.modelo)), element));
			predicates.add(this.builder.like(this.builder.lower(root.get(Articulo_.descripcion)), element));
		}
	}

	private <T> void addJoinPredicate (Root<Articulo> root, List<Predicate> predicates, SingularAttribute<Articulo, T> att, SingularAttribute<T, String> att2) {
		Join<Articulo, T> join = root.join(att, JoinType.LEFT);
		Expression<String> nombre = join.get(att2);
		Expression<String> lower = this.builder.lower(nombre);
		for (String element : this.widedFilter) {
			predicates.add(this.builder.like(lower, element));
		}
	}

	private void initFilters () {
		if (this.rawFilter != null && !this.rawFilter.isEmpty()) {
			this.widedFilter = this.rawFilter.trim().split(",");
			for (int i = 0; i < this.widedFilter.length; i++) {
				this.widedFilter[i] = "%" + this.widedFilter[i].trim().toLowerCase() + "%";
			}
		}
	}

	public void setFilterString (String filter) {
		this.rawFilter = filter;
	}
}