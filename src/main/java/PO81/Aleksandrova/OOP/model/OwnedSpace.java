package PO81.Aleksandrova.OOP.model;

public class OwnedSpace implements Space {
    private Person person;
    private Vehicle vehicle;

    public OwnedSpace(Person person, Vehicle vehicle) {
        this.person = person;
        this.vehicle = vehicle;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isEmpty (){
        return vehicle == null || vehicle.getStateNumber().trim().equals("");
    }
}
