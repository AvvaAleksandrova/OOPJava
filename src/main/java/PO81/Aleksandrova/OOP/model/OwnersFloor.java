package PO81.Aleksandrova.OOP.model;
import java.util.Arrays;
import java.util.Objects;

public class OwnersFloor implements Floor, IInstanceHandler {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int INITIAL_SIZE = 0;
    private final static Space DEFAULT_SPACE = new RentedSpace();
    private Space[] spaces;
    private int size;


    public OwnersFloor() {
        this.spaces = new Space[DEFAULT_CAPACITY];
        this.size = INITIAL_SIZE;
    }

    public OwnersFloor(int size) {
        this.spaces = new Space[size];
        this.size = INITIAL_SIZE;
    }

    public OwnersFloor(Space[] spaces) {
        this.spaces = new Space[spaces.length];
        System.arraycopy(spaces, 0, this.spaces, 0, spaces.length);
        this.size = countNotNull(this.spaces);
    }

    private int countNotNull(Space[] spaces) {
        int count = 0;
        for (Space space : spaces) {
            if (space != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean add(Space space) {
        if (this.size == this.spaces.length) {
            expandArray();
        }
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i] == null) {
                spaces[i] = space;
                size++;
                return true;
            }
        }
        return false;
    }

    @Override
    public void expandArray() {
        Space[] newArray = new Space[this.spaces.length * 2];
        System.arraycopy(this.spaces, 0, newArray, 0, this.size);
        this.spaces = newArray;
    }

/*
    public boolean addSpace(int index, Space space) {
        if (this.size == this.spaces.length) {
            expandArray();
        }
        shift(index, false);
        this.spaces[index] = space;
        this.size++;


        return true;
    }

    public void expand() {
    }

    public Space getSpace(int index) {
        return this.spaces[index];
    }

    public Space getSpace(String registrationNumber) {
        for (Space rentedSpace : spaces) {
            if (checkRegistrationNumber(rentedSpace, registrationNumber)) {
                return rentedSpace;
            }
        }
        return new RentedSpace();
    }
*/

    @Override
    public boolean add(int index, Space space) {
        if (this.size == this.spaces.length) {
            expandArray();
        }
        shift(index, false);
        this.spaces[index] = space;
        this.size++;


        return true;
    }

    @Override
    public void shift(int index, boolean isLeft) {
        expandArray();
        if (spaces.length >= index) {
            if (isLeft) {
                System.arraycopy(spaces, index + 1, spaces, index, spaces.length - index - 1);
                spaces[spaces.length - 1] = null;
            } else {
                System.arraycopy(spaces, index, spaces, index + 1, spaces.length - index - 1);
                spaces[index] = null;
            }
        }
    }

    @Override
    public void expand() {
    }

    @Override
    public Space get(int index) {
        return this.spaces[index];
    }

    @Override
    public Space getS(String registrationNumber) {
        return null;
    }

    public Space get(String registrationNumber) {
        for (Space space : spaces) {
            if (checkRegistrationNumber(space, registrationNumber)) {
                return space;
            }
        }
        return new DEFAULT_SPACE;
    }

    @Override
    public boolean checkRegistrationNumber(Space space, String registrationNumber) {
        return space.getVehicle().getRegistrationNumber().equals(registrationNumber);
    }

    public boolean hasSpace(String registrationNumber) {
        return Arrays.stream(spaces)
                .anyMatch(space -> checkRegistrationNumber(space, registrationNumber));
    }

    @Override
    public boolean hasSpace(Person person) {
        return Arrays.stream(spaces).anyMatch(space -> space.getPerson().equals(person));
    }

    @Override
    public Space replaceWith(int index, Space space) {
        return null;
    }

    @Override
    public Space replaceWith(Space space, int index) {
        Space replacedSpace = this.spaces[index];
        this.spaces[index] = space;
        return replacedSpace;
    }

    @Override
    public Space remove(int index) {
        Space removedSpace = this.spaces[index];
        shift(index, false);
        this.size--;
        return removedSpace;
    }

    public Space remove(String registrationNumber) {
        for (int i = 0; i < getSpaces().length; i++) {
            if (checkRegistrationNumber(spaces[i], registrationNumber)) {
                return remove(i);
            }
        }
        return new DEFAULT_SPACE();
    }

    @Override
    public boolean remove(Space space) {
        remove(indexOf(space));
        return true;
    }

    @Override
    public int indexOf(Space space) {
        for(int i = 0; i < size; i++) {
            if(get(i).equals(space)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int getSpacesCountWithPerson(Person person) {
        return (int) Arrays.stream(getSpaces())
                .filter(space -> space.getPerson().equals(person))
                .count();
    }

    public int size() {
        return size;
    }

    @Override
    public Space[] getSpaces() {
        Space[] notNullSpaces = new Space[size];
        System.arraycopy(spaces, 0, notNullSpaces, 0, size);
        return notNullSpaces;
    }

    @Override
    public Vehicle[] getVehicles() {
        return Arrays.stream(getSpaces())
                .map(Space::getVehicle)
                .filter(Objects::nonNull)
                .toArray(Vehicle[]::new);
    }

    @Override
    public int getVehiclesCount() {
        return getVehicles().length;
    }

    public boolean checkVehiclesType(Space space, VehicleTypes type) {
        return space.getVehicle().getType().equals(type);
    }

    public Space[] getSpacesByVehiclesType(VehicleTypes type) {
        return Arrays.stream(getSpaces())
                .filter(space -> checkVehiclesType(space, type))
                .toArray(Space[]::new);
    }

    @Override
    public Space[] getFreeSpaces() {
        return getSpacesByVehiclesType(VehicleTypes.NONE);
    }

    public int getSpacesCountByVehiclesType(VehicleTypes type) {
        return getSpacesByVehiclesType(type).length;
    }

    public void printSpaces() {
        for (Space space : getSpaces()) {
            System.out.println(space.toString());
        }
    }

    public void printVehicles() {
        for (Vehicle vehicle : getVehicles()) {
            System.out.println(vehicle.toString());
        }
    }

    public void printSpacesByVehiclesType(VehicleTypes type) {
        for (Space space : getSpacesByVehiclesType(type)) {
            System.out.println(space.toString());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Spaces:\n");
        for(Space space : getSpaces()) {
            builder.append(space.toString()).append("\n");
        }
        return builder.toString();
    }

    @Override
    public int hashCode() {
        int code = 71 * size;
        for (Space space : getSpaces()) {
            code ^= space.hashCode();
        }
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof OwnersFloor)) {
            return false;
        }
        OwnersFloor other = (OwnersFloor) obj;
        return size == other.size && Objects.deepEquals(spaces, other.spaces);
    }

}
