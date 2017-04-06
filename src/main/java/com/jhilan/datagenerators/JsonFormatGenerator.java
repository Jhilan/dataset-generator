package com.jhilan.datagenerators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhilan.collectors.FieldCollector;
import com.jhilan.exception.ConfigValidationException;
import com.jhilan.config.DataSetConfig;
import com.jhilan.config.YamlConfig;

/**
 * Created by jhilanalkarawi on 3/28/17.
 */
public class JsonFormatGenerator implements IDataGenerator {
    private static final Logger LOG = Logger.getLogger(JsonFormatGenerator.class);

    private final DataSetConfig dataSetConfig;
    private final List<FieldCollector> collectors;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String DEFAULT_FILE_NAME = "custom_dataset.json";
    private static final String PATH_FORMAT = "%s%s%s.json";

    public JsonFormatGenerator(final YamlConfig config) {
        collectors = registerCollectors(config.getConfiguration(), config.getFields());
        dataSetConfig = config.getConfiguration();
    }

    @Override
    public void generateDataset() throws IOException {
        final String datasetName =
                isSet(dataSetConfig.getDatasetFileName()) ? dataSetConfig.getDatasetFileName() : DEFAULT_FILE_NAME;

        final String filePath = isSet(dataSetConfig.getDatasetFilePath()) ?
                                String.format(PATH_FORMAT, dataSetConfig.getDatasetFilePath(), File.separator, datasetName) :
                                datasetName;

        final File file = new File(filePath);
        if (!file.createNewFile()) {
            throw new ConfigValidationException("file: " + filePath + " already exist.");
        }

        final Instant start = Instant.now();
        try (final FileWriter fw = new FileWriter(file)) {
            int documentsCount = dataSetConfig.getNumberOfDocuments();

            //write root json object
            fw.write("{\"docs\":[\n");
            int totalDocsGenerated = 0;

            while (documentsCount > 0) {
                final Map<String, Object> document = buildDocumentAsMap(collectors);
                fw.write(MAPPER.writeValueAsString(document) + ",\n");

                if (documentsCount % 10000 == 0) {
                    if (totalDocsGenerated != 0) {
                        LOG.info("total generated: " + totalDocsGenerated);
                    }
                }
                documentsCount--;
                totalDocsGenerated++;
            }
            //write end of root object
            fw.write("]}");

            final long duration = Duration.between(start, Instant.now()).getSeconds();
            final String message = "complete. total documents generated (%d) in %d %s.";
            if (duration < 60) {
                LOG.info(String.format(message, totalDocsGenerated, duration, "seconds."));
            } else {
                LOG.info(String.format(message, totalDocsGenerated, duration, "minutes."));
            }
        }
    }

    public static boolean isSet(String value) {
        return value != null && !value.isEmpty();
    }
}
