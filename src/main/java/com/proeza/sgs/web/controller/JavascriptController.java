package com.proeza.sgs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JavascriptController {

    @RequestMapping(value = "{resource}", method = RequestMethod.GET)
    public String home(ModelMap model, @PathVariable("resource") String resource) {
        return resource + ".js";
    }
}