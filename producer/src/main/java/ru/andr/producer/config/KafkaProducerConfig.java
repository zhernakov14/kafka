package ru.andr.producer.config;

import lombok.val;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
public class KafkaProducerConfig {

    public static final String TOPIC_NAME_1 = "homework_kafka_1";
    public static final String TOPIC_NAME_2 = "homework_kafka_2";
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String addresses;

    @Bean
    public NewTopic homeworkTopicWithoutPartitions() {
        return TopicBuilder
                .name(TOPIC_NAME_1)
                .build();
    }

    @Bean
    public NewTopic homeworkTopicWithPartitions() {
        return TopicBuilder
                .name(TOPIC_NAME_2)
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        val config = new HashMap<String, Object>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, addresses);
        config.put(ProducerConfig.BATCH_SIZE_CONFIG, 3);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
