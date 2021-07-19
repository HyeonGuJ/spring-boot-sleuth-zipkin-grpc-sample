package com.hyeonguj.tracing.service;


import com.hyeonguj.tracing.HelloRequest;
import com.hyeonguj.tracing.HelloResponse;
import com.hyeonguj.tracing.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;


@Slf4j
@GRpcService
public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloRequest request,
                         StreamObserver<HelloResponse> responseObserver) {

        log.info("[grpcServer] called");

        //get message
        String message = new StringBuilder().append("hello ")
                .append(request.getName())
                .toString();


        //build response
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(message)
                .build();

        //send message and complete.
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}