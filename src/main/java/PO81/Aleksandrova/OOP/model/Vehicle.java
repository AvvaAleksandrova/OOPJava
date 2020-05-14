package PO81.Aleksandrova.OOP.model;

public class Vehicle {
    private String stateNumber;
    private String manufacturer;
    private String model;

    public Vehicle(String stateNumber, String manufacturer, String model) {
        this.stateNumber = stateNumber;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public Vehicle() {
        this("","","");
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
