package com.space.management.functions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductUrlValidatorTest {

    @Test
    public void givenNullUrlShouldReturnNullUrl() {
        String result = ProductUrlValidator.validUrl(null);
        assertThat(result).isEqualTo(null);
    }

    @Test
    public void givenEmptyUrlShouldReturnNullUrl() {
        String result = ProductUrlValidator.validUrl("");
        assertThat(result).isEqualTo(null);
    }

    @Test
    public void givenUrlHTTPShouldReturnUrl() {
        String result = ProductUrlValidator.validUrl("http://blabla");
        assertThat(result).isEqualTo("http://blabla");
    }

    @Test
    public void givenUrlFTPShouldReturnUrl() {
        String result = ProductUrlValidator.validUrl("ftp://blabla");
        assertThat(result).isEqualTo("ftp://blabla");
    }
}
