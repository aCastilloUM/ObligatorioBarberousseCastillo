package linkedList;

public interface MyList<T> {
    void addFirst(T value);
    void addLast (T value);
    void remove(int position);
    T getValueNode(int position);
    boolean contains(T value);
    void cut(int index);
    void printList();
}
