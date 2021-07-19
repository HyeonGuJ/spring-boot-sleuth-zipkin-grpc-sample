package com.hyeonguj.tracing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@Slf4j
@RestController

public class PrivateController {


    private RestTemplate restTemplate;

    public PrivateController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping("/success")
    public String success() {

        log.info("### [privateService] - called ###");

        return "privateService's response - success";
    }


}
