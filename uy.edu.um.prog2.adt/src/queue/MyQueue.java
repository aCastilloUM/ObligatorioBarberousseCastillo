package queue;

public interface MyQueue<T> {
    boolean isEmpty();
    void enqueue(T valor);
    void dequeue() throws EmptyQueueException;
    void printQueue();
}
