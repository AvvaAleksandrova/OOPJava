package PO81.Aleksandrova.OOP.model;

public interface IInstanceHandler {
    void shift(int index, boolean isLeft);
    void expand();

    Space replaceWith(Space space, int index);

}
