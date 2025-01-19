package com.dn325.batchconsumer.listener;

import com.dn325.batchconsumer.model.IncomingMessage;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BatchListener {
    @RabbitListener(queues = "${spring.rabbitmq.queue}", containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessage(List<Message> messages, Channel channel) throws IOException {
        System.out.println("Received batch of messages: " + messages.size());
        long lastTag = 0L;

        for (Message message : messages) {
            var incomingMessage = IncomingMessage.fromMessage(message);
            System.out.println("Processing: " + incomingMessage);
            lastTag = message.getMessageProperties().getDeliveryTag();
        }

        // Acknowledge all messages in the batch at once
        if (lastTag > 0) {
            channel.basicAck(lastTag, true);
        }
    }
}
