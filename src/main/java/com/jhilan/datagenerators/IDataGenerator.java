package com.jhilan.datagenerators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jhilan.collectors.DateRangeFieldCollector;
import com.jhilan.collectors.FieldCollector;
import com.jhilan.collectors.KeyFieldCollector;
import com.jhilan.collectors.MultiValueFieldCollector;
import com.jhilan.collectors.NumberRangeFieldCollector;
import com.jhilan.collectors.SingleValueFieldCollector;
import com.jhilan.collectors.TextFieldCollector;
import com.jhilan.config.DataSetConfig;
import com.jhilan.config.MetaDataFields;

/**
 * Created by jhilanalkarawi on 3/28/17.
 */
public interface IDataGenerator {

    void generateDataset() throws IOException;

    default List<FieldCollector> registerCollectors(final DataSetConfig config, final MetaDataFields metaDataFields) {
        final List<FieldCollector> collectors = new ArrayList<>(8);

        if (metaDataFields.getKeyField() != null) {
            collectors.add(new KeyFieldCollector(metaDataFields.getKeyField()));
        }

        if (metaDataFields.getTextField() != null) {
            final BodyTextIterator iterator = new BodyTextIterator(metaDataFields.getTextField(), config);
            collectors.add(new TextFieldCollector(metaDataFields.getTextField(), iterator));
        }

        if (metaDataFields.getSingleValueFields() != null && !metaDataFields.getSingleValueFields().isEmpty()) {
            collectors.add(new SingleValueFieldCollector(metaDataFields.getSingleValueFields()));
        }

        if (metaDataFields.getMultiValueFields() != null && !metaDataFields.getSingleValueFields().isEmpty()) {
            collectors.add(new MultiValueFieldCollector(metaDataFields.getMultiValueFields()));
        }

        if (metaDataFields.getNumberRangeFields() != null && !metaDataFields.getSingleValueFields().isEmpty()) {
            collectors.add(new NumberRangeFieldCollector(metaDataFields.getNumberRangeFields()));
        }

        if (metaDataFields.getDateRangeFields() != null && !metaDataFields.getSingleValueFields().isEmpty()) {
            collectors.add(new DateRangeFieldCollector(metaDataFields.getDateRangeFields()));
        }

        return collectors;
    }

    default Map<String, Object> buildDocumentAsMap(final List<FieldCollector> collectors) {
        final Map<String, Object> document = new HashMap<>(8);

        for (final FieldCollector collector : collectors) {
            document.putAll(collector.collect());
        }
        return document;
    }
}
