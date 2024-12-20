package ru.andr.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.andr.producer.config.KafkaProducerConfig;
import ru.andr.producer.domain.StringCallbackListener;

@Service
@RequiredArgsConstructor
public class SenderService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final StringCallbackListener stringCallbackListener;

    public void sendMessages(String message) {
        val parts = message.split(";");

        String key = null;
        String value = message;
        if (parts.length > 1) {
            key = parts[0];
            value = parts[1];
        }

        send(KafkaProducerConfig.TOPIC_NAME_1, key, value);
        send(KafkaProducerConfig.TOPIC_NAME_2, key, value);
    }

    private void send(String topic, String key, String value) {
        val future = kafkaTemplate.send(topic, key, value);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                stringCallbackListener.onFailure(ex);
            } else {
                stringCallbackListener.onSuccess(result);
            }
        });
    }
}
