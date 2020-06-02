package PO81.Aleksandrova.OOP.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Vehicle implements Cloneable {
    private final static String DEFAULT_DATA = "[NO_DATA]";
    private final static String DEFAULT_NUMBER = "A000AA00";
    private final static Vehicle NO_VEHICLE = new Vehicle(VehicleTypes.NONE);
    private String registrationNumber;
    private String manufacturer;
    private String model;
    private VehicleTypes type;

    public Vehicle(String registrationNumber, String manufacturer, String model, VehicleTypes type) {
        this.registrationNumber = checkNumber(registrationNumber);
        this.manufacturer = Objects.requireNonNull(manufacturer, "Значение manufacturer не должно быть null");
        this.model = Objects.requireNonNull(model, "Значение model не должно быть null");
        this.type = Objects.requireNonNull(type, "Значение type не должно быть null");
    }

    public Vehicle(VehicleTypes type) {
        this(DEFAULT_NUMBER, DEFAULT_DATA, DEFAULT_DATA, type);
    }

    public Vehicle() {
        this(VehicleTypes.NONE);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleTypes getType() {
        return type;
    }

    public void setType(VehicleTypes type) {
        this.type = Objects.requireNonNull(type, "Значение type не должно быть null");
    }

    public static Vehicle getNoVehicle() {
        return NO_VEHICLE;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = checkNumber(registrationNumber);
    }

    static String checkNumber(String registrationNumber) {
        return registrationNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = Objects.requireNonNull(manufacturer,
                "Значение manufacturer не должно быть null");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = Objects.requireNonNull(model, "Значение model не должно быть null");
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, manufacturer, model, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) obj;
        return Objects.equals(registrationNumber, other.registrationNumber) &&
                Objects.equals(manufacturer, other.manufacturer) &&
                Objects.equals(model, other.model) &&
                Objects.equals(type, other.type);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
