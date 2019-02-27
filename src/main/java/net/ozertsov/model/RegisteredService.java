package net.ozertsov.model;

public class RegisteredService {

    String description;
    String unit;


    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "RegisteredService{" +
                "description='" + description + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}