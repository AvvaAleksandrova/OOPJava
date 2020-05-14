package PO81.Aleksandrova.OOP.model;

public class Parking {
    private OwnersFloor[] ownersFloors;
    int countOwnersFloors = 0;

    public Parking(int ownersFloorsLength) {
        this.ownersFloors = new OwnersFloor[ownersFloorsLength];
    }

    public Parking(OwnersFloor[] ownersFloors) {
        this.ownersFloors = ownersFloors;
    }

    public boolean addOwnersFloor(OwnersFloor ownersFloor) {
        resize();
        ownersFloors[countOwnersFloors] = ownersFloor;
        countOwnersFloors++;
        return true;
    }

    public boolean addOwnersFloor(int number, OwnersFloor ownersFloor) {
        resize();
        OwnersFloor[] buf = ownersFloors;
        System.arraycopy(buf, 0, ownersFloors, 0, number);
        System.arraycopy(buf, number, ownersFloors, number + 1, countOwnersFloors - number);
        ownersFloors[countOwnersFloors] = ownersFloor;
        countOwnersFloors++;
        return true;
    }

    public OwnersFloor gerOwnersFloor(int number) {
        return ownersFloors[number];
    }

    public OwnersFloor replaceOwnerFloor(int number, OwnersFloor newOwnersFloor) {
        OwnersFloor buf = ownersFloors[number];
        ownersFloors[number] = newOwnersFloor;
        return buf;

    }

    public OwnersFloor deleteOwnerFloor(int number) {
        OwnersFloor buf = ownersFloors[number];
        System.arraycopy(ownersFloors, number + 1, ownersFloors, number, countOwnersFloors- number -1);
        ownersFloors[countOwnersFloors - 1] = null;
        countOwnersFloors--;
        return buf;
    }

    public int getCountOwnersFloors() {
        return countOwnersFloors;
    }

    public OwnersFloor[] getOwnersFloors() {
        OwnersFloor[] buf = new OwnersFloor[countOwnersFloors];
        System.arraycopy(ownersFloors, 0, buf, 0, countOwnersFloors);
        return buf;
    }

    public OwnersFloor[] getSortOwnersFloors() {
        OwnersFloor buf;
        OwnersFloor[] newOwnersFloors = new OwnersFloor[ownersFloors.length];
        System.arraycopy(ownersFloors, 0, newOwnersFloors, 0, ownersFloors.length);
        for (int j = 0; j < newOwnersFloors.length - 1; j++) {
            for (int i = j; i < newOwnersFloors.length - 1; i++) {
                if (newOwnersFloors[i].getCountSpaces() > newOwnersFloors[i + 1].getCountSpaces()) {
                    buf = newOwnersFloors[i];
                    newOwnersFloors[i] = newOwnersFloors[i + 1];
                    newOwnersFloors[i + 1] = buf;
                }
            }
        }
        return newOwnersFloors;
    }

    public Vehicle[] getVehiclesWithSpace() {
        int vehicleCount = 0;
        for (int i = 0; i< ownersFloors.length; i++) {
            for (int j = 0; j < ownersFloors[i].getSpaces().length; j++) {
                vehicleCount += ownersFloors[i].getCountSpaces();
            }
        }
        Vehicle[] vehicles = new Vehicle[vehicleCount];
        int index = 0;
        for (int i = 0; i< ownersFloors.length; i++) {
            Space[] spaces = ownersFloors[i].getSpaces();
            for (int j = 0; j < spaces.length; j++) {
                vehicles[index] = spaces[j].getVehicle();
                index++;
            }
        }
        return vehicles;
    }

    public Space getSpace(String stateNumber) {
        for (int i = 0; i< ownersFloors.length; i++) {
            if (ownersFloors[i].isVehicle(stateNumber)) {
                return ownersFloors[i].getSpace(stateNumber);
            }
        }
        return null;
    }

    public Space deleteSpace(String stateNumber) {
        for (int i = 0; i< ownersFloors.length; i++) {
            if (ownersFloors[i].isVehicle(stateNumber)) {
                return ownersFloors[i].deleteSpace(stateNumber);
            }
        }
        return null;
    }

    public Space replace(String stateNumber, Space space) {
        for (int i = 0; i < ownersFloors.length; i++) {
            if (ownersFloors[i].isVehicle(stateNumber)) {
                for (int j = 0; j < ownersFloors[i].getSpaces().length; j++) {
                    if (ownersFloors[i].getSpaces()[j].getVehicle().getStateNumber().equals(stateNumber))
                        return ownersFloors[i].replaceSpace(j, space);
                }
            }
        }
        return null;
    }

    private void resize() {
        if (countOwnersFloors == ownersFloors.length) {
            OwnersFloor[] buf = ownersFloors;
            ownersFloors = new OwnersFloor[buf.length * 2];
            System.arraycopy(buf, 0, ownersFloors, 0, countOwnersFloors);
        }
    }
}
