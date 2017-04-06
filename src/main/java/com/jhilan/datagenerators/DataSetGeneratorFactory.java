package com.jhilan.datagenerators;

import com.jhilan.exception.ConfigValidationException;
import com.jhilan.config.DataSetConfig;
import com.jhilan.config.YamlConfig;

public final class DataSetGeneratorFactory {
    private DataSetGeneratorFactory() {}

    public static IDataGenerator createDataGenerator(YamlConfig yamlConfig) {
        if (yamlConfig.getFormat() == DataSetConfig.Format.JSON) {
            return new JsonFormatGenerator(yamlConfig);
        } else if (yamlConfig.getFormat() == DataSetConfig.Format.DAT) {
            return new DatFormatGenrator(yamlConfig);
        } else {
            throw new ConfigValidationException("not valid output Format requested: " + yamlConfig.getFormat());
        }
    }
}
