package linkedList;

public class LinkedList<T> implements MyList<T> {

    Node<T> head;
    Node<T> last;
    int size;

    public LinkedList(){
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void addFirst(T value) {
        if (head == null){
            head = new Node<T>(value);
        } else {
            Node<T> newNode = new Node<T>(value);
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T value) {
        if (head == null){
            head = new Node<T> (value);
            this.last = this.head;
        } else {
            Node<T> newNode = new Node<>(value);
            Node<T> temp = this.head;
            while (temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }
        size++;
    }

    @Override
    public void remove(int position) {

    }

    @Override
    public T getValueNode(int position) {
        return null;
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    @Override
    public void cut(int index) {

    }

    @Override
    public void printList() {

    }
}
