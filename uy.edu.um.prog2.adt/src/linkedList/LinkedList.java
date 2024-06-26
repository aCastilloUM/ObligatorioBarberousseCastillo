package linkedList;

import exceptions.EmptyListException;

public class LinkedList<T> implements MyList<T> {

    ListNode<T> head;
    ListNode<T> last;
    int size;

    public LinkedList(){
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    public ListNode<T> getHead() {
        return head;
    }

    public ListNode<T> getLast() {
        return last;
    }

    public void setHead(ListNode<T> head) {
        this.head = head;
    }

    public void setLast(ListNode<T> last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (head == null && size == 0);
    }

    @Override
    public void addFirst(T value) {
        if (head == null){
            head = new ListNode<T>(value);
        } else {
            ListNode<T> newNode = new ListNode<T>(value);
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T value) {
        ListNode<T> newNode = new ListNode<>(value);
        if (head == null){
            head = newNode;
            last = newNode;
        } else {
            ListNode<T> temp = this.head;
            while (temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    @Override
    public void remove(int position) throws EmptyListException {
        if (size == 0){
            throw new EmptyListException();
        }
        if (position == 0){
            head = head.getNext();
        } else {
            int contador = 0;
            ListNode<T> temp = head;
            while (contador < position - 1){
                temp = temp.getNext();
                contador++;
            }
            temp.setNext(temp.getNext().getNext());
        }
        size--;
    }

    @Override
    public T getValueNode(int position) {
        int contador = 0;
        ListNode<T> temp = this.head;
        T retorno = null;
        while (contador != position && temp != null){
            temp = temp.getNext();
            contador++;
        }
        if (contador == position){
            retorno = temp.getValue();
        }
        return retorno;
    }

    @Override
    public boolean contains(T value) {
        ListNode<T> temp = this.head;
        boolean encontrar = false;
        while (temp != null && temp.getValue().equals(value)) {
            temp = temp.getNext();
        }
        if (temp != null) {
            encontrar = true;
        }
        return encontrar;
    }

    @Override
    public void cut (int index) {
        int contador = 0;
        ListNode<T> temp = head;
        while (contador < index - 1){
            temp = temp.getNext();
            contador++;
        }
        temp.setNext(null);
        last = temp;
        size = index;
    }

    @Override
    public void printList() {
        ListNode<T> temp = this.head;
        do {
            System.out.println(temp.getNext());
            temp = temp.getNext();
        } while (temp != null);
    }

}
