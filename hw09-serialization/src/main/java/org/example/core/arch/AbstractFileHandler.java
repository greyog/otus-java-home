package org.example.core.arch;

import org.example.core.entity.TargetStructure;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public abstract class AbstractFileHandler implements ConvertedFileHandler {

    private static final String SEPARATOR = File.separator;
    private static final String OUT_DIRECTORY = "out";
    private final String filePath = ClassLoader.getSystemResource(OUT_DIRECTORY).getPath();

    protected Path getPath(String fileName) {
        return Path.of(filePath + SEPARATOR + fileName + getFileExtension());
    }

    protected abstract String getFileExtension();

    @Override
    public abstract void writeToFile(List<TargetStructure> data, String fileName) throws IOException;

    @Override
    public abstract List<TargetStructure> readFromFile(String fileName) throws IOException;
}
