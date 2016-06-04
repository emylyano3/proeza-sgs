package com.proeza.security.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.security.dao.IRolDao;
import com.proeza.security.dto.RolDTO;
import com.proeza.security.entity.Rol;
import com.proeza.security.service.IRoleService;

@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private IRolDao rolDao;

    @Override
    public List<RolDTO> findAll () {
        List<Rol> roles = this.rolDao.findAll();
        List<RolDTO> result = new ArrayList<RolDTO>(roles.size());
        for (Rol rol : roles) {
            result.add(new RolDTO(rol));
        }
        Collections.sort(result);
        return result;
    }
}