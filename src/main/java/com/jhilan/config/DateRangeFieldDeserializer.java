package com.jhilan.config;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhilan.exception.ConfigValidationException;

public class DateRangeFieldDeserializer extends JsonDeserializer<Map<String, List<LocalDate>>> {
    @Override
    public Map<String, List<LocalDate>> deserialize(final JsonParser jsonParser, final DeserializationContext ctxt)
            throws IOException {
        final ObjectCodec objectCodec = jsonParser.getCodec();
        final JsonNode json = objectCodec.readTree(jsonParser);

        final Map<String, List<Object>> deserializedMap =
                new ObjectMapper().readValue(json.toString(), new TypeReference<Map<String, List<Object>>>() {});

        final Map<String, List<LocalDate>> dateRangeFields = new HashMap<>(deserializedMap.size());
        for (final Map.Entry<String, List<Object>> field : deserializedMap.entrySet()) {
            if (field.getValue().size() != 2) {
                throw new ConfigValidationException(
                        "field: " + field.getKey() + " is a date range and should have" + "only 2 values");
            }
            final List<LocalDate> dateRange = new ArrayList<>(2);
            dateRange.add(LocalDate.parse((CharSequence) field.getValue().get(0)));
            dateRange.add(LocalDate.parse((CharSequence) field.getValue().get(1)));
            dateRangeFields.put(field.getKey(), dateRange);
        }
        return dateRangeFields;
    }
}
