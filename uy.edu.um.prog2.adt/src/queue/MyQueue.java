package queue;

import exceptions.EmptyQueueException;

public interface MyQueue<T> {

    boolean isEmpty();

    void enqueue(T valor);

    void dequeue() throws EmptyQueueException;

    boolean contains(T value) throws EmptyQueueException;

    void printQueue();
}
