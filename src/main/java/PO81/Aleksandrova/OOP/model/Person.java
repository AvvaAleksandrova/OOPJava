package PO81.Aleksandrova.OOP.model;

public final class Person {
    public static final Person UNKNOWN_PERSON = new Person("", "");
    private String firstName;
    private String secondName;


    public Person(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }


    public static void getUnknownPerson() {
    }


    public String getFirstName() {
        return firstName;
    }


    public String getSecondName() {
        return secondName;
    }


    @Override
    public String toString() {
        return "\nPerson info: " + "first name - " + firstName + ", second name - " + secondName;
    }
}





