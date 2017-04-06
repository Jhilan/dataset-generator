package com.jhilan.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BodyTextField {
    private final String name;
    private final boolean randomText;

    @JsonCreator
    public BodyTextField(@JsonProperty("name") final String name, @JsonProperty("randomText") final boolean randomText) {
        this.name = name;
        this.randomText = randomText;
    }

    public String getName() {
        return name;
    }

    public boolean isRandomText() {
        return randomText;
    }
}
