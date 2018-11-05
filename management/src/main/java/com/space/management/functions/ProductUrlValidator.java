package com.space.management.functions;

import org.springframework.util.StringUtils;

import static java.util.Optional.ofNullable;

public final class ProductUrlValidator {
    private ProductUrlValidator() { }

    public static String validUrl(final String url) {
        return ofNullable(url)
                .filter(x -> StringUtils.startsWithIgnoreCase(url, "http") ||
                        StringUtils.startsWithIgnoreCase(url, "ftp"))
                .orElse(null);
    }
}
