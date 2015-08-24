package com.proeza.sgs.web.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proeza.sgs.business.entity.dto.ArticuloDTO;
import com.proeza.sgs.business.entity.dto.ResourceDTO;
import com.proeza.sgs.business.service.IArticuloService;

@RestController
@RequestMapping("rest/articulo")
public class ArticulosRestController {

    private static final Logger log = Logger.getLogger(ArticulosRestController.class);

    @Autowired
    private IArticuloService    productService;

    @RequestMapping(value = "findAll")
    public List<ArticuloDTO> findAll () {
        return this.productService.findAll();
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<ArticuloDTO> search (@RequestParam("filter") String filter) {
        return this.productService.findByStringFilter(filter);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@RequestBody ArticuloDTO articulo) {
        this.productService.update(articulo);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create (@RequestBody ArticuloDTO articulo) {
        this.productService.create(articulo);
    }

    @RequestMapping(value = "addImage", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void uploadImage (ModelMap model, Principal principal, @RequestParam("artCode") String code, @RequestParam("file") MultipartFile file) {
        try {
            this.productService.addImage(code, "Imagen", "Imagen del artículo", MediaType.IMAGE_JPEG, file.getBytes());
        } catch (Exception e) {
            log.error("Error agregando la imagen al articulo causado por: " + e.getMessage(), e);
        }
    }

    @RequestMapping(value = "getImage/{article}/{imageId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getImage (@PathVariable String article, @PathVariable Long imageId) {
        try {
            return this.productService.getImage(article, imageId);
        } catch (Exception e) {
            log.error("Error agregando la imagen al articulo causado por: " + e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "getThumbnail/{article}/{imageId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getThumbnail (@PathVariable String article, @PathVariable Long imageId) {
        try {
            return this.productService.getThumbnail(article, imageId);
        } catch (Exception e) {
            log.error("Error agregando la imagen al articulo causado por: " + e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "getImages/{article}", method = RequestMethod.POST)
    public List<ResourceDTO> getImages (@PathVariable String article) {
        try {
            return this.productService.getImagesAvail(article);
        } catch (Exception e) {
            log.error("Error agregando la imagen al articulo causado por: " + e.getMessage(), e);
            return new ArrayList<ResourceDTO>(0);
        }
    }
}