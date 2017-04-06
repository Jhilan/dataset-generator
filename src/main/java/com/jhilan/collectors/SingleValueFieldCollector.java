package com.jhilan.collectors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class SingleValueFieldCollector implements FieldCollector{
    private final Map<String, List<Object>> fields;

    public SingleValueFieldCollector(final Map<String, List<Object>> fields) {
        this.fields = fields;
    }

    @Override
    public Map<String, Object> collect() {
        final Map<String, Object> collectedFields = new HashMap<>(fields.size());
        for (final Map.Entry<String, List<Object>> field :  fields.entrySet()) {
            final int randomNumber = ThreadLocalRandom.current().nextInt(field.getValue().size() - 1);
            collectedFields.put(field.getKey(), field.getValue().get(randomNumber));
        }
        return collectedFields;
    }
}
