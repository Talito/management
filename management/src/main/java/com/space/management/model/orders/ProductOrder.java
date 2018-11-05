package com.space.management.model.orders;

import com.space.management.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {

    private String customerId;
    private List<Product> products;
}
