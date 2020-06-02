package PO81.Aleksandrova.OOP.model;
import java.util.Arrays;
import java.util.Objects;
import java.time.LocalDate;
import java.util.NoSuchElementException;

public class OwnersFloor implements Floor, IInstanceHandler {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int INITIAL_SIZE = 0;
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
                spaces[i] = Objects.requireNonNull(space, "Параметр space не должен быть null");
                size = size();
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
        if (spaces.length >= index && index >= 0) {
            if (isLeft) {
                System.arraycopy(spaces, index + 1, spaces, index, spaces.length - index - 1);
                spaces[spaces.length - 1] = null;
            } else {
                System.arraycopy(spaces, index, spaces, index + 1, spaces.length - index - 1);
                spaces[index] = null;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void expand() {

    }

    @Override
    public Space replaceWith(Space space, int index) {
        return null;
    }

    @Override
    public Space get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return this.spaces[index];
    }

    @Override
    public Space get(String registrationNumber) throws NoRentedSpaceException {
        Objects.requireNonNull(registrationNumber, "Параметр registrationNumber не должен быть null");
        for (Space space : spaces) {
            if (isRegistrationNumberEqual(space, registrationNumber)) {
                return space;
            }
        }
        throw new NoRentedSpaceException();
    }

    @Override
    public boolean isRegistrationNumberEqual(Space space, String registrationNumber) {
        return space.getVehicle().getRegistrationNumber().equals(registrationNumber);
    }

    public boolean hasSpace(String registrationNumber) {
        return Arrays.stream(spaces)
                .anyMatch(space -> isRegistrationNumberEqual(space, registrationNumber));
    }

    @Override
    public boolean hasSpace(Person person) {
        return Arrays.stream(spaces).anyMatch(space -> space.getPerson().equals(person));
    }

    @Override
    public Space replaceWith(int index, Space space) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Space replacedSpace = spaces[index];
        spaces[index] = Objects.requireNonNull(space, "Параметр space не должен быть null");
        return replacedSpace;
    }

    @Override
    public Space remove(int index) {
        Space removedSpace = this.spaces[index];
        shift(index, false);
        size = size();
        return removedSpace;
    }

    @Override
    public Space remove(String registrationNumber) {
        for (int i = 0; i < getSpaces().length; i++) {
            if (isRegistrationNumberEqual(spaces[i], Vehicle.checkNumber(registrationNumber)))  {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean remove(Space space) {
        remove(indexOf(space));
        return true;
    }

    @Override
    public int indexOf(Space space) {
        Objects.requireNonNull(space, "Параметр space не должен быть null");
        for (int i = 0; i < size; i++) {
            if (get(i).equals(space)) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int getSpacesCountWithPerson(Person person) {
        Objects.requireNonNull(person, "Параметр person не должен быть null");
        return (int) Arrays.stream(getSpaces())
                .filter(space -> space.getPerson().equals(person))
                .count();
    }

    @Override
    public int size() {
        return getSpaces().length;
    }

    @Override
    public Space[] getSpaces() {
        return Arrays.stream(spaces).filter(Objects::nonNull).toArray(Space[]::new);
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

    @Override
    public boolean isVehiclesTypeEqual(Space space, VehicleTypes type) {
        return space.getVehicle().getType().equals(type);
    }

    @Override
    public Space[] getSpacesByVehiclesType(VehicleTypes type) {
        return Arrays.stream(getSpaces())
                .filter(space -> isVehiclesTypeEqual(space, Objects.requireNonNull(type, "Параметр type не должен быть null")))
                .toArray(Space[]::new);
    }

    @Override
    public Space[] getFreeSpaces() {
        return getSpacesByVehiclesType(VehicleTypes.NONE);
    }

    public int getSpacesCountByVehiclesType(VehicleTypes type) {
        return getSpacesByVehiclesType(Objects.requireNonNull(type, "Параметр type не должен быть null")).length;
    }

    @Override
    public LocalDate getNearestEndsDate() throws NoRentedSpaceException {
        throw new NoRentedSpaceException();
    }

    @Override
    public Space getSpaceWithNearestEndsDate() throws NoRentedSpaceException {
        throw new NoRentedSpaceException();
    }

    public void printSpaces() {
        System.out.println(toString());
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
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
