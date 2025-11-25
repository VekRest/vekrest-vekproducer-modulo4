package com.vekrest.vekproducer.vekproducer.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic clientRegisteredTopic(
            @Value("${spring.kafka.topic.client.registered}") String topicName,
            @Value("${spring.kafka.partitions}") int partitions,
            @Value("${spring.kafka.replicas}") short replicas) {
        return new NewTopic(topicName, partitions, replicas);
    }
}