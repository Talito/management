package com.space.management.model.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.space.management.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_orders")
@Entity
public class ProductOrderConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String orderReferenceId;
    @NotNull
    private String customerId;
    private OrderStatus orderStatus;
    @JsonInclude
    @Transient
    List<Product> products;

}
