package com.asyncjava.completablefuture;


import com.asyncjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.asyncjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {

    public static void main(String[] args){
        HelloWorldService helloWorldService = new HelloWorldService();
        CompletableFuture.supplyAsync(() -> helloWorldService.helloWorld())
                .thenApply((result) -> result.toUpperCase())
                .thenAccept((result) -> {
                    log("Result is " + result);
                })
        .join();
        log("Done!");
    }

}
