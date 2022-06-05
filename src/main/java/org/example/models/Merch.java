package org.example.models;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Merch {
    private int id;
    private String name;
    private int value;
    private String types;
    private String Institute;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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