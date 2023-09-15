package org.example.core.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageSendDateDeserializer extends StdDeserializer<LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

    protected MessageSendDateDeserializer(Class<LocalDateTime> vc) {
        super(vc);
    }

    protected MessageSendDateDeserializer() {
        this(null);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String value = p.getText();
        if (!"".equals(value)) {
            return LocalDateTime.parse(value, formatter);
        }
        return null;
    }
}
