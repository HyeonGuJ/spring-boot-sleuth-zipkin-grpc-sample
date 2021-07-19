package com.hyeonguj.tracing.controller;

import com.hyeonguj.tracing.client.GrpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class PrivateController {

    private final GrpClient grpcClient;

    @GetMapping("/success")
    public String success() {

        log.info("### [grpcClient] - called ###");
        grpcClient.sayHello(" -grpcClient-");
        return "grpcClient's response - success";
    }

    @GetMapping("/slow")
    public String slow() {

        log.info("### [grpcClient] - called ###");

        try {
            grpcClient.sayHello(" -grpcClient-");
            Thread.sleep(300);
        } catch (InterruptedException e) {
            log.info("[grpcClient] - catch exception!! - (do nothing)");
        }


        return "grpcClient's response - slow";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @GetMapping("/fail")
    public String fail() {

        log.info("### [grpcClient] - called ###");
        grpcClient.sayHello(" -grpcClient-");
        return "grpcClient's response - fail";
    }

}
