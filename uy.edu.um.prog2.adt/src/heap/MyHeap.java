package heap;

import exceptions.EmptyStackException;

public interface MyHeap <K,T> {

    void add(K key, T data);

    T get() throws exceptions.InvalidKeyException.EmptyHeapException;

    void  delete ()throws exceptions.InvalidKeyException.EmptyHeapException;

    boolean contains(K key);

}
