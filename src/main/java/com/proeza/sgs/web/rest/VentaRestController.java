package com.proeza.sgs.web.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.sgs.business.service.IVentaService;
import com.proeza.sgs.business.service.dto.QuickSaleDTO;

@RestController
@RequestMapping("rest/venta")
public class VentaRestController {

    @Autowired
    private IVentaService ventaService;

    @RequestMapping(value = "quickSale", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void quickSale (@RequestBody QuickSaleDTO dto, Principal principal) {
        this.ventaService.quickSale(dto);
    }
}