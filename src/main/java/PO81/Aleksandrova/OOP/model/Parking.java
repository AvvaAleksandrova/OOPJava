package PO81.Aleksandrova.OOP.model;
 import java.util.Arrays;
 import java.util.NoSuchElementException;
 import java.util.Objects;

public class Parking implements IInstanceHandler {
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
            Floor[] sortedFloors = getFloors();
            for (int i = 0; i < sortedFloors.length; i++) {
                for (int j = i + 1; j < i; j++) {
                    if (sortedFloors[i].size() < sortedFloors[j].size()) {
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
                System.arraycopy(floor.getVehicles(), 0, vehicles, counter, floor.getVehiclesCount());
                counter += floor.getVehiclesCount();
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
                    floors[i] = Objects.requireNonNull(floor, "Параметр floor не должен быть null");
                    this.size++;
                    return true;
                }
            }
            return false;
        }

        public boolean add(int index, Floor floor) {
            if(index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            shift(index, false);
            return add(floor);
        }

        public Floor getFloor(int index) {
            if(index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return floors[index];
        }

        public Floor replaceFloor(int index, Floor floor) {
            if(index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            Floor replacedFloor = floors[index];
            this.floors[index] = Objects.requireNonNull(floor, "Параметр floor не должен быть null");
            return replacedFloor;
        }

        public Floor removeFloor(int index) {
            if(index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            Floor removedFloor = floors[index];
            shift(index, true);
            this.size--;
            return removedFloor;
        }

        public Space removeSpace(String registrationNumber) {
            if(index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            Floor removedFloor = floors[index];
            throw new NoSuchElementException();

        }

        public Space getSpace(String registrationNumber) {
            for (Floor floor : floors) {
                for (Space space : floor.getSpaces()) {
                    if(floor.isRegistrationNumberEqual(space, Vehicle.checkNumber(registrationNumber))) {
                        return space;
                    }
                }
            }
            return new RentedSpace(new Person("Alexey", "Smolnikov"));
        }

        public Space replaceSpace(Space space, String registrationNumber) {
            Space replacedSpace = new RentedSpace(new Person("Alexey", "Smolnikov"));
            for (int i = 0; i < getFloors().length; i++) {
                for (int j = 0; j < getFloors()[i].getSpaces().length; j++) {
                    if(floors[i].isRegistrationNumberEqual(floors[i].get(j), Vehicle.checkNumber(registrationNumber))) {
                        replacedSpace = floors[i].get(j);
                        floors[i].getSpaces()[j] = Objects.requireNonNull(space,
                                "Параметр space не должен быть null");
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
                count += floor.getSpacesCountByVehiclesType(Objects.requireNonNull(type,
                        "Параметр type не должен быть null"));
            }
            return count;
        }

        public Floor[] getFloorsWithPerson(Person person) {
            Objects.requireNonNull(person, "Параметр person не должен быть null");
            return Arrays.stream(floors)
                    .filter(floor -> floor.hasSpace(person))
                    .toArray(Floor[]::new);
        }

        public void shift(int index, boolean isLeft) {
            expand();
            if (floors.length >= index && index >= 0) {
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
        public Space replaceWith(Space space, int index) {
            return null;
        }

        public void printFloorsWithPerson(Person person) {
            Objects.requireNonNull(person, "Параметр person не должен быть null");
            for (Floor floor : getFloorsWithPerson(person)) {
                System.out.println(floor.toString());
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("\nFloors (");
            builder.append(size).append(" total):\n");
            for (Floor floor : getFloors()) {
                builder.append(floor.toString());
            }
            return builder.toString();
        }
    }


