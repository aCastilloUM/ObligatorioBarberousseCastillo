package stack;

public interface MyStack <T>{

    void push(T value);

    void pop() throws EmptyStackException;

    T peek() throws EmptyStackException;

    boolean isEmpty();

    void printStack();

    boolean containsStack(T value);
}
