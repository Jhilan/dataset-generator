package com.jhilan.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YamlConfig {
    private final DataSetConfig configuration;
    private final MetaDataFields fields;

    @JsonCreator
    public YamlConfig(@JsonProperty("configuration") final DataSetConfig configuration, @JsonProperty("fields") final MetaDataFields fields) {
        this.configuration = configuration;
        this.fields = fields;
    }

    public DataSetConfig getConfiguration() {
        return configuration;
    }

    public MetaDataFields getFields() {
        return fields;
    }

    public DataSetConfig.Format getFormat() {
        return configuration.getOutputFormat();
    }
}
