package binaryTree;

import exceptions.EmptyListException;
import linkedList.LinkedList;

public interface MyBinaryTree<K,T> {

    public T search(K key) throws EmptyTreeException, InvalidKeyException;

    public void add(K key, T data) throws InvalidKeyException;

    public void delete(K key) throws InvalidKeyException, EmptyTreeException, EmptyListException;

    LinkedList<T> preOrder() throws EmptyTreeException;

    LinkedList<T> inOrder() throws EmptyTreeException;

    LinkedList<T> postOrder() throws EmptyTreeException;

}
