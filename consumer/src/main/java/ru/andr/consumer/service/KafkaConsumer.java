package ru.andr.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

//    @KafkaListener(topicPartitions = @TopicPartition(topic = "homework_kafka",
//    partitionOffsets = {
//            @PartitionOffset(partition = "0", initialOffset = "0"),
//            @PartitionOffset(partition = "3", initialOffset = "0")
//    }))

    @KafkaListener(topics = "homework_kafka_2")
    public void listen(@Header(KafkaHeaders.OFFSET) long offset,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                       @Payload String message) {
        System.out.println("Consume message: (" + key + ") " + message + "\nOffset: " + offset + "\nPartition: " + partition);
    }
}
