package com.jhilan.config;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class MetaDataFields {
    private final KeyField keyField;
    private final BodyTextField textField;

    private final Map<String, List<Object>> singleValueFields;
    private final Map<String, List<Object>> multiValueFields;

    private final Map<String, List<Object>> numberRangeFields;

    @JsonDeserialize(using = DateRangeFieldDeserializer.class)
    private final Map<String, List<LocalDate>> dateRangeFields;

    @JsonCreator
    public MetaDataFields(@JsonProperty("keyField") final KeyField keyField,
                          @JsonProperty("textField") final BodyTextField textField,
                          @JsonProperty("singleValueFields") final Map<String, List<Object>> singleValueFields,
                          @JsonProperty("multiValueFields") final Map<String, List<Object>> multiValueFields,
                          @JsonProperty("numberRangeFields") final Map<String, List<Object>> numberRangeFields,
                          @JsonProperty("dateRangeFields") final Map<String, List<LocalDate>> dateRangeFields) {
        this.keyField = keyField;
        this.textField = textField;
        this.singleValueFields = singleValueFields;
        this.multiValueFields = multiValueFields;
        this.numberRangeFields = numberRangeFields;
        this.dateRangeFields = dateRangeFields;
    }

    public KeyField getKeyField() {
        return keyField;
    }

    public BodyTextField getTextField() {
        return textField;
    }

    public Map<String, List<Object>> getSingleValueFields() {
        return singleValueFields;
    }

    public Map<String, List<Object>> getMultiValueFields() {
        return multiValueFields;
    }

    public Map<String, List<Object>> getNumberRangeFields() {
        return numberRangeFields;
    }

    public Map<String, List<LocalDate>> getDateRangeFields() {
        return dateRangeFields;
    }
}
