package PO81.Aleksandrova.OOP.model;

public interface Space {
    Vehicle getVehicle();

    void setVehicle(Vehicle vehicle);

    Person getPerson();

    void setPerson(Person person);

    boolean isEmpty();

    String toString();

    int hashCode();

    boolean equals(Object obj);

    public Object clone() throws CloneNotSupportedException;
}

