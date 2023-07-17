package org.example.core.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import org.example.core.util.MessageSendDateDeserializer;

import java.io.Serializable;
import java.time.LocalDateTime;

//список из полей
// <chat_sessions.chat_identifier> -
// <chat_sessions.members.last> -
// <chat_sessions.messages.belong_number> -
// <chat_sessions.messages.send_date> -
// <chat_sessions.messages.text>
// с группировкой по полю <chat_sessions.messages.belong_number>
// и сортировкой от более старых сообщений к более новым
@Data
@Builder
public class TargetStructure implements Serializable {

    @JsonProperty("chat_identifier")
    private String chatIdentifier;

    @JsonProperty("member_last")
    private String memberLast;

    @JsonProperty("belong_number")
    private String belongNumber;

    @JsonProperty("send_date")
    @JsonDeserialize(using = MessageSendDateDeserializer.class)
    private LocalDateTime sendDate;

    private String text;
}
