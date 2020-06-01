package PO81.Aleksandrova.OOP.model;

public abstract class AbstractSpace implements Space  {
    private Vehicle vehicle;
    private Person person;


   /* protected AbstractSpace() {
        this(Vehicle.getNoVehicle());
    }*/


    protected AbstractSpace(Person person) {
        this(Vehicle.getNoVehicle(), person);
    }


    protected AbstractSpace(Vehicle vehicle, Person person) {
        this.vehicle = vehicle;
        this.person = person;
    }



    public Vehicle getVehicle() {
        return vehicle;
    }



    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }



    public Person getPerson() {
        return person;
    }



    public void setPerson(Person person) {
        this.person = person;
    }



    public boolean isEmpty() {
        return vehicle.equals(Vehicle.getNoVehicle()) || vehicle.getType().equals(VehicleTypes.NONE);
    }



    public String toString() {
        StringBuilder builder = new StringBuilder("# Space #\n");
        builder.append(vehicle.toString()).append(person.toString());
        return builder.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return null;
    }
}


