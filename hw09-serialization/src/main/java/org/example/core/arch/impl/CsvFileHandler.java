package org.example.core.arch.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.example.core.arch.AbstractFileHandler;
import org.example.core.entity.TargetStructure;

import java.io.*;
import java.util.List;

public class CsvFileHandler extends AbstractFileHandler {

    @Override
    protected String getFileExtension() {
        return ".csv";
    }

    @Override
    public void writeToFile(List<TargetStructure> data, String fileName) throws IOException {
        try (Writer writer = new FileWriter(getPath(fileName).toFile())) {

            StatefulBeanToCsv<TargetStructure> sbc = new StatefulBeanToCsvBuilder<TargetStructure>(writer)
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            sbc.write(data);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TargetStructure> readFromFile(String fileName) throws IOException {
        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(getPath(fileName).toString())))) {
            final var strategy =
                    new HeaderColumnNameMappingStrategy<TargetStructure>();
            strategy.setType(TargetStructure.class);
            final var csv = new CsvToBean<TargetStructure>();
            csv.setMappingStrategy(strategy);
            csv.setCsvReader(reader);
            return csv.parse();
        }
    }
}
