package com.proeza.sgs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JavascriptController {

    @RequestMapping(value = "js/{resource}", method = RequestMethod.GET)
    public String root(ModelMap model, @PathVariable("resource") String resource) {
        return resource + ".js";
    }

    @RequestMapping(value = "js/articulo/{resource}", method = RequestMethod.GET)
    public String articulo(ModelMap model, @PathVariable("resource") String resource) {
        return "articulo/" + resource + ".js";
    }

    @RequestMapping(value = "js/usuario/{resource}", method = RequestMethod.GET)
    public String usuario(ModelMap model, @PathVariable("resource") String resource) {
        return "usuario/" + resource + ".js";
    }
}