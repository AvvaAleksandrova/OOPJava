package PO81.Aleksandrova.OOP.model;

public class Person {
    private String name;
    private String surname;
    static final Person UNDEFINED = new Person("", "");

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }
}
