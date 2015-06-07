package com.proeza.sgs.business.dao.filter;

import java.util.List;

public interface EntityFilter<E> {
    List<E> doFilter ();
}