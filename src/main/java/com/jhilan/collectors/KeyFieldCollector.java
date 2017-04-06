package com.jhilan.collectors;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.jhilan.config.KeyField;

import static com.jhilan.datagenerators.JsonFormatGenerator.isSet;

public class KeyFieldCollector implements FieldCollector {
    private final boolean isUuid;
    private final String keyFieldName;
    private final AtomicInteger counter;
    private static final String DEFAULT_FIELD_NAME = "identifier";

    public KeyFieldCollector(final KeyField keyField) {
        isUuid = keyField.isUuid();
        keyFieldName = isSet(keyField.getName()) ? keyField.getName() : DEFAULT_FIELD_NAME;
        counter = new AtomicInteger(keyField.getStartingNumber());
    }

    @Override
    public Map<String, Object> collect() {
        if (isUuid) {
            return Collections.singletonMap(keyFieldName, UUID.randomUUID());
        } else {
            return Collections.singletonMap(keyFieldName, counter.getAndIncrement());
        }
    }
}
