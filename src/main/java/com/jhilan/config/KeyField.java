package com.jhilan.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KeyField {
    private final String name;
    private final int startingNumber;
    private final boolean uuid;

    @JsonCreator
    public KeyField(@JsonProperty("name") final String name, @JsonProperty("startingNumber") final int startingNumber,
                    @JsonProperty("uuid") final boolean uuid) {
        this.name = name;
        this.startingNumber = startingNumber;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public int getStartingNumber() {
        return startingNumber;
    }

    public boolean isUuid() {
        return uuid;
    }
}
