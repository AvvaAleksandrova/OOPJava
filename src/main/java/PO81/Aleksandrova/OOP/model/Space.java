package PO81.Aleksandrova.OOP.model;

public class Space {
    private Person person;
    private Vehicle vehicle;

    public Space(Person person, Vehicle vehicle) {
        this.person = person;
        this.vehicle = vehicle;
    }

    public Space(){
        this(Person.UNDEFINED, new Vehicle());
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
}
