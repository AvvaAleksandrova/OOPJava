package PO81.Aleksandrova.OOP.model;

public class Parking {
    private Floor[] floors;
    int countFloors = 0;

    public Parking(int floorsLength) {
        this.floors = new Floor[floorsLength];
    }

    public Parking(Floor[] floors) {
        this.floors = floors;
    }

    public boolean addFloor(Floor floor) {
        resize();
        floors[countFloors] = floor;
        countFloors++;
        return true;
    }

    public boolean addFloor(int number, Floor floor) {
        resize();
        Floor[] buf = floors;
        System.arraycopy(buf, 0, floors, 0, number);
        System.arraycopy(buf, number, floors, number + 1, countFloors - number);
        floors[countFloors] = floor;
        countFloors++;
        return true;
    }

    public Floor gerFloor(int number) {
        return floors[number];
    }

    public Floor replaceOwnerFloor(int number, Floor newFloor) {
        Floor buf = floors[number];
        floors[number] = newFloor;
        return buf;

    }

    public Floor deleteOwnerFloor(int number) {
        Floor buf = floors[number];
        System.arraycopy(floors, number + 1, floors, number, countFloors - number -1);
        floors[countFloors - 1] = null;
        countFloors--;
        return buf;
    }

    public int getCountFloors() {
        return countFloors;
    }

    public Floor[] getFloors() {
        Floor[] buf = new Floor[countFloors];
        System.arraycopy(floors, 0, buf, 0, countFloors);
        return buf;
    }

    public Floor[] getSortFloors() {
        Floor buf;
        Floor[] newFloors = new Floor[floors.length];
        System.arraycopy(floors, 0, newFloors, 0, floors.length);
        for (int j = 0; j < newFloors.length - 1; j++) {
            for (int i = j; i < newFloors.length - 1; i++) {
                if (newFloors[i].getCountSpace() > newFloors[i + 1].getCountSpace()) {
                    buf = newFloors[i];
                    newFloors[i] = newFloors[i + 1];
                    newFloors[i + 1] = buf;
                }
            }
        }
        return newFloors;
    }

    public Vehicle[] getVehiclesWithSpace() {
        int vehicleCount = 0;
        for (int i = 0; i< floors.length; i++) {
            for (int j = 0; j < floors[i].getSpaces().length; j++) {
                vehicleCount += floors[i].getCountSpace();
            }
        }
        Vehicle[] vehicles = new Vehicle[vehicleCount];
        int index = 0;
        for (int i = 0; i< floors.length; i++) {
            Space[] spaces = floors[i].getSpaces();
            for (int j = 0; j < spaces.length; j++) {
                vehicles[index] = spaces[j].getVehicle();
                index++;
            }
        }
        return vehicles;
    }

    public Space getSpace(String stateNumber) {
        for (int i = 0; i< floors.length; i++) {
            if (floors[i].isVehicle(stateNumber)) {
                return floors[i].getSpace(stateNumber);
            }
        }
        return null;
    }

    public Space deleteSpace(String stateNumber) {
        for (int i = 0; i< floors.length; i++) {
            if (floors[i].isVehicle(stateNumber)) {
                return floors[i].deleteSpace(stateNumber);
            }
        }
        return null;
    }

    public Space replace(String stateNumber, Space space) {
        for (int i = 0; i < floors.length; i++) {
            if (floors[i].isVehicle(stateNumber)) {
                for (int j = 0; j < floors[i].getSpaces().length; j++) {
                    if (floors[i].getSpaces()[j].getVehicle().getStateNumber().equals(stateNumber))
                        return floors[i].replaceSpace(j, space);
                }
            }
        }
        return null;
    }

    private void resize() {
        if (countFloors == floors.length) {
            Floor[] buf = floors;
            floors = new Floor[buf.length * 2];
            System.arraycopy(buf, 0, floors, 0, countFloors);
        }
    }
}
