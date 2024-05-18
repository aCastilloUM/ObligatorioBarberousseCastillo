package queue;

public class Queue<T> implements MyQueue<T> {

    QueueNode<T> first;
    QueueNode<T> last;
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
        } else {
            QueueNode<T> temp = last;
            while (temp.getPrevious() != null){
                temp = temp.getPrevious();
            }
            temp.setPrevious(newQueue);
            first = newQueue;
        }
    }

    @Override
    public void dequeue() throws EmptyQueueException {
        QueueNode<T> temp = last;
        if (isEmpty()){
            throw new EmptyQueueException();
        }
        while (temp.getPrevious().getPrevious() != null){
            temp = temp.getPrevious();
        }
        first = temp;
        first.setPrevious(null);
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
