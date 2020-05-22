package PO81.Aleksandrova.OOP;

import PO81.Aleksandrova.OOP.model.OwnersFloor;
import PO81.Aleksandrova.OOP.model.Person;
import PO81.Aleksandrova.OOP.model.Vehicle;

public class Test {
    public static void lab1tests() {
        Person person = new Person("Gibard", "Morten");
        Person person1 = new Person("Ireson", "Clerissa");
        Person person2 = new Person("Gokes", "Mikael");
        Person person3 = new Person("GokesTEST", "MikaelTEST");

        Vehicle vehicle = new Vehicle("West-Borer", "Duiker, common", "1FT7W2A66EE790570");
        Vehicle vehicle1 = new Vehicle("Kshlerin, Schaefer and Nienow", "Chipmunk, least", "WAUKH94F26N511206");
        Vehicle vehicle2 = new Vehicle("Zieme Inc", "Hyena, spotted", "WAULFAFR4BA791523");
        Vehicle vehicle3 = new Vehicle("Zieme IncTEST", "Hyena, spotted TEST", "WAULFAFR4BA791523 TEST");

        Space space = new Space(person, vehicle);
        Space space1 = new Space(person1, vehicle1);
        Space space2 = new Space(person2, vehicle2);
        Space space3 = new Space(person3, vehicle3);

        Space[] spaces = new Space[3];
        spaces[0] = space;
        spaces[1] = space1;
        spaces[2] = space2;

        OwnersFloor ownersFloor = new OwnersFloor(spaces);

        Space deleteSpace2 = ownersFloor.deleteSpace(1);
        Space replace = ownersFloor.replaceSpace(2,space1);

    }

    ;

    public static void main(String[] args) {
        lab1tests();
    }
}
