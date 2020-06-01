package PO81.Aleksandrova.OOP.model;

public class RentedSpace extends AbstractSpace {
    public RentedSpace() {
        super();
    }


    public RentedSpace(Person person) {
        super(person);
    }


    public RentedSpace(Vehicle vehicle, Person person) {
        super(vehicle, person);
    }
}



