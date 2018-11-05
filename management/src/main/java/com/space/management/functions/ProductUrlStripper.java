package com.space.management.functions;

import com.space.management.model.Product;
import com.space.management.model.orders.OrderStatus;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.space.management.model.orders.OrderStatus.PROCESSED;
import static java.util.Optional.ofNullable;

public final class ProductUrlStripper {
    private ProductUrlStripper() { };

    public static List<Product> stripUrls(final OrderStatus orderStatus, final List<Product> products) {
        return ofNullable(orderStatus)
                .filter(x -> PROCESSED.equals(orderStatus))
                .map(x -> products)
                .orElseGet(() -> removeUrl(products));
    }

    private static List<Product> removeUrl(final List<Product> products) {
        return ofNullable(products)
                .map(Collection::stream).orElse(Stream.empty())
                .map(x -> x.withUrl(null))
                .collect(Collectors.toList());
    }
}
