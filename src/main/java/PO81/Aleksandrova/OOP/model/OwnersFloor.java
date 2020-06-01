package PO81.Aleksandrova.OOP.model;

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



    public boolean addSpace(Space space) {
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


    public void expandArray() {
        Space[] newArray = new Space[this.spaces.length * 2];
        System.arraycopy(this.spaces, 0, newArray, 0, this.size);
        this.spaces = newArray;
    }


    public boolean addSpace(int index, Space space) {
        if (this.size == this.spaces.length) {
            expandArray();
        }
        shift(index, false);
        this.spaces[index] = space;
        this.size++;


        return true;
    }

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

    public boolean checkRegistrationNumber(Space rentedSpace, String registrationNumber) {
        return rentedSpace.getVehicle().getRegistrationNumber().equals(registrationNumber);
    }

    public boolean hasSpace(String registrationNumber) {
        for (Space rentedSpace : spaces) {
            if (checkRegistrationNumber(rentedSpace, registrationNumber)) {
                return true;
            }
        }
        return false;
    }

    public Space replaceWith(int index, Space space) {
        return null;
    }

    public Space replaceWith(Space space, int index) {
        Space replacedRentedSpace = this.spaces[index];
        this.spaces[index] = space;
        return replacedRentedSpace;
    }

    public Space remove(int index) {
        Space removedRentedSpace = this.spaces[index];
        shift(index, false);
        this.size--;
        return removedRentedSpace;
    }

    public Space remove(String registrationNumber) {
        for (int i = 0; i < getSpaces().length; i++) {
            if (checkRegistrationNumber(spaces[i], registrationNumber)) {
                return remove(i);
            }
        }
        return new RentedSpace();
    }

    public int size() {
        return size;
    }

    public Space[] getSpaces() {
        Space[] notNullSpaces = new Space[size];
        System.arraycopy(spaces, 0, notNullSpaces, 0, size);
        return notNullSpaces;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] notNullVehicles = new Vehicle[size];
        for (int i = 0; i < size; i++) {
            notNullVehicles[i] = spaces[i].getVehicle();
        }
        return notNullVehicles;
    }

    public boolean checkVehiclesType(Space space, VehicleTypes type) {
        return space.getVehicle().getType().equals(type);
    }

    public Space[] getSpacesByVehiclesType(VehicleTypes type) {
        return Arrays.stream(getSpaces())
                .filter(space -> checkVehiclesType(space, type))
                .toArray(Space[]::new);
    }

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
}
