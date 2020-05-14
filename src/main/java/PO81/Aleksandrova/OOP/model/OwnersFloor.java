package PO81.Aleksandrova.OOP.model;

public class OwnersFloor {
    private Space[] spaces;
    private int countSpaces;

    public OwnersFloor() {
        this(16);
    }

    public OwnersFloor(int spacesCount) {
        this.spaces = new Space[spacesCount];
        countSpaces = 0;
    }

    public OwnersFloor(Space[] spaces) {
        this.spaces = spaces;
        countSpaces = spaces.length;
    }

    public boolean addSpace(Space space) {
        resize();
        spaces[countSpaces] = space;
        countSpaces++;
        return true;
    }

    public boolean addSpace(int number, Space space) {
        resize();
        Space[] buf = spaces;
        System.arraycopy(buf, 0, spaces, 0, number);
        System.arraycopy(buf, number, spaces, number + 1, countSpaces - number);
        spaces[number] = space;
        countSpaces++;
        return true;
    }

    public Space getSpace(int number) {
        return spaces[number];
    }

    public Space getSpace(String stateNumber) {
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i].getVehicle().getStateNumber().equals(stateNumber)) {
                return spaces[i];
            }
        }
        return null;
    }

    public boolean isVehicle(String stateNumber) {
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i].getVehicle().getStateNumber().equals(stateNumber)) {
                return true;
            }
        }
        return false;
    }

    public Space replaceSpace(int number, Space newSpace) {
        Space buf = spaces[number];
        spaces[number] = newSpace;
        return buf;
    }

    public Space deleteSpace(int number) {
        Space buf = spaces[number];
        System.arraycopy(spaces, number + 1, spaces, number, countSpaces - number -1);
        spaces[countSpaces - 1] = null;
        countSpaces--;
        return buf;
    }

    public Space deleteSpace(String stateNumber) {
        Space buf;
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i].getVehicle().getStateNumber().equals(stateNumber)){
                return deleteSpace(i);
            }
        }
        return null;
    }

    public int getCountSpaces() {
        return countSpaces;
    }

    public Space[] getSpaces() {
        Space[] buf = new Space[countSpaces];
        System.arraycopy(spaces, 0, buf, 0, countSpaces);
        return buf;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[countSpaces];
        for (int i = 0; i < countSpaces; i++) {
            vehicles[i] = spaces[i].getVehicle();
        }
        return vehicles;
    }

    private void resize() {
        if (countSpaces == spaces.length) {
            Space[] buf = spaces;
            spaces = new Space[buf.length * 2];
            System.arraycopy(buf, 0, spaces, 0, countSpaces);
        }
    }
}
