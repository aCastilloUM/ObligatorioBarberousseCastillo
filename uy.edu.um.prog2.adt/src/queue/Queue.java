package queue;

import exceptions.EmptyQueueException;

public class Queue<T> implements MyQueue<T> {

    QueueNode<T> first;
    QueueNode<T> last;          //Primero en entrar, primero en salir
    int size;

    public Queue(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return (first == null && size == 0);
    }

    @Override
    public void enqueue(T valor) {
        QueueNode<T> newQueue = new QueueNode<T>(valor);
        if (last == null){
            last = newQueue;
            first = newQueue;
        } else {
            QueueNode<T> temp = last;
            while (temp.getPrevious() != null){
                temp = temp.getPrevious();
            }
            temp.setPrevious(newQueue);
            first = newQueue;
        }
        size++;
    }

    @Override
    public void dequeue() throws EmptyQueueException {
        if (isEmpty()){
            throw new EmptyQueueException();
        }
        QueueNode<T> temp = last;
        if (size == 1) {
            first = null;
            last = null;
            size = 0;
        } else {
            temp = temp.getPrevious();
            last = temp;
            last.setNext(null);
            size--;
        }
    }

    public boolean contains(T value) throws EmptyQueueException {
        QueueNode<T> temp = last;
        if (temp == null){
            throw new EmptyQueueException();
        }
        while (temp != null) {
            if (temp.getValue().equals(value)) {
                return true;
            }
            temp = temp.getPrevious();
        }
        return false;
    }

    @Override
    public void printQueue() {
        QueueNode<T> temp = last;
        while (temp != null){
            System.out.println(temp.getValue());
            temp = temp.getPrevious();
        }
    }
}
