package com.proeza.sgs.business.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.business.dto.ClaseDTO;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.service.IClaseService;

@Service
@Transactional
public class ClaseService implements IClaseService {

	@Autowired
	private ClaseDao	dao;

	@Override
	public Collection<ClaseDTO> findAll () {
		return hideEntites(this.dao.findAll());
	}

	private List<ClaseDTO> hideEntites (List<Clase> clases) {
		List<ClaseDTO> result = new ArrayList<>(clases.size());
		for (Clase clase : clases) {
			result.add(new ClaseDTO(clase));
		}
		return result;
	}
}