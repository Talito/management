package com.space.management.controllers;

import com.space.management.model.Product;
import com.space.management.model.orders.ProductOrder;
import com.space.management.model.orders.ProductOrderConfirmation;
import com.space.management.services.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
// TODO()
public class ProductOrderController {

    private final ManagementService managementService;

    @Autowired
    public ProductOrderController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping
    public ProductOrderConfirmation orderProduct(@RequestBody final ProductOrder productOrder) {
        return managementService.processCustomerOrder(productOrder);
    }

    @GetMapping(path = "/{customerId}")
    public List<ProductOrderConfirmation> getProductOrder(@PathVariable(name = "customerId", required = false) final String customerId) {
       return managementService.getCustomerOrders(customerId);
    }

    @GetMapping(path = "/top/products")
    public List<Product> getTopProducts() {
        return null;
    }

    @GetMapping(path = "/top/missions")
    public List<Product> getTopMissions() {
        return null;
    }
}
