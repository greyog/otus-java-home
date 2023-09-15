package org.example.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Member implements Serializable {

    private String first;

    @JsonProperty("handle_id")
    private int handleId;

    @JsonProperty("image_path")
    private String imagePath;

    private String last;

    private String middle;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String service;

    @JsonProperty("thumb_path")
    private String thumbPath;
}