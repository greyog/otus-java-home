package org.example.core.arch.impl;

import org.example.core.arch.AbstractFileHandler;
import org.example.core.entity.TargetStructure;

import java.io.*;
import java.util.List;

public class JavaFileHandler extends AbstractFileHandler {

    @Override
    protected String getFileExtension() {
        return ".javabin";
    }

    @Override
    public void writeToFile(List<TargetStructure> data, String fileName) throws IOException {
        try (var fileOutputStream = new FileOutputStream(getPath(fileName).toFile())) {
            try (var objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(data);
            }
        }
    }


    @Override
    public List<TargetStructure> readFromFile(String fileName) throws IOException {
        List<TargetStructure> result = null;
        try (var fileInputStream = new FileInputStream(getPath(fileName).toFile())) {
            try (var objectInputStream = new ObjectInputStream(fileInputStream)) {
                result = (List<TargetStructure>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
