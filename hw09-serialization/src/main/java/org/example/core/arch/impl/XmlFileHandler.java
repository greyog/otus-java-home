package org.example.core.arch.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.core.arch.AbstractFileHandler;
import org.example.core.entity.TargetStructure;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class XmlFileHandler extends AbstractFileHandler {

    @Override
    protected String getFileExtension() {
        return ".xml";
    }

    @Override
    public void writeToFile(List<TargetStructure> data, String fileName) throws IOException {
        var xmlMapper = new XmlMapper();
        var xml = xmlMapper.writeValueAsString(data);
        Files.writeString(getPath(fileName), xml);
    }

    @Override
    public List<TargetStructure> readFromFile(String fileName) throws IOException {
        var xmlMapper = new XmlMapper();
        return xmlMapper.readValue(getPath(fileName).toFile(),
                new TypeReference<>() {
                });

    }
}
