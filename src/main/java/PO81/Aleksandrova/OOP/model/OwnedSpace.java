package PO81.Aleksandrova.OOP.model;

public class OwnedSpace extends AbstractSpace  {


    public OwnedSpace() {
        super();
    }


    public OwnedSpace(Person person) {
        super(person);
    }


    public OwnedSpace(Vehicle vehicle, Person person) {
        super(vehicle, person);
    }
}

