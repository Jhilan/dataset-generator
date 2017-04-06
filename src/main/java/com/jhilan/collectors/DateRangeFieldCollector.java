package com.jhilan.collectors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class DateRangeFieldCollector implements FieldCollector {
    private final Map<String, List<LocalDate>> fields;

    public DateRangeFieldCollector(final Map<String, List<LocalDate>> fields) {
        this.fields = fields;
    }

    @Override
    public Map<String, Object> collect() {
        final Map<String, Object> collectedFields = new HashMap<>(fields.size());
        for (final Map.Entry<String, List<LocalDate>> field : fields.entrySet()) {
            final List<LocalDate> values = field.getValue();
            //the DateRangeFieldDeserializer check that list is of two values, so this should be safe from npe.
            final long minDate = values.get(0).toEpochDay();
            final long maxDate = values.get(1).toEpochDay();

            final LocalDate randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(minDate, maxDate));
            collectedFields.put(field.getKey(), LocalDateTime.of(randomDate, LocalTime.now()).toString());
        }
        return collectedFields;
    }
}
