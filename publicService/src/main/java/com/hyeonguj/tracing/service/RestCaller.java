package com.hyeonguj.tracing.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class RestCaller {
    private RestTemplate restTemplate;

    public RestCaller(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Async
    public CompletableFuture<String> getForString(String url) {
        String response = restTemplate.getForObject(url, String.class);
        return CompletableFuture.completedFuture(response);
    }
}
