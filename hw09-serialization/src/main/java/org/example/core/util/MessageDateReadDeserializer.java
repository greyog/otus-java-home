package org.example.core.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class MessageDateReadDeserializer extends StdDeserializer<LocalDateTime> {

    private static final long MAC_UNIX_NANO_TIME_OFFSET = 978307200 * 1_000_000_000L;
    private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
            .toFormatter();

    protected MessageDateReadDeserializer(Class<LocalDateTime> vc) {
        super(vc);
    }

    protected MessageDateReadDeserializer() {
        this(null);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String value = p.getText();
        if (!value.isEmpty()) {
            long nano = Long.parseLong(value) + MAC_UNIX_NANO_TIME_OFFSET;
            return LocalDateTime.ofEpochSecond(nano / 1_000_000_000L,
                    (int) (nano % 1_000_000_000),
                    ZoneOffset.ofHours(3));
        }
        return null;
    }
}
