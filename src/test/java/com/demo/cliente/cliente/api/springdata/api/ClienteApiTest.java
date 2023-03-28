package com.demo.cliente.cliente.api.springdata.api;

import com.demo.cliente.cliente.api.springdata.dto.ClienteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClienteApiTest {

    private static final String API_ROOT = "http://localhost";
    private static final String API_RESOURCE_ALL = "/v1/api/cliente/all";
    private WebTestClient webTestClient;

    @LocalServerPort
    private int port = 0;

    @BeforeEach
    public void setup() {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl(API_ROOT + ":" + port)
                .responseTimeout(Duration.ofSeconds(3600))
                .build();
    }

    @Test
    void obtenerTodosLosClientes() {
        var clientes = webTestClient
                .get()
                .uri(API_RESOURCE_ALL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ClienteDto.class)
                .returnResult()
                .getResponseBody();
        assertNotNull(clientes);
        assert clientes.size() > 0;
    }
}