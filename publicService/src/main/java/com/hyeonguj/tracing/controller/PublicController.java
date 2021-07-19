package com.hyeonguj.tracing.controller;

import com.hyeonguj.tracing.service.RestCaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Slf4j
@RestController

public class PublicController {


    private RestTemplate restTemplate;
    private RestCaller restCaller;

    public PublicController(RestTemplateBuilder builder, RestCaller restCaller) {
        this.restTemplate = builder.build();
        this.restCaller = restCaller;
    }


    @GetMapping("/success")
    public String success() {

        log.info("### [publicService] - called ###");

        log.info("### [publicService] - return  : {}", restTemplate.getForObject("http://localhost:8082/success", String.class));
        log.info("### [publicService] - return  : {}", restTemplate.getForObject("http://localhost:8083/success", String.class));


        return "pong";
    }

    @GetMapping("/success/async")
    public String success2() {

        log.info("### [publicService] - called ###");

        List<String> urls = Arrays.asList("http://localhost:8082/success"
                , "http://localhost:8083/success");


        List<CompletableFuture<String>> result = new ArrayList<>();

        urls.stream().forEach(url -> {
            CompletableFuture<String> response = restCaller.getForString(url);
            result.add(response);
        });


        //blocking - wait for response
        result.stream().forEach(e -> {
            try {
                log.info("### [publicService] - return  : {}", e.get());
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        });

        return "pong";
    }


    @GetMapping("/slow")
    public String slow() {

        log.info("### [publicService] - called ###");

        List<String> urls = Arrays.asList("http://localhost:8082/success"
                , "http://localhost:8083/slow");

        List<CompletableFuture<String>> result = new ArrayList<>();

        urls.stream().forEach(url -> {
            CompletableFuture<String> response = restCaller.getForString(url);
            result.add(response);
        });


        //blocking - wait for response
        result.stream().forEach(e -> {
            try {
                log.info("### [publicService] - return  : {}", e.get());
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        });


        return "pong";
    }

    @GetMapping("/fail")
    public String fail() {

        log.info("### [publicService] - called ###");
        log.info("### [publicService] - return  : {}", restTemplate.getForObject("http://localhost:8082/success", String.class));
        log.info("### [publicService] - return  : {}", restTemplate.getForObject("http://localhost:8083/fail", String.class));


        return "pong";
    }

    @GetMapping("/fail/parallel")
    public String parallel() {

        log.info("### [publicService] - called ###");

        List<String> urls = Arrays.asList("http://localhost:8082/success"
                , "http://localhost:8083/success");

        urls.parallelStream()
                .forEach(url ->
                        log.info("### [publicService] - return  : {}", restTemplate.getForObject(url, String.class))
                );

        return "pong";
    }
}