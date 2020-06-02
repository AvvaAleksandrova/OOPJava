package PO81.Aleksandrova.OOP;

import PO81.Aleksandrova.OOP.model.OwnersFloor;
import PO81.Aleksandrova.OOP.model.Person;
import PO81.Aleksandrova.OOP.model.Vehicle;
import PO81.Aleksandrova.OOP.model.OwnedSpace;
import PO81.Aleksandrova.OOP.model.Parking;
import PO81.Aleksandrova.OOP.model.RentedSpace;
import PO81.Aleksandrova.OOP.model.RentedSpacesFloor;
import PO81.Aleksandrova.OOP.model.Space;
import PO81.Aleksandrova.OOP.model.VehicleTypes;
import java.time.LocalDate;
import java.time.Period;
import java.util.NoSuchElementException;
import java.util.Random;



public class Test {
        public static void main(String[] args) {
                //lab1tests();
                //lab2tests();
                //lab3tests();
                //lab4tests();
                //lab5tests();
                lab6tests();
        }


        public static void lab1tests() {
                printFrame();
                System.out.println("Лабораторная №1");
                testPersons();
                testVehicles();
                testSpaces();
                testParking();
        }


        private static void testPersons() {
                printFrame();
                Person firstPerson = new Person("Thomas", "Müller");
                Person secondPerson = new Person("Victoria", "Androsenko");
                Person thirdPerson = new Person("Ivan", "Bubin");
                System.out.println("Вот несколько человек:\n" + firstPerson.toString() + secondPerson.toString() +
                        thirdPerson.toString());
        }


        private static void testVehicles() {
                printFrame();
                Vehicle vehicle = new Vehicle("OH 35811", "Mercedes", "Benz", VehicleTypes.CAR);
                Vehicle secondVehicle = new Vehicle(VehicleTypes.MOTORBIKE);
                System.out.println("Вот несколько машин:\n " + vehicle.toString() + "\n" + secondVehicle.toString());
        }


        private static void testSpaces() {
                printFrame();
                RentedSpace firstRentedSpace = new RentedSpace();
                RentedSpace secondRentedSpace = new RentedSpace(new Vehicle("OH 35811", "Mercedes", "Benz", VehicleTypes.CAR),
                        new Person("Becky", "Lynch"));
                RentedSpace thirdRentedSpace = new RentedSpace(new Vehicle(VehicleTypes.TRUCK), new Person("Ivan", "Bubin"));
                System.out.println("Первое место пустое? " + firstRentedSpace.isEmpty());
                System.out.println("Второе место пустое? " + secondRentedSpace.isEmpty());
                System.out.println("Третье место пустое? " + thirdRentedSpace.isEmpty());
        }


        void testOwnersFloor() {
                OwnersFloor floor = createOwnersFloor();
                System.out.println("Состояние этажа после распределения мест: " + floor.toString());
                System.out.println("\nНайдено место по номеру K257TC: " + floor.hasSpace("K257TC") + ", " + floor.get("K257TC").toString());
                System.out.println("\nНайдено место по индексу 2: " + floor.get(2).toString());
                floor.replaceWith(1, new RentedSpace(new Vehicle("X225HK", "UAZ", "Patriot", VehicleTypes.CAR),
                        new Person("Becky", "L")));
                System.out.println("\nСостояние этажа после замены места: " + floor.toString());
                floor.remove("OH 35811");
                System.out.println("\nСостояние этажа после удаления места: " + floor.toString());
        }


        private static void testParking() {
                printFrame();
                OwnersFloor floor = createOwnersFloor();
                Parking parking = new Parking(2);
                parking.add(new OwnersFloor());
                floor.add(new RentedSpace(new Vehicle("P807OC", "Lada", "Granta", VehicleTypes.CAR),
                        new Person("Vladimir", "Vladimirov")));
                parking.add(0, floor);
                System.out.print(parking.toString());
                System.out.print("\nРазмеры отсортированных этажей: " + parking.getSortedFloors()[0].size() + ", " +
                        parking.getSortedFloors()[1].size());
                System.out.print("\nОбщее кол-во машин: " + parking.getVehicles().length);
                parking.replaceFloor(0, floor);
                parking.replaceSpace(new RentedSpace(), "K257TC");
                parking.removeSpace("A897AO");
                parking.removeFloor(1);
                System.out.println("\nКонечное состояние парковки");
                System.out.println(parking.toString());
        }


        private static void lab2tests() {
                printFrame();
                System.out.println("Лабораторная №2");
                printFrame();
                Parking parking = new Parking(createOwnersFloor(), createRentedSpacesFloor());
                RentedSpacesFloor floor = (RentedSpacesFloor) parking.getFloor(1);
                printFrame();
                System.out.println("Тестирование нового добавленного класса - RentedSpaceFloor\n" +
                        "Начальное состояние этажа");
                printFrame();
                floor.printSpaces();
                printFrame();
                System.out.println("Удаление парковочного места с индексом 2");
                printFrame();
                floor.remove(2);
                floor.printSpaces();
                printFrame();
                System.out.println("Добавление парковочного места по индексу 1");
                printFrame();
                floor.add(1, new RentedSpace(new Vehicle("E005PP", "Geely", "NX", VehicleTypes.CAR),
                        new Person("Paolo", "Manchini")));
                floor.printSpaces();
                printFrame();
                System.out.println("Замена парковочного места с индексом 2 ");
                printFrame();
                floor.replaceWith(2, new RentedSpace(new Vehicle("A897AO", "Volkswagen", "Polo", VehicleTypes.CAR),
                        new Person("Gregor", "McYorn")));
                floor.printSpaces();
                printFrame();
                System.out.println("Добавление еще одного места в коне списка");
                printFrame();
                floor.add(new RentedSpace(new Vehicle("K649TA", "Opel", "Astra", VehicleTypes.CAR),
                        new Person("Ivan", "Bubin")));
                floor.printSpaces();
                printFrame();
                System.out.println("Проверка наличия парковочных мест по заданному номеру");
                printFrame();
                System.out.println("Результат поиска для [X225HK]: " + floor.hasSpace("X225HK"));
                System.out.println("Результат поиска для [X100XX]: " + floor.hasSpace("X100XX") + "\n");
                printFrame();
                System.out.println("Список всех машин на этаже");
                printFrame();
                floor.printVehicles();
        }


        private static RentedSpacesFloor createRentedSpacesFloor() {
                return new RentedSpacesFloor(new Space[]{
                        new RentedSpace(new Vehicle("K257TC", "BMW", "X5", VehicleTypes.CAR),
                                new Person("Thomas", "Müller")),
                        new RentedSpace(new Vehicle("T441AX", "Toyota", "RAV4", VehicleTypes.CAR),
                                new Person("Victoria", "Androsenko")),
                        new RentedSpace(new Vehicle("A897AO", "Volkswagen", "Polo", VehicleTypes.CAR),
                                new Person("Gregor", "McYorn")),
                        new RentedSpace(new Vehicle("X225HK", "UAZ", "Patriot", VehicleTypes.CROSSOVER),
                                new Person("Vladimir", "Vladimirov"))
                });
        }


        private static OwnersFloor createOwnersFloor() {
                OwnersFloor floor = new OwnersFloor();
                for (Space space : createRentedSpacesFloor().getSpaces()) {
                        floor.add(new OwnedSpace(space.getVehicle(), space.getPerson()));
                }
                return floor;
        }


        private static void printFrame() {
                System.out.println("============================================" +
                        "============================================");
        }


        private static void lab3tests() {
                printFrame();
                System.out.println("Лабораторная №3");
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
                OwnersFloor firstFloor = createOwnersFloor();
                RentedSpacesFloor secondFloor = createRentedSpacesFloor();
                Parking parking = new Parking(firstFloor, secondFloor);
                System.out.println("Cписок кроссоверов на первом этаже");
                printFrame();
                firstFloor.printSpacesByVehiclesType(VehicleTypes.CROSSOVER);
                printFrame();
                System.out.println("Cписок автомобилей на втором этаже");
                printFrame();
                secondFloor.printSpacesByVehiclesType(VehicleTypes.CAR);
                printFrame();
                firstFloor.add(new RentedSpace());
                firstFloor.add(new RentedSpace());
                System.out.println("Кол-во пустых мест на первом этаже: " + firstFloor.getFreeSpaces().length);
                printFrame();
                secondFloor.add(new RentedSpace());
                System.out.println("Oбщее число незанятых парковочных мест: " + parking.getFreeSpacesCount());
                printFrame();
                System.out.println("Общее число автомобилей: " + parking.getSpacesCountByVehiclesType(VehicleTypes.CAR));
                printFrame();
        }


        private static void lab4tests() {
                printFrame();
                System.out.println("Лабораторная №4");
                printFrame();
                System.out.println("Тестирование методов toString(), equals() и hascode()");
                printFrame();
                OwnersFloor firstFloor = createOwnersFloor();
                RentedSpacesFloor secondFloor = createRentedSpacesFloor();
                Parking parking = new Parking(firstFloor, secondFloor);
                Space space = firstFloor.get(2);
                Space otherSpace = secondFloor.get(1);
                System.out.println(space.toString() + "\n" + otherSpace.toString());
                System.out.println("space hashcode: " + space.hashCode());
                System.out.println("otherSpace hashcode: " + otherSpace.hashCode());
                System.out.println("space.equals(otherSpace)? " + space.equals(otherSpace));
                System.out.println("space.equals(space)? " + space.equals(space));
                printFrame();
                System.out.println("Тестирование новых методов интерфейса Floor и класса Parking");
                printFrame();
                System.out.println("Удаление парковочного места по ссылке");
                printFrame();
                System.out.println("Состояние этажа до удаления места: ");
                firstFloor.printSpaces();
                System.out.println("Удаление прошло успешно? " + firstFloor.remove(space));
                System.out.println("Состояние этажа после удаления места: ");
                firstFloor.printSpaces();
                printFrame();
                space = firstFloor.get(1);
                System.out.println("Индекс вхождения объекта Space: " + firstFloor.indexOf(space));
                printFrame();
                Person person = space.getPerson();
                System.out.println("Число парковочных мест, связанных c " + person.toString() + ": " +
                        firstFloor.getSpacesCountWithPerson(person));
                printFrame();
                System.out.println("Массив этажей, на которых есть парковочные места, принадлежащие " + person.toString());
                printFrame();
                parking.printFloorsWithPerson(person);
                printFrame();
        }
        private static void lab5tests() {
                printFrame();
                System.out.println("Лабораторная №5");
                printFrame();
                OwnersFloor firstFloor = createOwnersFloor();
                RentedSpacesFloor secondFloor = createRentedSpacesFloor();
                Parking parking = new Parking(firstFloor, secondFloor);
                System.out.println("Тестирование исключений");
                printFrame();
                try {
                        Person guessWho = new Person(null, "Mortunkov");
                        Vehicle vehicleWithIncorrectNumber = new Vehicle("rniebo", "Suzuki", "MAV5", VehicleTypes.MOTORBIKE);
                        firstFloor.getSpacesByVehiclesType(null);
                        firstFloor.get(23);
                        System.out.println("Тестирование новых методов, работающих с LocalDate");
                        printFrame();
                        RentedSpace rentedSpace = (RentedSpace) secondFloor.get(1);
                        System.out.println(rentedSpace.toString());
                        System.out.println("Период аренды rentedSpace: " + getPeriodValue(rentedSpace.getPeriod()));
                        System.out.println("Ближайшая дата окончания аренды на этаже: " + secondFloor.getNearestEndsDate());
                        System.out.println("Парковочное место с ближайшей датой окончания аренды:\n" +
                                secondFloor.getSpaceWithNearestEndsDate().toString());
                        printFrame();
                        System.out.println("Список всех мест на этаже:\n");
                        secondFloor.printSpaces();
                } catch (NullPointerException | RegistrationNumberFormatException | IndexOutOfBoundsException |
                        NoSuchElementException | NoRentedSpaceException e) {
                        e.printStackTrace();
                }
        }


        private static String getPeriodValue(Period period) {
                return String.format("%d years %d months %d days", period.getYears(), period.getMonths(), period.getDays());
        }
        private static void lab6tests() {
                printFrame();
                System.out.println("Лабораторная №6");
                printFrame();
                System.out.println("Тестирование компаратора");
                printFrame();
                OwnersFloor firstFloor = createOwnersFloor();
                RentedSpacesFloor secondFloor = createRentedSpacesFloor();
                secondFloor.add(new RentedSpace());
                OwnersFloor voidFloor = new OwnersFloor();
                Parking parking = new Parking(firstFloor, secondFloor, voidFloor);
                System.out.print("Размеры этажей, отсортированных по возрастанию: ");
                for (Floor floor : parking.getSortedFloors()) {
                        System.out.print(floor.size() + " ");
                }
                System.out.println();
                printFrame();
                System.out.println("[!] Итератор используется в методах, которые уже протестированы в других лабораторных [!]");
        }



}
