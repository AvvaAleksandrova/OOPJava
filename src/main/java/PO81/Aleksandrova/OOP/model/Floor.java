package PO81.Aleksandrova.OOP.model;

public interface Floor {
    boolean add(Space space);

    void expandArray();

    boolean add(int index, Space space);

    Space get(int index);

    Space getS(String registrationNumber);

    boolean hasSpace(String registrationNumber);

    boolean hasSpace(Person person);

    Space replaceWith(int index, Space space);

    Space remove(int index);

    boolean remove(Space space);

    int indexOf(Space space);

    int getSpacesCountWithPerson(Person person);

    Space remove(String registrationNumber);

    int size();

    Space[] getSpaces();

    Vehicle[] getVehicles();

    int getVehiclesCount();

    boolean checkRegistrationNumber(Space space, String registrationNumber);

    boolean checkVehiclesType(Space space, VehicleTypes types);

    Space[] getSpacesByVehiclesType(VehicleTypes type);

    Space[] getFreeSpaces();

    int getSpacesCountByVehiclesType(VehicleTypes type);

    String toString();

    int hashCode();

    boolean equals(Object obj);

}
