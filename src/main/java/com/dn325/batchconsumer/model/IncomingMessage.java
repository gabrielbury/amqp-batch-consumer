package com.dn325.batchconsumer.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.*;
import org.springframework.amqp.core.Message;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@Data
@NoArgsConstructor
public class IncomingMessage implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("document")
    private String document;

    @Override
    public String toString() {
        return String.format("name=%s;document=%s", this.name, this.document);
    }

    public static IncomingMessage fromMessage(Message message) throws JsonProcessingException {
        return new ObjectMapper().readValue(new String(message.getBody(), StandardCharsets.UTF_8), IncomingMessage.class);
    }
}
