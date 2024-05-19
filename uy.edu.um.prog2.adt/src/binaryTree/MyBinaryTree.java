package binaryTree;

public interface MyBinaryTree<K,T> {

    public T serch(K key) throws EmptyTree, InvalidKey;

    TreeNode<K, T> serchNode(K key) throws EmptyTree, InvalidKey;

    public void add(K key, T data) throws InvalidKey;

    void addNode(TreeNode<K, T> add) throws InvalidKey;

    public void delete(K key) throws InvalidKey, EmptyTree;



}
