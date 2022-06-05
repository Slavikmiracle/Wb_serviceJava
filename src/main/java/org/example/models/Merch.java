package org.example.models;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Merch {
    @NotEmpty(message = "Id should not be empty")
    private int id;

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Value should not be empty")
    private int value;

    @NotEmpty(message = "Type should not be empty")
    private String types;

    @NotEmpty(message = "Institute should not be empty")
    private String Institute;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public void setInstitute(String institute) {
        Institute = institute;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public String getTypes() {
        return types;
    }

    public String getInstitute() {
        return Institute;
    }
}
