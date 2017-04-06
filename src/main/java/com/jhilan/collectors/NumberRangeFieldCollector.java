package com.jhilan.collectors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.jhilan.exception.ConfigValidationException;

public class NumberRangeFieldCollector implements FieldCollector {
    private final Map<String, List<Object>> fields;

    public NumberRangeFieldCollector(final Map<String, List<Object>> fields) {
        this.fields = validateAndGetFields(fields);
    }

    @Override
    public Map<String, Object> collect() {
        final Map<String, Object> collectedFields = new HashMap<>(fields.size());
        for (final Map.Entry<String, List<Object>> field : fields.entrySet()) {
            final List<Object> values = field.getValue();
            final int min = (int) values.get(0);
            final int max = (int) values.get(1);

            final int randRange = ThreadLocalRandom.current().nextInt(max - min) + min;
            collectedFields.put(field.getKey(), randRange);
        }
        return collectedFields;
    }

    //---- private ----
    private static Map<String, List<Object>> validateAndGetFields(final Map<String, List<Object>> fields) {
        for (final Map.Entry<String, List<Object>> field : fields.entrySet()) {
            if (field.getValue() == null || field.getValue().size() != 2) {
                throw new ConfigValidationException(
                        "field: " + field.getKey() + " defined as range fields and should have only " + "2 entries.");
            }
        }
        return fields;
    }
}
