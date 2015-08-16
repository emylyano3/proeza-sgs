package com.proeza.sgs.business.service;

import java.util.List;

import org.springframework.http.MediaType;

import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.dto.ResourceDTO;

public interface IArticuloService {

    List<ArticuloDTO> findAll ();

    List<ArticuloDTO> findByStringFilter (String filter);

    void update (ArticuloDTO articulo);

    /**
     * Agrega una imagen a un articulo determinado
     */
    void addImage (String articleCode, String imageName, String imageDesc, MediaType type, byte[] image);

    byte[] getImage (String articleCode, Long id);

    byte[] getThumbnail (String articleCode, Long id);

    List<ResourceDTO> getImagesAvail (String articleCode);
}