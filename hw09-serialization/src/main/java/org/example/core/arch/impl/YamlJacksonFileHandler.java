package org.example.core.arch.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.core.arch.AbstractFileHandler;
import org.example.core.entity.TargetStructure;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class YamlJacksonFileHandler extends AbstractFileHandler {

    @Override
    protected String getFileExtension() {
        return ".yaml";
    }

    @Override
    public void writeToFile(List<TargetStructure> data, String fileName) throws IOException {
        var objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();
        var convertedYaml = objectMapper.writeValueAsString(data);
        Files.writeString(getPath(fileName), convertedYaml);
    }


    @Override
    public List<TargetStructure> readFromFile(String fileName) throws IOException {
        var objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();
        return objectMapper.readValue(getPath(fileName).toFile(),
                new TypeReference<>() {
                });

    }
}
