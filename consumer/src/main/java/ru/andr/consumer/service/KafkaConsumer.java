package ru.andr.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "homework_kafka_1")
    public void listenFromTopic1(@Header(KafkaHeaders.OFFSET) long offset,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                       String message) {
        System.out.println("Consume message: (" + key + ") " + message + "\nOffset: " + offset + "\nPartition: " + partition);
    }

    @KafkaListener(topics = "homework_kafka_2")
    public void listenFromTopic2(@Header(KafkaHeaders.OFFSET) long offset,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                       @Payload String message) {
        System.out.println("Consume message: (" + key + ") " + message + "\nOffset: " + offset + "\nPartition: " + partition);
    }
}
