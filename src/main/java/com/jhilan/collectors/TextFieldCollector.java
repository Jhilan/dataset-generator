package com.jhilan.collectors;

import java.util.Collections;
import java.util.Map;

import com.jhilan.datagenerators.BodyTextIterator;
import com.jhilan.config.BodyTextField;

import static com.jhilan.datagenerators.JsonFormatGenerator.isSet;

public class TextFieldCollector implements FieldCollector {
    private final String textFieldName;
    private final BodyTextIterator bodyTextIterator;
    private static final String DEFAULT_FIELD_NAME = "bodyText";

    public TextFieldCollector(final BodyTextField bodyTextField, final BodyTextIterator bodyTextIterator) {
        this.bodyTextIterator = bodyTextIterator;
        textFieldName = isSet(bodyTextField.getName()) ? bodyTextField.getName() : DEFAULT_FIELD_NAME;
    }

    @Override
    public Map<String, Object> collect() {
        return Collections.singletonMap(textFieldName, bodyTextIterator.next());
    }
}
