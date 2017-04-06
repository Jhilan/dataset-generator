package com.jhilan;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import com.jhilan.datagenerators.DataSetGeneratorFactory;
import com.jhilan.datagenerators.IDataGenerator;
import com.jhilan.exception.ConfigValidationException;
import com.jhilan.exception.FileWriteException;
import com.jhilan.config.YamlConfig;

public final class Main {
    public static void main(String[] args) throws IOException {
        final Parser parser = new Parser(args);

        final File configFile = new File(parser.getConfigFilePath());

        if (!configFile.exists()) {
            throw new ConfigValidationException("config file not found: " + configFile.getAbsolutePath());
        }

        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        final YamlConfig config = mapper.readValue(configFile, YamlConfig.class);

        final IDataGenerator dataGenerator = DataSetGeneratorFactory.createDataGenerator(config);

        try {
            dataGenerator.generateDataset();
        } catch (final IOException e) {
            throw new FileWriteException(e.getMessage(), e);
        }
    }
}
