package heap;

public class HeapNode <K,T> implements Comparable<K>{
    private T data;
    private K key;

    public HeapNode(K key, T data){
        this.data = data;
        this.key = key;
    }

    @Override
    public int compareTo(K otherKey) {
        return (int)(this.key) - (int)(otherKey);
    }

    public T getData() {
        return data;
    }

    public K getKey() {
        return key;
    }

}
