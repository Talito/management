package com.space.management.controller;

import com.space.management.ManagementApplication;
import com.space.management.configuration.CustomWebSecurityConfigurerAdapter;
import com.space.management.controllers.ProductController;
import com.space.management.model.Product;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MySQLContainer;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagementApplication.class)
@AutoConfigureWebTestClient
@ContextConfiguration(classes={ManagementApplication.class, CustomWebSecurityConfigurerAdapter.class})
public class MissionControllerIntegrationTest {

    @Rule
    public MySQLContainer mysql = getConfiguredMySQLContainer();

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Ignore(value = "To fix")
    public void addProduct() {
        webTestClient.post().uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(Product.builder()
                        .missionName("Apollo Test")
                        .acquisitionTime(LocalDateTime.now())
                        .url(null)
                        .price(BigDecimal.ONE)
                        .build()), Product.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectStatus().isOk();
    }

    private MySQLContainer getConfiguredMySQLContainer() {
        return new MySQLContainer()
                .withDatabaseName("management");
    }
}
