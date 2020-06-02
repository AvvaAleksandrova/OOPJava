package PO81.Aleksandrova.OOP.model;

public class NoRentedSpaceException extends Exception {
    public NoRentedSpaceException() {
        super();
    }

    public NoRentedSpaceException(String message) {
        super(message);
    }

    public NoRentedSpaceException(String message, Throwable object) {
        super(message, object);
    }
}

