package PO81.Aleksandrova.OOP.model;

import java.time.LocalDate;
import java.util.NoSuchElementException;

public interface Floor {
    boolean add(Space space);

    void expandArray();

    boolean add(int index, Space space) throws IndexOutOfBoundsException;;

    Space get(int index) throws IndexOutOfBoundsException;;

    Space get(String registrationNumber)throws NoRentedSpaceException;;

    boolean hasSpace(String registrationNumber);

    boolean hasSpace(Person person);

    Space replaceWith(int index, Space space) throws IndexOutOfBoundsException;

    Space remove(int index) throws IndexOutOfBoundsException;

    boolean remove(Space space) ;

    int indexOf(Space space);

    int getSpacesCountWithPerson(Person person);

    Space remove(String registrationNumber) throws NoSuchElementException;

    int size();

    Space[] getSpaces();

    Vehicle[] getVehicles();

    int getVehiclesCount();

    boolean isRegistrationNumberEqual(Space space, String registrationNumber);

    boolean isVehiclesTypeEqual(Space space, VehicleTypes types);

    Space[] getSpacesByVehiclesType(VehicleTypes type);

    Space[] getFreeSpaces();

    int getSpacesCountByVehiclesType(VehicleTypes type);

    LocalDate getNearestEndsDate() throws NoRentedSpaceException;

    Space getSpaceWithNearestEndsDate() throws NoRentedSpaceException;

    String toString();

    int hashCode();

    boolean equals(Object obj);

    public Object clone() throws CloneNotSupportedException;

}
