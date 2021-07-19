package com.hyeonguj.tracing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

        log.info("### [grpcServer] - called ###");

        return "grpcServer's response - success";
    }

    @GetMapping("/slow")
    public String slow() {

        log.info("### [grpcServer] - called ###");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info("[grpcServer] - catch exception!! - (do nothing)");
        }


        return "grpcServer's response - slow";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @GetMapping("/fail")
    public String fail() {

        log.info("### [grpcServer] - called ###");
        return "grpcServer's response - fail";
    }

}
