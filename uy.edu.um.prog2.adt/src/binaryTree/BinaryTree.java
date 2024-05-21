package binaryTree;

import java.util.LinkedList;

public class BinaryTree<K,T> implements MyBinaryTree<K,T>{

    private TreeNode<K,T> root;

    public BinaryTree(){
        this.root = null;
    }

    public TreeNode<K,T> getRoot(){
        return this.root;
    }

    @Override
    public T serch(K key) throws EmptyTree, InvalidKey {
        TreeNode<K,T> res;
        if (this.root != null) {
            res = this.root.findNode(key);
        } else {
            throw new EmptyTree();
        }
        return res.getData();
    }

    public TreeNode<K, T> serchNode(K key) throws EmptyTree, InvalidKey {
        TreeNode<K,T> aux;
        if (this.root != null) {
            aux = this.root.findNode(key);
        } else {
            throw new EmptyTree();
        }
        return aux;
    }

    @Override
    public void add(K key, T data) throws InvalidKey {
        TreeNode<K,T> add = new TreeNode<>(key, data);
        TreeNode<K,T> parent;
        if (this.root == null){
            this.root = add;
        }
        else {
            parent=this.root.findFreeParent(key);
            if (parent.compareTo(key)>0){
                parent.setLeftChild(add);
            } else {
                parent.setRightChild(add);
            }
        }

    }

    public void addNode(TreeNode<K, T> add) throws InvalidKey {
        if (this.root == null) {
            this.root = add;
        } else {
            TreeNode<K,T> parent = this.root.findFreeParent(add.getKey());
            if (parent.compareTo(add.getKey()) > 0) {
                parent.setLeftChild(add);
            } else {
                parent.setRightChild(add);
            }
        }
    }

    @Override
    public void delete(K key) throws InvalidKey, EmptyTree {
        if (this.root != null) {
            TreeNode<K,T> del = this.serchNode(key);
            TreeNode<K,T> parent = this.root.getParent(key);
            if (parent.getLeftChild() == del) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
            LinkedList<TreeNode<K,T>> list = new LinkedList<TreeNode<K, T>>();
            del.getChildList(list);
            list.remove();
            for (int i = 0; i < list.size(); i ++) {
                list.get(i).setRightChild(null);
                list.get(i).setLeftChild(null);
                this.addNode(list.get(i));
            }
        } else {
            throw new EmptyTree();
        }
    }

    public void inOrder() throws EmptyTree{
        LinkedList<T> list = new LinkedList<>();
        if (this.root != null) {
            this.root.inOrder(list);
        } else {
            throw new EmptyTree();
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == this.root.getData()) {
                System.out.print(list.get(i) + " [R] ");
            } else {
                System.out.print(list.get(i) + " ");
            }
        }
        System.out.println(" ");
    }
    public void preOrder() throws EmptyTree{
        LinkedList<T> list = new LinkedList<>();
        if (this.root != null) {
            this.root.preOrder(list);
        } else {
            throw new EmptyTree();
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == this.root.getData()) {
                System.out.print(list.get(i) + " [R] ");
            } else {
                System.out.print(list.get(i)+ " ");
            }
        }
        System.out.println(" ");
    }
    public void postOrder() throws EmptyTree{
        LinkedList<T> list = new LinkedList<>();
        if (this.root != null) {
            this.root.postOrder(list);
        } else {
            throw new EmptyTree();
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == this.root.getData()) {
                System.out.println(list.get(i) + " [R] ");
            } else {
                System.out.print(list.get(i)+ " ");
            }
        }
        System.out.println(" ");
    }
}
