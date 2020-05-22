package PO81.Aleksandrova.OOP.model;

public interface Floor {
    boolean addSpace(Space space);

    boolean addSpace(int number, Space space);

    Space getSpace(int number);

    Space getSpace(String stateNumber);

    boolean isVehicle(String stateNumber);

    Space replaceSpace(int number, Space newSpace);

    Space deleteSpace(int number);

    Space deleteSpace(String stateNumber);

    int getCountSpace();

    Space[] getSpaces();

    Vehicle[] getVehicles();
}
