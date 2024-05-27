package heap;

import exceptions.EmptyStackException;

public interface MyHeap <K,T>{

    void add(K key, T data);

    T get() throws exceptions.InvalidKeyException.EmptyHeapException;

    void delete(K key) throws exceptions.InvalidKeyException.EmptyHeapException, EmptyStackException.InvalidKeyException;

    boolean contains(K key);

}
