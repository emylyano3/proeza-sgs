package com.proeza.sgs.business.dao.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ArticuloFilterFactory {

    @Autowired
    private ApplicationContext context;

    public ArticuloFilter create () {
        return this.context.getBean(ArticuloFilter.class);
    }

    public ArticuloFilter create (String filter) {
        ArticuloFilter articuloFilter = this.context.getBean(ArticuloFilter.class);
        articuloFilter.setFilterString(filter);
        return articuloFilter;
    }
}