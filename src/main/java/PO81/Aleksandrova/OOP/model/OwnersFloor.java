package PO81.Aleksandrova.OOP.model;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class OwnersFloor implements Floor, IInstanceHandler {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int INITIAL_SIZE = 0;
    private Space[] spaces;
    private int size;

    public OwnersFloor() {
        this.spaces = new Space[DEFAULT_CAPACITY];
        this.size = INITIAL_SIZE;
    }

    public OwnersFloor(int size) {
        this.spaces = new Space[size];
        this.size = INITIAL_SIZE;
    }

    public OwnersFloor(Space[] spaces) {
        this.spaces = new Space[spaces.length];
        System.arraycopy(spaces, 0, this.spaces, 0, spaces.length);
        this.size = countNotNull(this.spaces);
    }

    private int countNotNull(Space[] spaces) {
        int count = 0;
        for (Space space : spaces) {
            if (space != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean add(Space space) {
        if (this.size == this.spaces.length) {
            expandArray();
        }
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i] == null) {
                spaces[i] = Objects.requireNonNull(space, "Параметр space не должен быть null");
                size = size();
                return true;
            }
        }
        return false;
    }

    @Override
    public void expandArray() {
        Space[] newArray = new Space[this.spaces.length * 2];
        System.arraycopy(this.spaces, 0, newArray, 0, this.size);
        this.spaces = newArray;
    }

    @Override
    public boolean add(int index, Space space) {
        if (this.size == this.spaces.length) {
            expandArray();
        }
        shift(index, false);
        this.spaces[index] = space;
        this.size++;


        return true;
    }

    @Override
    public void shift(int index, boolean isLeft) {
        expandArray();
        if (spaces.length > index && index >= 0) {
            if (isLeft) {
                System.arraycopy(spaces, index + 1, spaces, index, spaces.length - index - 1);
                spaces[spaces.length - 1] = null;
            } else {
                System.arraycopy(spaces, index, spaces, index + 1, spaces.length - index - 1);
                spaces[index] = null;
            }
            size = size();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void expand() {
    }

    @Override
    public Space replaceWith(Space space, int index) {
        return null;
    }

    @Override
    public Space get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return this.spaces[index];
    }

    @Override
    public Space replaceWith(int index, Space space) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Space replacedSpace = spaces[index];
        spaces[index] = Objects.requireNonNull(space, "Параметр space не должен быть null");
        return replacedSpace;
    }

    @Override
    public Space[] toArray() {
        return Arrays.stream(spaces).filter(Objects::nonNull).toArray(Space[]::new);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Spaces:\n");
        iterator().forEachRemaining(space -> builder.append(space.toString()).append("\n"));
        return builder.toString();
    }

    @Override
    public int hashCode() {
        AtomicInteger code = new AtomicInteger(71 * size);
        iterator().forEachRemaining(space -> code.updateAndGet(value -> value ^ space.hashCode()));
        return code.get();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OwnersFloor)) {
            return false;
        }
        OwnersFloor other = (OwnersFloor) obj;
        return size == other.size && Objects.deepEquals(spaces, other.spaces);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
