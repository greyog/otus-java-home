package org.example.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.example.core.util.MessageDateReadDeserializer;
import org.example.core.util.MessageSendDateDeserializer;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Message implements Serializable {

    @JsonProperty("ROWID")
    private int rowId;

    private String attributedBody;

    @JsonProperty("belong_number")
    private String belongNumber;

    @JsonDeserialize(using = MessageDateReadDeserializer.class)
    private LocalDateTime date;

    @JsonProperty("date_read")
    @JsonDeserialize(using = MessageDateReadDeserializer.class)
    private Object dateRead;

    private String guid;

    @JsonProperty("handle_id")
    private int handleId;

    @JsonProperty("has_dd_results")
    private int hasDdResults;

    @JsonProperty("is_deleted")
    private int isDeleted;

    @JsonProperty("is_from_me")
    private int isFromMe;

    @JsonProperty("send_date")
    @JsonDeserialize(using = MessageSendDateDeserializer.class)
    private LocalDateTime sendDate;

    @JsonProperty("send_status")
    private int sendStatus;

    private String service;

    private String text;
}