package com.vekrest.vekproducer.vekproducer.event;

import com.vekrest.vekproducer.vekproducer.entities.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class ClientEventProducer {
    private static final Logger LOG = LoggerFactory.getLogger(ClientEventProducer.class);

    private final String topic;
    private final KafkaTemplate<String, Client> kafkaTemplate;

    public ClientEventProducer(
            KafkaTemplate<String, Client> kafkaTemplate,
            @Value("${spring.kafka.topic.client.registered}") String topic
    ) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Client client) throws ExecutionException, InterruptedException, TimeoutException {
        LOG.info("Produzindo evento de cliente para o Kafka: {}", client.toString());
        kafkaTemplate.send(topic, UUID.randomUUID().toString(), client)
                .get(5000, TimeUnit.MILLISECONDS);
    }
}
