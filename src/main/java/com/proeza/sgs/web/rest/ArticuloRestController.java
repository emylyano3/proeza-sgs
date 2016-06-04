package com.proeza.sgs.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proeza.core.resources.message.IMessageResolver;
import com.proeza.sgs.business.entity.dto.ArticuloDTO;
import com.proeza.sgs.business.entity.dto.ClaseDTO;
import com.proeza.sgs.business.entity.dto.MarcaDTO;
import com.proeza.sgs.business.entity.dto.ResourceDTO;
import com.proeza.sgs.business.entity.dto.RubroDTO;
import com.proeza.sgs.business.entity.dto.TipoDTO;
import com.proeza.sgs.business.service.IArticuloService;
import com.proeza.sgs.business.service.IClaseService;
import com.proeza.sgs.business.service.IMarcaService;
import com.proeza.sgs.business.service.IRubroService;
import com.proeza.sgs.business.service.ITipoService;
import com.proeza.sgs.web.handler.ErrorInfo;
import com.proeza.sgs.web.handler.ErrorTracker;

@RestController
@RequestMapping("rest/product")
public class ArticuloRestController {

    private static final Logger log             = Logger.getLogger(ArticuloRestController.class);

    private static final String INTEGRITY_ERROR = "prod.integrity.error";
    private static final String NOTFOUND_ERROR  = "prod.notfound.error";

    @Autowired
    private IArticuloService    productService;

    @Autowired
    private IClaseService       claseService;

    @Autowired
    private ITipoService        tipoService;

    @Autowired
    private IRubroService       rubroService;

    @Autowired
    private IMarcaService       marcaService;

    @Autowired
    private IMessageResolver    messageResolver;

    @Autowired
    private ErrorTracker        errorTracker;

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo conflict(HttpServletRequest request, Exception e) {
        this.errorTracker.trackError(e);
        ErrorInfo errorInfo = new ErrorInfo(request.getRequestURL().toString(), this.messageResolver.getMessage(INTEGRITY_ERROR, request));
        log.info("Error handled: " + errorInfo);
        return errorInfo;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataRetrievalFailureException.class)
    public ErrorInfo notFound(HttpServletRequest request, Exception e) {
        this.errorTracker.trackError(e);
        ErrorInfo errorInfo = new ErrorInfo(request.getRequestURL().toString(), this.messageResolver.getMessage(NOTFOUND_ERROR, request));
        log.info("Error handled: " + errorInfo);
        return errorInfo;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<ArticuloDTO> search(@RequestParam("filter") String filter) {
        return this.productService.findByStringFilter(filter);
    }

    @RequestMapping(value = "find", method = RequestMethod.POST)
    public ArticuloDTO find(@RequestBody String code) {
        return this.productService.findByCode(code);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody ArticuloDTO articulo) {
        this.productService.update(articulo);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody ArticuloDTO articulo) {
        this.productService.create(articulo);
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@RequestBody String code) {
        this.productService.remove(code);
    }

    @RequestMapping(value = "addImage", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addImage(@RequestParam("artCode") String code, @RequestParam("file") MultipartFile file) {
        try {
            this.productService.addImage(code, "Imagen", "Imagen del artículo", MediaType.IMAGE_JPEG, file.getBytes());
        } catch (Exception e) {
            log.error("Error agregando la imagen al articulo causado por: " + e.getMessage(), e);
        }
    }

    @RequestMapping(value = "getImage/{article}/{imageId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getImage(@PathVariable String article, @PathVariable Long imageId) {
        try {
            return this.productService.getImage(article, imageId);
        } catch (Exception e) {
            log.error("Error agregando la imagen al articulo causado por: " + e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "getThumbnail/{article}/{imageId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getThumbnail(@PathVariable String article, @PathVariable Long imageId) {
        try {
            return this.productService.getThumbnail(article, imageId);
        } catch (Exception e) {
            log.error("Error agregando la imagen al articulo causado por: " + e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "getImages/{article}", method = RequestMethod.POST)
    public List<ResourceDTO> getImages(@PathVariable String article) {
        try {
            return this.productService.getImagesAvail(article);
        } catch (Exception e) {
            log.error("Error agregando la imagen al articulo causado por: " + e.getMessage(), e);
            return new ArrayList<ResourceDTO>(0);
        }
    }

    @RequestMapping(value = "classesByCategory", method = RequestMethod.POST)
    public List<ClaseDTO> classesByCategory(@RequestBody String categoryCode) {
        return this.productService.classesByCategory(categoryCode);
    }

    @RequestMapping(value = "getCategories", method = RequestMethod.POST)
    public List<RubroDTO> getCategories() {
        return this.rubroService.findAll();
    }

    @RequestMapping(value = "categoryByCode", method = RequestMethod.POST)
    public RubroDTO categoryByCode(@RequestBody String categoryCode) {
        return this.rubroService.findByCode(categoryCode);
    }

    @RequestMapping(value = "addCategory", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCategory(@RequestBody RubroDTO prodCategory) {
        this.rubroService.create(prodCategory);
    }

    @RequestMapping(value = "deleteCategory", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@RequestBody RubroDTO prodCategory) {
        this.rubroService.delete(prodCategory);
    }

    @RequestMapping(value = "updateCategory", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@RequestBody RubroDTO prodCategory) {
        this.rubroService.update(prodCategory);
    }

    @RequestMapping(value = "brandByCode", method = RequestMethod.POST)
    public MarcaDTO brandByCode(@RequestBody String brandCode) {
        return this.marcaService.findByCode(brandCode);
    }

    @RequestMapping(value = "getBrands", method = RequestMethod.POST)
    public List<MarcaDTO> getBrands() {
        return this.marcaService.findAll();
    }

    @RequestMapping(value = "addBrand", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addBrand(@RequestBody MarcaDTO prodBrand) {
        this.marcaService.create(prodBrand);
    }

    @RequestMapping(value = "deleteBrand", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@RequestBody MarcaDTO prodBrand) {
        this.marcaService.delete(prodBrand);
    }

    @RequestMapping(value = "updateBrand", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBrand(@RequestBody MarcaDTO prodBrand) {
        this.marcaService.update(prodBrand);
    }

    @RequestMapping(value = "getClasses", method = RequestMethod.POST)
    public List<ClaseDTO> getClasses() {
        return this.claseService.findAll();
    }

    @RequestMapping(value = "classByCode", method = RequestMethod.POST)
    public ClaseDTO classByCode(@RequestBody String classCode) {
        return this.claseService.findByCode(classCode);
    }

    @RequestMapping(value = "addClass", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addClass(@RequestBody ClaseDTO prodClass) {
        this.claseService.create(prodClass);
    }

    @RequestMapping(value = "deleteClass", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@RequestBody ClaseDTO prodClass) {
        this.claseService.delete(prodClass);
    }

    @RequestMapping(value = "updateClass", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClass(@RequestBody ClaseDTO prodClass) {
        this.claseService.update(prodClass);
    }

    @RequestMapping(value = "typesByClass", method = RequestMethod.POST)
    public List<TipoDTO> typesByClass(@RequestBody String classCode) {
        return this.productService.typesByClass(classCode);
    }

    @RequestMapping(value = "getTypes", method = RequestMethod.POST)
    public List<TipoDTO> getTypes() {
        return this.tipoService.findAll();
    }

    @RequestMapping(value = "typeByCode", method = RequestMethod.POST)
    public TipoDTO typeByCode(@RequestBody String typeCode) {
        return this.tipoService.findByCode(typeCode);
    }

    @RequestMapping(value = "addType", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addType(@RequestBody TipoDTO prodType) {
        this.tipoService.create(prodType);
    }

    @RequestMapping(value = "deleteType", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteType(@RequestBody TipoDTO prodType) {
        this.tipoService.delete(prodType);
    }

    @RequestMapping(value = "updateType", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateType(@RequestBody TipoDTO prodType) {
        this.tipoService.update(prodType);
    }
}