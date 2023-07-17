package org.example.core.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.core.entity.Root;
import org.example.core.util.DataConverter;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("sms-256866-480df9.json"))
                .getFile());
        ObjectMapper om = new ObjectMapper();
        Root root = om.readValue(file, Root.class);

//        System.out.println(root);

        var converted = DataConverter.convert(root);
        System.out.println(converted);
//todo add localdatetime serializer
        System.out.println(om.writeValueAsString(converted));

    }
}
