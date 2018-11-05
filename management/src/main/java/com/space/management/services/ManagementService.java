package com.space.management.services;

import com.space.management.model.ImageryType;
import com.space.management.model.Mission;
import com.space.management.model.Product;
import com.space.management.model.orders.ProductOrder;
import com.space.management.model.orders.ProductOrderConfirmation;
import com.space.management.repositories.MissionsRepository;
import com.space.management.repositories.ProductsOrderConfirmationRepository;
import com.space.management.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.space.management.functions.ProductUrlStripper.stripUrls;
import static com.space.management.functions.ProductUrlValidator.validUrl;
import static com.space.management.model.orders.OrderStatus.PENDING;
import static java.util.Optional.ofNullable;

@Service
// TODO(): processing of orders should be extracted to its own service
public class ManagementService {

    private ProductsOrderConfirmationRepository productsOrderConfirmationRepository;
    private ProductsRepository productsRepository;
    private MissionsRepository missionsRepository;

    @Autowired
    public ManagementService(ProductsOrderConfirmationRepository productsOrderConfirmationRepository,
                             ProductsRepository productsRepository,
                             MissionsRepository missionsRepository) {
        this.productsOrderConfirmationRepository = productsOrderConfirmationRepository;
        this.productsRepository = productsRepository;
        this.missionsRepository = missionsRepository;
    }

    public Product addProduct(final Product product) {
        final String validUrl = validUrl(product.getUrl());
        return productsRepository.save(product.withUrl(validUrl));
    }

    public Mission addMission(final Mission mission) {
        return missionsRepository.save(mission);
    }

    public void removeProduct(final Long productId) {
        productsRepository.deleteById(productId);
    }

    public void removeMission(final Long missionId) {
        missionsRepository.deleteById(missionId);
    }

    public List<Mission> retrieveMissions() {
        return missionsRepository.findAll();
    }

    public final List<Product> findProductsBy(final String missionName,
                                              final ImageryType imageryType,
                                              final LocalDateTime before,
                                              final LocalDateTime after) {
        if (!StringUtils.isEmpty(missionName)) {
            return productsRepository.findAllByMissionName(missionName);
        } else if (!ObjectUtils.isEmpty(before) || !ObjectUtils.isEmpty(after)) {
            return productsRepository.findAllByAcquisitionTimeIsBetween(before, after);
        } else if (!ObjectUtils.isEmpty(imageryType)) {
            return this.findProductsByMissionImageryType(imageryType);
        }
        return productsRepository.findAll();
    }

    public final ProductOrderConfirmation processCustomerOrder(final ProductOrder order) {
        List<Product> existingProducts = productsRepository.findAllById(order.getProducts().stream().map(Product::getProductId).collect(Collectors.toList()));
        // TODO(): save on database the order confirmation
        return ProductOrderConfirmation.builder()
                .orderReferenceId(String.valueOf(UUID.randomUUID()))
                .orderStatus(PENDING)
                .customerId(order.getCustomerId())
                .products(stripUrls(PENDING, existingProducts))
                .build();
    }

    public List<ProductOrderConfirmation> getCustomerOrders(final String customerId) {
        return ofNullable(customerId)
                .map(productsOrderConfirmationRepository::findAllByCustomerId)
                .orElseGet(productsOrderConfirmationRepository::findAll);
    }

    private List<Product> findProductsByMissionImageryType(final ImageryType imageryType) {
        return missionsRepository.findAllByImageryType(imageryType)
                .stream()
                .filter(x -> ObjectUtils.isEmpty(x.getProducts()))
                .flatMap(x -> x.getProducts().stream())
                .collect(Collectors.toList());
    }
}
