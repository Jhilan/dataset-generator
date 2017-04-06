package com.jhilan.collectors;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jhilanalkarawi on 3/30/17.
 */
public class MultiValueFieldCollector implements FieldCollector {
    private final Map<String, List<Object>> fields;

    public MultiValueFieldCollector(final Map<String, List<Object>> fields) {
        this.fields = fields;
    }

    @Override
    public Map<String, Object> collect() {
        final Map<String, Object> collectedFields = new HashMap<>(fields.size());
        for (final Map.Entry<String, List<Object>> field : fields.entrySet()) {
            final Object randomSelectionsList = generateRandomSelections(field);
            collectedFields.put(field.getKey(), randomSelectionsList);
        }
        return collectedFields;
    }

    //---- private ----
    private static Object generateRandomSelections(Map.Entry<String, List<Object>> field) {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        final int maxSelection = field.getValue().size() - 1;

        int totalValuesToSelect = random.nextInt(maxSelection);
        if (totalValuesToSelect == 0) {
            totalValuesToSelect++;
        }
        final Collection<Object> selections = new HashSet<>(totalValuesToSelect);

        while (selections.size() != totalValuesToSelect) {
            final int randNumber = random.nextInt(maxSelection);
            selections.add(field.getValue().get(randNumber));
        }
        return selections;
    }
}
