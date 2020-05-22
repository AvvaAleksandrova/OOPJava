package PO81.Aleksandrova.OOP.model;

public class RentedSpacesFloor {
    private Node head;
    private Node tail;
    private int size;

    public RentedSpacesFloor() {
        head = new Node(null, null, null);
        tail = new Node(null, null, null);
        size = 0;
    }

    public RentedSpacesFloor(Space[] spaces){
        head = new Node(null, null, null);
        tail = new Node(null, null, null);
        size = 0;
        for (int i = 0; i < spaces.length; i++){
            addSpace(spaces[i]);
        }
    }

    public boolean addSpace(Space space) {
        addLast(space);
        size++;
        return true;
    }

    public boolean addSpace(int number, Space space) {
        if (checkIndex(number)){
            if (number==size){
                addLast(space);
                size++;
                return true;
            } else {
                addTo(number, space);
                size++;
                return true;
            }
        }
        return false;
    }

    public Space getSpace(int number) {
        Node current = head;
        if (checkIndex(number)){
            for (int i = 0; i <= number; i++){
                current = current.next;
            }
        }
        return current.value;
    }

    public Space getSpace(String stateNumber) {
        for (int i = 0; i < size; i++){
            if (getNode(i).value.getVehicle().getStateNumber().equals(stateNumber)){
                return getNode(i).value;
            }
        }
        return null;
    }

    public boolean isVehicle(String stateNumber) {
        for (int i = 0; i < size; i++){
            if (getNode(i).value.getVehicle().getStateNumber().equals(stateNumber)){
                return true;
            }
        }
        return false;
    }

    public Space replaceSpace(int number, Space newSpace) {
        if (checkIndex(number)){
            Space current = getNode(number).value;
            getNode(number).value = newSpace;
            return current;
        }
        return null;
    }

    public Space deleteSpace(int number) {
        if (checkIndex(number)){
            removeNode(number);
            return getNode(number).value;
        }
        return null;
    }

    public Space deleteSpace(String stateNumber) {
        for (int i = 0; i < size; i++){
            if (getNode(i).value.getVehicle().getStateNumber().equals(stateNumber)){
                removeNode(i);
                return getNode(i).value;
            }
        }
        return null;
    }

    public int getCountSpace() {
        return size;
    }

    public Space[] getSpaces() {
        Space[] spaces = new Space[size];
        for (int i = 0; i < size; i++){
            spaces[i] = getNode(i).value;
        }
        return spaces;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[size];
        for (int i = 0; i < size; i++){
            vehicles[i] = getNode(i).value.getVehicle();
        }
        return vehicles;
    }

    private static class Node{
        Space value;
        Node previous;
        Node next;

        Node(Space value, Node previous, Node next){
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        public Space getValue() {
            return value;
        }

        public void setValue(Space value) {
            this.value = value;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private void addFirst(Space space){
        Node first = head;
        Node newNode = new Node(space, null, first);
        head = newNode;
        if (first == null){
            tail = newNode;
        } else {
            first.previous = newNode;
        }
    }

    private void addTo(int index, Space space){
        Node nextNode = getNode(index);
        Node prevNode = getNode(index - 1);
        Node newNode = new Node(space, nextNode.previous, nextNode);
        nextNode.previous = newNode;
        if (head.value == null){
            head = newNode;
        } else {
            prevNode = newNode;
        }
    }

    private void addLast(Space space){
        Node last = tail;
        Node newNode = new Node(space, last, null);
        tail = newNode;
        if (head.value == null){
            head = newNode;
        } else {
            last.next = newNode;
        }

    }

    private Node getNode(int index){
        if (index < (size >> 1)) {
            Node target = head;
            for (int i = 0; i < index; i++)
                target = target.next;
            return target;
        } else {
            Node target = tail;
            for (int i = size - 1; i > index; i--)
                target = target.previous;
            return target;
        }
    }

    private void removeNode(int index){
        if (checkIndex(index)){
            getNode(index).setNext(getNode(index + 1));
        }
    }

    private boolean checkIndex(int index){
        return (index >= 0 && index <= size);
    }
}
