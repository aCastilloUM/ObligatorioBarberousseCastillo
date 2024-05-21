package queue;

public class QueueNode<T> {

    private T value;
    private QueueNode<T> next;
    private QueueNode<T> previous;

    public QueueNode(T value){
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public QueueNode<T> getNext() {
        return next;
    }

    public void setNext(QueueNode<T> next) {
        this.next = next;
    }

    public QueueNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(QueueNode<T> previous) {
        this.previous = previous;
    }
}
