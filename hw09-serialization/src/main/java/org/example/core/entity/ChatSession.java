package org.example.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ChatSession implements Serializable {

    @JsonProperty("chat_id")
    private int chatId;

    @JsonProperty("chat_identifier")
    private String chatIdentifier;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("is_deleted")
    private int isDeleted;

    private List<Member> members;

    private List<Message> messages;
}
