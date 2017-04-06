package com.jhilan.datagenerators;

import java.util.List;

import com.jhilan.collectors.FieldCollector;
import com.jhilan.config.YamlConfig;

/**
 * Created by jhilanalkarawi on 3/28/17.
 */
public class DatFormatGenrator implements IDataGenerator {
    private final List<FieldCollector> collectors;

    public DatFormatGenrator(final YamlConfig config) {
        collectors = registerCollectors(config.getConfiguration(), config.getFields());
    }

    @Override
    public void generateDataset()  {
        throw new UnsupportedOperationException("DAT format is not implemented.");
    }
}
