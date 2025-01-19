package com.dn325.batchconsumer.config.amqp;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.queue}")
    public String queueName;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setBatchListener(true); // Enable batch mode
        factory.setConsumerBatchEnabled(true); // Required for batch consumption
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL); // Recommended for batch processing
        factory.setPrefetchCount(10);
        factory.setBatchSize(10);
        return factory;
    }
}
