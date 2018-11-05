package com.space.management.controllers;

import com.space.management.model.ImageryType;
import com.space.management.model.Product;
import com.space.management.services.ManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ManagementService managementService;

    @Autowired
    public ProductController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping
//    @PreAuthorize("hasRole('USER')")
    public Product addProduct(@RequestBody final Product product) {
        log.info("Adding product: " + product);
        return managementService.addProduct(product);
    }

    @DeleteMapping(path = "/{productId}")
    public void removeProduct(@PathVariable(name = "productId") final Long productId) {
        log.warn("Removing product: " + productId);
        managementService.removeProduct(productId);
    }

    @GetMapping
    public List<Product> getProductsBy(@RequestParam(name = "missionName", required = false) final String missionName,
                                       @RequestParam(name = "productType", required = false) final ImageryType productType,
                                       @RequestParam(name = "beforeAcquisitionDate", required = false) final LocalDateTime beforeAcquisitionDate,
                                       @RequestParam(name = "afterAcquisitionDate", required = false) final LocalDateTime afterAcquisitionDate) {
        // TODO(): move to service
        log.info("Getting products. Params: missionName => {}, productType => {}, beforeAcquisitionDate => {}, afterAcquisitionDate => {}",
                missionName, productType, beforeAcquisitionDate, afterAcquisitionDate);
        return managementService.findProductsBy(missionName, productType, beforeAcquisitionDate, afterAcquisitionDate);
    }

}
