package ru.andr.producer.service;

import lombok.val;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.andr.producer.config.KafkaProducerConfig;
import ru.andr.producer.domain.StringCallbackListener;

@Service
public class SenderService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final StringCallbackListener stringCallbackListener;

    public SenderService(KafkaTemplate<String, String> kafkaTemplate, StringCallbackListener stringCallbackListener) {
        this.kafkaTemplate = kafkaTemplate;
        this.stringCallbackListener = stringCallbackListener;
    }

    public void sendMessage(String message) {
        val parts = message.split(";");

        String key = null;
        String value = message;
        if (parts.length > 1) {
            key = parts[0];
            value = parts[1];
        }
        System.out.println("KEY " + key);
        val future = kafkaTemplate.send(KafkaProducerConfig.TOPIC_NAME_2, key, value);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                stringCallbackListener.onFailure(ex);
            } else {
                stringCallbackListener.onSuccess(result);
            }
        });
    }
}
