package PO81.Aleksandrova.OOP;

import PO81.Aleksandrova.OOP.model.OwnersFloor;
import PO81.Aleksandrova.OOP.model.Person;
import PO81.Aleksandrova.OOP.model.Vehicle;

public class Test {
    public static void lab1tests() {

    }

    public static void lab2tests(){

    }
   public static void lab3tests(){
        printFrame();
        System.out.println("Тестирование изменений в классе Vehicle");
        printFrame();
        Vehicle truck = new Vehicle("H7716OM", "Scania", "SL50", VehicleTypes.TRUCK);
        Vehicle bikeWithoutNumbers = new Vehicle(VehicleTypes.MOTORBIKE);
        Vehicle voidVehicle = new Vehicle();
        System.out.println("Типы созданных машин: " + truck.getType().getValue() + ", " +
                bikeWithoutNumbers.getType().getValue() + ", " + voidVehicle.getType().getValue());
        printFrame();
        System.out.println("Тестирование изменений в классах, реализующих интерфейс Space");
        printFrame();
        RentedSpace rentedSpace = new RentedSpace(new Person("Alexey", "Smolnikov"));
        OwnedSpace ownedSpace = new OwnedSpace(truck, new Person("Max", "Ludendorf"));
        System.out.println("Является ли rentedSpace пустым? " + rentedSpace.isEmpty());
        System.out.println("Является ли ownedSpace пустым? " + ownedSpace.isEmpty());
        System.out.println("Вызов метода setVehicle()...");
        rentedSpace.setVehicle(bikeWithoutNumbers);
        System.out.println("Является ли rentedSpace пустым? " + rentedSpace.isEmpty() + "\n");
        printFrame();
        System.out.println("Тестирование новых методов интерфейса Floor и класса Parking");
        printFrame();
        Parking parking = new Parking(createOwnersFloor(), createRentedSpacesFloor());
        OwnersFloor firstFloor = (OwnersFloor) parking.getFloor(0);
        RentedSpacesFloor secondFloor = (RentedSpacesFloor) parking.getFloor(1);
        System.out.println("Cписок кроссоверов на первом этаже");
        printFrame();
        firstFloor.printSpacesByVehiclesType(VehicleTypes.CROSSOVER);
        printFrame();
        System.out.println("Cписок автомобилей на втором этаже");
        printFrame();
        secondFloor.printSpacesByVehiclesType(VehicleTypes.CAR);
        printFrame();
        firstFloor.addSpace(new RentedSpace());
        firstFloor.addSpace(new RentedSpace());
        System.out.println("Кол-во пустых мест на первом этаже: " + firstFloor.getFreeSpaces().length);
        printFrame();
        secondFloor.addSpace(new RentedSpace());
        System.out.println("Oбщее число незанятых парковочных мест: " + parking.getFreeSpacesCount());
        printFrame();
        System.out.println("Общее число автомобилей: " + parking.getSpacesCountByVehiclesType(VehicleTypes.CAR));
        printFrame();

    }

    public static void main(String[] args) {
        lab3tests();
    }
}
