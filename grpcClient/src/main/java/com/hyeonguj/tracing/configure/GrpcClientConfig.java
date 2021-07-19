package com.hyeonguj.tracing.configure;


import com.hyeonguj.tracing.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import org.springframework.cloud.sleuth.brave.instrument.grpc.SpringAwareManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GrpcClientConfig {



    private final SpringAwareManagedChannelBuilder springAwareManagedChannelBuilder;

    public GrpcClientConfig(SpringAwareManagedChannelBuilder springAwareManagedChannelBuilder) {
        this.springAwareManagedChannelBuilder = springAwareManagedChannelBuilder;
    }

    @Bean
    public HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub() {
        ManagedChannel channel = this.springAwareManagedChannelBuilder.forAddress("localhost", 8084).usePlaintext().build();
        return HelloServiceGrpc.newBlockingStub(channel);
    }


}