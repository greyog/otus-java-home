package org.example.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Root implements Serializable {

    @JsonProperty("chat_sessions")
    private List<ChatSession> chatSessions;
}
