package com.hyeonguj.tracing.client;

import com.hyeonguj.tracing.HelloRequest;
import com.hyeonguj.tracing.HelloResponse;
import com.hyeonguj.tracing.HelloServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrpClient {

    private final HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;

    public String sayHello(String name) {

        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setName(name)
                .build();

        HelloResponse helloResponse = helloServiceBlockingStub.sayHello(helloRequest);
        return helloResponse.getMessage();
    }
}