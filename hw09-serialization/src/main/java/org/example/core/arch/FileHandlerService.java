package org.example.core.arch;

import org.example.core.entity.TargetStructure;

import java.io.IOException;
import java.util.List;

public interface FileHandlerService {

    void writeToFile(List<TargetStructure> data, String fileName) throws IOException;

    List<TargetStructure> readFromFile(String fileName) throws IOException;
}
