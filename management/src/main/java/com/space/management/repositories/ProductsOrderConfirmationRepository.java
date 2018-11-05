package com.space.management.repositories;

import com.space.management.model.orders.ProductOrderConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsOrderConfirmationRepository extends JpaRepository<ProductOrderConfirmation, Long> {

    List<ProductOrderConfirmation> findAllByCustomerId(final String customerId);
}
