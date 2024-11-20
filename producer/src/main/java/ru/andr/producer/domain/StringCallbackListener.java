package ru.andr.producer.domain;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class StringCallbackListener {

    private final CompletableFuture<SendResult<String, String>> completableFuture;

    public StringCallbackListener() {
        this.completableFuture = new CompletableFuture<>();
    }

    public void onFailure(Throwable ex) {
        System.out.println("Failed: " + ex.getMessage());
        completableFuture.completeExceptionally(ex);
    }

    public void onSuccess(SendResult<String, String> result) {
        if (result == null || result.getRecordMetadata() == null) {
            throw new RuntimeException("Cannot!");
        }

        RecordMetadata metadata = result.getRecordMetadata();
        System.out.println("Success: " + metadata.topic() +
                "\nOffset: " + metadata.offset() +
                "\nPartition: " + metadata.partition());

        completableFuture.complete(result);
    }
}
