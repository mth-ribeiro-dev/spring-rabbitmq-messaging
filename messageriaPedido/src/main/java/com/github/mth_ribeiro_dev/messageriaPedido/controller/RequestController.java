package com.github.mth_ribeiro_dev.messageriaPedido.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("api/v1")
public class RequestController {

    private final RestClient serviceEmail = RestClient.create("http://localhost:8081");

    @PostMapping("/request")
    public void request(){
        serviceEmail.post()
                .uri("/api/v1/email")
                .retrieve()
                .toBodilessEntity();

        System.out.println("request sent");
    }
}
