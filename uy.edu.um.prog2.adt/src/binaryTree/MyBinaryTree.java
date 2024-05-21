package binaryTree;

import linkedList.EmptyListException;
import linkedList.LinkedList;

public interface MyBinaryTree<K,T> {

    public T serch(K key) throws EmptyTree, InvalidKey;

    public void add(K key, T data) throws InvalidKey;

    public void delete(K key) throws InvalidKey, EmptyTree, EmptyListException;

    LinkedList<T> preOrder() throws EmptyTree;

    LinkedList<T> inOrder() throws EmptyTree;

    LinkedList<T> postOrder() throws EmptyTree;

}
