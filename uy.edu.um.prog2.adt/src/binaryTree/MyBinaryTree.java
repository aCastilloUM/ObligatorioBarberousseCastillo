package binaryTree;

public interface MyBinaryTree<K,T> {

    public T serch(K key) throws EmptyTree, InvalidKey;

    public void add(K key, T data) throws InvalidKey;

    public void delete(K key) throws InvalidKey, EmptyTree;

    void preOrder() throws EmptyTree;

    void inOrder() throws EmptyTree;

    void postOrder() throws EmptyTree;


}
