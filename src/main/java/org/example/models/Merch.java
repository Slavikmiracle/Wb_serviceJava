package org.example.models;

public class Merch {
    private int id;
    private String name;
    private int value;
    private String type;
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

    public void setType(String type) {
        this.type = type;
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

    public String getType() {
        return type;
    }

    public String getInstitute() {
        return Institute;
    }
}
