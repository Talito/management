package com.space.management.functions;

import com.space.management.model.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.space.management.functions.ProductUrlStripper.stripUrls;
import static com.space.management.model.orders.OrderStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductUrlStripperTest {

    @Test
    public void givenNullOrderStatusShouldReturnProductsWithoutUrl() {
        List<Product> products = mockSomeProducts();

        List<Product> strippedProducts = stripUrls(null, products);

        assertThat(strippedProducts).isEqualTo(products.stream().map(x -> x.withUrl(null)).collect(Collectors.toList()));
    }

    @Test
    public void givenPendingOrderStatusShouldReturnProductsWithoutUrl() {
        List<Product> products = mockSomeProducts();

        List<Product> strippedProducts = stripUrls(PENDING, products);

        assertThat(strippedProducts).isEqualTo(products.stream().map(x -> x.withUrl(null)).collect(Collectors.toList()));
    }

    @Test
    public void givenCancelledOrderStatusShouldReturnProductsWithoutUrl() {
        List<Product> products = mockSomeProducts();

        List<Product> strippedProducts = stripUrls(CANCELLED, products);

        assertThat(strippedProducts).isEqualTo(products.stream().map(x -> x.withUrl(null)).collect(Collectors.toList()));
    }

    @Test
    public void givenProcessedStatusShouldReturnProductsWithUrl() {
        List<Product> products = mockSomeProducts();

        List<Product> strippedProducts = stripUrls(PROCESSED, products);

        assertThat(strippedProducts).isEqualTo(products);
    }

    private static List<Product> mockSomeProducts() {
        return Stream.of(
                Product.builder()
                        .acquisitionTime(LocalDateTime.now())
                        .missionName("someMissionName")
                        .price(BigDecimal.ONE)
                        .productId(123L)
                        .url("https://some.url")
                        .build(),
                Product.builder()
                        .acquisitionTime(LocalDateTime.now())
                        .missionName("someMissionName")
                        .price(BigDecimal.ONE)
                        .productId(123L)
                        .url("https://some.url")
                        .build()
        ).collect(Collectors.toList());
    }
}
