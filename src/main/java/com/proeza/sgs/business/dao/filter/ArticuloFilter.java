package com.proeza.sgs.business.dao.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Articulo_;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Clase_;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.Marca_;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.business.entity.Tipo_;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ArticuloFilter implements EntityFilter<Articulo> {

    private static final int MIN_FILTER_LENGTH = 3;

    private String           rawFilter;

    private String[]         rawFilterSplitted;

    private String[]         widedFilter;

    @Autowired
    private IArticuloDao     articuloDao;

    private CriteriaBuilder  builder;

    public ArticuloFilter () {
    }

    @Override
    public List<Articulo> doFilter () {
        if (initFilters()) {

            CriteriaQuery<Articulo> criteria = this.builder.createQuery(Articulo.class);
            Root<Articulo> root = criteria.from(Articulo.class);
            criteria.select(root);
            Join<Articulo, Clase> joinClase = root.join(Articulo_.clase);
            Join<Articulo, Marca> joinMarca = root.join(Articulo_.marca);
            Join<Articulo, Tipo> joinTipo = root.join(Articulo_.tipo, JoinType.LEFT);

            Expression<Boolean> habilitado = root.get(Articulo_.habilitado);

            Expression<String> nombreClase = joinClase.get(Clase_.nombre);
            Expression<String> nombreMarca = joinMarca.get(Marca_.nombre);
            Expression<String> nombreTipo = joinTipo.get(Tipo_.nombre);

            Expression<String> lowerClase = this.builder.lower(nombreClase);
            Expression<String> lowerMarca = this.builder.lower(nombreMarca);
            Expression<String> lowerTipo = this.builder.lower(nombreTipo);
            Expression<String> lowerModelo = this.builder.lower(root.get(Articulo_.modelo));
            Expression<String> lowerDescripcion = this.builder.lower(root.get(Articulo_.descripcion));

            List<Predicate> predicates = new ArrayList<>(5);

            for (String element : this.widedFilter) {
                predicates.add(this.builder.like(lowerClase, element));
                predicates.add(this.builder.like(lowerMarca, element));
                predicates.add(this.builder.like(lowerTipo, element));
                predicates.add(this.builder.like(lowerModelo, element));
                predicates.add(this.builder.like(lowerDescripcion, element));
            }
            Predicate or = this.builder.or(toArray(predicates));
            Predicate and = this.builder.and(or, this.builder.equal(habilitado, true));
            criteria.where(and);

            List<Articulo> bag = this.articuloDao.getEntityManager().createQuery(criteria).getResultList();
            return reapplyFilters(bag);
        } else {
            return new ArrayList<>(0);
        }
    }

    private List<Articulo> reapplyFilters (List<Articulo> bag) {
        List<Articulo> result = new ArrayList<>(13);
        Set<String> matched = new HashSet<>(this.rawFilterSplitted.length);
        for (Articulo articulo : bag) {
            for (String filter : this.rawFilterSplitted) {
                if (articulo.getMarca().getNombre().toLowerCase().contains(filter)) {
                    matched.add(filter);
                }
                if (articulo.getClase().getNombre().toLowerCase().contains(filter)) {
                    matched.add(filter);
                }
                if (articulo.getTipo() != null && articulo.getTipo().getNombre().toLowerCase().contains(filter)) {
                    matched.add(filter);
                }
                if (articulo.getModelo() != null && articulo.getModelo().toLowerCase().contains(filter)) {
                    matched.add(filter);
                }
                if (articulo.getDescripcion() != null && articulo.getDescripcion().toLowerCase().contains(filter)) {
                    matched.add(filter);
                }
            }
            if (matched.size() == this.rawFilterSplitted.length) {
                result.add(articulo);
            }
            matched.clear();
        }
        return result;
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
            String[] aux = this.rawFilter.trim().split(",");
            List<String> noList = new ArrayList<>(0);
            List<String> yesList = new ArrayList<>(0);
            for (String filter : aux) {
                if (filter.trim().length() >= MIN_FILTER_LENGTH) {
                    yesList.add(filter.trim().toLowerCase());
                } else {
                    noList.add(filter.trim().toLowerCase());
                }
            }
            if (!yesList.isEmpty()) {
                // Si hay al menos un filtro que cumple el requisito de tamaño, uso todos para filtrar
                yesList.addAll(noList);
                this.builder = this.articuloDao.getEntityManager().getCriteriaBuilder();
                this.rawFilterSplitted = new String[yesList.size()];
                this.widedFilter = new String[yesList.size()];
                for (int i = 0; i < yesList.size(); i++) {
                    String filter = yesList.get(i);
                    this.rawFilterSplitted[i] = filter;
                    this.widedFilter[i] = "%" + filter + "%";
                }
                return true;
            }
        }
        return false;
    }

    public void setFilterString (String filter) {
        this.rawFilter = filter;
    }
}