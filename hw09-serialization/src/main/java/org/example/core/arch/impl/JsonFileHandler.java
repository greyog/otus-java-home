package org.example.core.arch.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.core.arch.AbstractFileHandler;
import org.example.core.entity.TargetStructure;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class JsonFileHandler extends AbstractFileHandler {

    @Override
    protected String getFileExtension() {
        return ".json";
    }

    @Override
    public void writeToFile(List<TargetStructure> data, String fileName) throws IOException {
        var objectMapper = new ObjectMapper();
        var convertedJson = objectMapper.writeValueAsString(data);
        Files.writeString(getPath(fileName), convertedJson);
    }


    @Override
    public List<TargetStructure> readFromFile(String fileName) throws IOException {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(getPath(fileName).toFile(),
                new TypeReference<>() {
                });

    }
}
