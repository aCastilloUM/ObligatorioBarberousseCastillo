package stack;

public class StackNode<T> {
    private T value;
    private StackNode<T> prev;

    public StackNode(T value){
        this.value=value;
        this.prev=null;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public void setPrev(StackNode<T> prev) {
        this.prev = prev;
    }


    public T getValue() {
        return value;
    }

    public StackNode<T> getPrev() {
        return prev;
    }

}
