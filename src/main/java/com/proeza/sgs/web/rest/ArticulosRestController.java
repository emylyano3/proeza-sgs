package com.proeza.sgs.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proeza.sgs.business.entity.dto.ArticuloDTO;
import com.proeza.sgs.business.entity.dto.ClaseDTO;
import com.proeza.sgs.business.entity.dto.ResourceDTO;
import com.proeza.sgs.business.entity.dto.TipoDTO;
import com.proeza.sgs.business.service.IArticuloService;
import com.proeza.sgs.business.service.IClaseService;

@RestController
@RequestMapping("rest/product")
public class ArticulosRestController {

    private static final Logger log = Logger.getLogger(ArticulosRestController.class);

    @Autowired
    private IArticuloService productService;

    @Autowired
    private IClaseService claseService;

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<ArticuloDTO> search (@RequestParam("filter") String filter) {
        return this.productService.findByStringFilter(filter);
    }

    @RequestMapping(value = "find", method = RequestMethod.POST)
    public ArticuloDTO find (@RequestBody String code) {
        return this.productService.findByCode(code);
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

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove (@RequestBody String code) {
        this.productService.remove(code);
    }

    @RequestMapping(value = "addImage", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addImage (@RequestParam("artCode") String code, @RequestParam("file") MultipartFile file) {
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

    @RequestMapping(value = "classesByCategory", method = RequestMethod.POST)
    public List<ClaseDTO> classesByCategory (@RequestBody String categoryCode) {
        return this.productService.classesByCategory(categoryCode);
    }

    @RequestMapping(value = "getClasses", method = RequestMethod.POST)
    public List<ClaseDTO> getClasses () {
        return this.claseService.findAll();
    }

    @RequestMapping(value = "classByCode", method = RequestMethod.POST)
    public ClaseDTO classByCode (@RequestBody String classCode) {
        return this.claseService.findByCode(classCode);
    }

    @RequestMapping(value = "addClass", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addClass (@RequestBody ClaseDTO prodClass) {
        this.claseService.create(prodClass);
    }

    @RequestMapping(value = "deleteClass", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass (@RequestBody ClaseDTO prodClass) {
        this.claseService.delete(prodClass);
    }

    @RequestMapping(value = "updateClass", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClass (@RequestBody ClaseDTO prodClass) {
        this.claseService.update(prodClass);
    }

    @RequestMapping(value = "typeByclasses", method = RequestMethod.POST)
    public List<TipoDTO> typeByclasses (@RequestBody String classCode) {
        return this.productService.typeByclasses(classCode);
    }
}