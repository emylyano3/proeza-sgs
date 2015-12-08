package com.proeza.sgs.business.service;

import java.util.List;

import org.springframework.http.MediaType;

import com.proeza.sgs.business.entity.dto.ArticuloDTO;
import com.proeza.sgs.business.entity.dto.ClaseDTO;
import com.proeza.sgs.business.entity.dto.ResourceDTO;
import com.proeza.sgs.business.entity.dto.TipoDTO;

public interface IArticuloService {

    List<ArticuloDTO> findAll();

    List<ArticuloDTO> findByStringFilter(String filter);

    ArticuloDTO findByCode(String code);

    void update(ArticuloDTO articulo);

    void create(ArticuloDTO articulo);

    void remove(String productCode);

    /**
     * Agrega una imagen a un articulo determinado
     */
    void addImage(String productCode, String imageName, String imageDesc, MediaType type, byte[] image);

    byte[] getImage(String productCode, Long id);

    byte[] getThumbnail(String productCode, Long id);

    /**
     * Devuelve la descripcion de cada una de las imagenes disponibles para el
     * articulo. NO devuelve las imagenes propiamente dichas.
     */
    List<ResourceDTO> getImagesAvail(String productCode);

    List<ClaseDTO> classesByCategory(String categoryCode);

    List<TipoDTO> typesByClass(String classCode);
}