package PO81.Aleksandrova.OOP.model;

import java.util.Objects;

public final class Person implements Cloneable {
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
        return String.format("%s %s", secondName, firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person other = (Person) obj;
        return Objects.equals(firstName, other.firstName) && Objects.equals(secondName, other.secondName);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}





