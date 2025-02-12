package br.com.postech.techchallenge.orderapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    CREATED("Created"),
    RECEIVED("Received"),
    PREPARATION("Preparation"),
    DONE("Done"),
    FINISHED("Finished");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static ProductCategory fromDisplayName(String displayName) {
        for (ProductCategory category : ProductCategory.values()) {
            if (category.getDisplayName().equals(displayName) || category.name().equalsIgnoreCase(displayName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Status: " + displayName + " não encontrada!");
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}
