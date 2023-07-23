package org.example.core.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.core.arch.FileHandlerService;
import org.example.core.arch.impl.JavaFileHandler;
import org.example.core.entity.Root;
import org.example.core.util.DataConverter;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    private static final String FILENAME = "converted";

    public static void main(String[] args) throws IOException, URISyntaxException {
        var rawData = ClassLoader.getSystemResource("sms-256866-480df9.json");
        var objectMapper = new ObjectMapper();
        var root = objectMapper.readValue(rawData, Root.class);

        var convertedData = DataConverter.convert(root);
        System.out.println(convertedData);
        System.out.println("-----------------------------------------------------------------------------------------");
//        ConvertedFileHandler fileHandler = new JsonFileHandler();
//        ConvertedFileHandler fileHandler = new XmlFileHandler();
//        ConvertedFileHandler fileHandler = new CsvFileHandler();
//        FileHandlerService fileHandler = new YamlJacksonFileHandler();
//        FileHandlerService fileHandler = new ProtobufFileHandler();
        FileHandlerService fileHandler = new JavaFileHandler();

        fileHandler.writeToFile(convertedData, FILENAME);

        System.out.println(fileHandler.readFromFile(FILENAME));
    }
}
