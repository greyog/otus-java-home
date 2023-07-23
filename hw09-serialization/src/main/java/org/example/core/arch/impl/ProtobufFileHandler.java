package org.example.core.arch.impl;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.example.core.arch.AbstractFileHandler;
import org.example.core.entity.TargetStructure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ProtobufFileHandler extends AbstractFileHandler {

    private static final LinkedBuffer buffer = LinkedBuffer.allocate(512);
    private static final Schema<TargetStructure> schema = RuntimeSchema.getSchema(TargetStructure.class);

    @Override
    protected String getFileExtension() {
        return ".protobin";
    }

    @Override
    public void writeToFile(List<TargetStructure> data, String fileName) throws IOException {
        try (var fileOutputStream = new FileOutputStream(getPath(fileName).toFile())) {
            ProtostuffIOUtil.writeListTo(fileOutputStream,
                    data,
                    schema,
                    buffer);
        }
    }


    @Override
    public List<TargetStructure> readFromFile(String fileName) throws IOException {
        try (var fileInputStream = new FileInputStream(getPath(fileName).toFile())) {
            return ProtostuffIOUtil.parseListFrom(fileInputStream, schema);
        }
    }
}
