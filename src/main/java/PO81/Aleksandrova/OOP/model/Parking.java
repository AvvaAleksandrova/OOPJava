package PO81.Aleksandrova.OOP.model;

public class Parking {
    private final static int INITIAL_SIZE = 0;
    private Floor[] floors;
    private int size;


    public Parking(int size) {
        this.floors = new Floor[size];
        this.size = INITIAL_SIZE;
    }


    public Parking(Floor... floors) {
        this.floors = floors;
        this.size = floors.length;
    }


    public int size() {
        return this.size;
    }


    public Floor[] getFloors() {
        Floor[] notNullFloors = new OwnersFloor[size];
        System.arraycopy(floors, 0, notNullFloors, 0, size);
        return notNullFloors;
    }


    public Floor[] getSortedFloors() { //bubble sort
        Floor floor;
        Floor[] sortedFloors = floors.clone();
        for (int i = 0; i < sortedFloors.length; i++) {
            for (int j = i + 1; j < i; j++) {
                if (sortedFloors[i].size() > sortedFloors[j].size()) {
                    floor = sortedFloors[i];
                    sortedFloors[i] = sortedFloors[j];
                    sortedFloors[j] = floor;
                }
            }
        }
        return sortedFloors;
    }


    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[getSpacesCount()];
        int counter = 0;
        for (Floor floor : floors) {
            for (Space space : floor.getSpaces()) {
                vehicles[counter] = space.getVehicle();
                counter++;
            }
        }
        return vehicles;
    }


    public int getSpacesCount() {
        int totalCount = 0;
        for (Floor floor : floors) {
            totalCount += floor.size();
        }
        return totalCount;
    }


    public boolean add(Floor floor) {
        expand();
        for (int i = 0; i < floors.length; i++) {
            if (floors[i] == null) {
                floors[i] = floor;
                this.size++;
                return true;
            }
        }
        return false;
    }


    public boolean add(int index, Floor floor) {
        shift(index, false);
        return add(floor);
    }


    public Floor getFloor(int index) {
        return floors[index];
    }


    public Floor replaceFloor(int index, OwnersFloor floor) {
        Floor replacedFloor = floors[index];
        this.floors[index] = floor;
        return replacedFloor;
    }


    public Floor removeFloor(int index) {
        Floor removedFloor = floors[index];
        shift(index, true);
        this.size--;
        return removedFloor;
    }


    public Space removeSpace(String registrationNumber) {
        for (int i = 0; i < getFloors().length; i++) {
            for (int j = 0; j < getFloors()[i].getSpaces().length; j++) {
                if (floors[i].checkRegistrationNumber(floors[i].getSpace(j), registrationNumber)) {
                    return floors[i].remove(j);
                }
            }
        }
        return new RentedSpace();
    }


    public Space getSpace(String registrationNumber) {
        for (Floor floor : floors) {
            for (Space space : floor.getSpaces()) {
                if (floor.checkRegistrationNumber(space, registrationNumber)) {
                    return space;
                }
            }
        }
        return new RentedSpace();
    }


    public Space replaceSpace(Space space, String registrationNumber) {
        Space replacedSpace = new RentedSpace();
        for (int i = 0; i < getFloors().length; i++) {
            for (int j = 0; j < getFloors()[i].getSpaces().length; j++) {
                if (floors[i].checkRegistrationNumber(floors[i].getSpace(j), registrationNumber)) {
                    replacedSpace = floors[i].getSpace(j);
                    this.floors[i].getSpaces()[j] = space;
                }
            }
        }
        return replacedSpace;
    }


    public int getFreeSpacesCount() {
        return getSpacesCountByVehiclesType(VehicleTypes.NONE);
    }


    public int getSpacesCountByVehiclesType(VehicleTypes type) {
        int count = 0;
        for (Floor floor : getFloors()) {
            count += floor.getSpacesCountByVehiclesType(type);
        }
        return count;
    }


    public void shift(int index, boolean isLeft) {
        expand();
        if (floors.length >= index) {
            if (isLeft) {
                System.arraycopy(floors, index + 1, floors, index, floors.length - index - 1);
                this.floors[floors.length - 1] = null;
            } else {
                System.arraycopy(floors, index, floors, index + 1, floors.length - index - 1);
                this.floors[index] = null;
            }
        }
    }


    public void expand() {
        if (floors[floors.length - 1] != null) {
            Floor[] updatedFloors = new Floor[size * 2];
            System.arraycopy(floors, 0, updatedFloors, 0, floors.length);
            this.floors = updatedFloors;
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\n= Parking =");
        for (Floor floor : getFloors()) {
            builder.append(floor.toString());
        }
        return builder.toString();
    }


}

