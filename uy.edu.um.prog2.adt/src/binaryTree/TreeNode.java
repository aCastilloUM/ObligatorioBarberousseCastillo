package binaryTree;

import linkedList.LinkedList;

public class TreeNode<K,T> implements Comparable<K>{

    private T data;
    private K key;
    private TreeNode<K,T> leftChild;
    private TreeNode<K,T> rightChild;

    public TreeNode(K key, T data) {
        this.data = data;
        this.key = key;
    }

    @Override
    public int compareTo(K otherKey) {
        return (int)(this.key) - (int)(otherKey);
    }
    //Si return es menor a 0, significa que this.key<otherKey
    //Si return es mayor a 0, significa que this.key>otherKey
    //Si return es igual a 0, significa que son iguales

    public K getKey() {
        return this.key;
    }

    public T getData() {
        return this.data;
    }

    public TreeNode<K, T> getLeftChild() {
        return this.leftChild;
    }

    public TreeNode<K,T> getRightChild() {
        return this.rightChild;
    }

    public void setLeftChild(TreeNode<K, T> left) {
        this.leftChild = left;
    }

    public void setRightChild(TreeNode<K,T> right) {
        this.rightChild = right;
    }

    public TreeNode<K, T> findNode(K key) throws InvalidKeyException {
        TreeNode<K,T> aux = null;
        if (this.key == key) {
            aux = this;
        } else {
            if (this.compareTo(key) > 0) {
                if (this.leftChild != null) {
                    aux = this.leftChild.findNode(key);
                }
            } else {
                if (this.rightChild != null) {
                    aux = this.rightChild.findNode(key);
                }
            }
        }
        if (aux == null) {
            throw new InvalidKeyException();
        }
        return aux;
    }

    public TreeNode<K, T> findFreeParent(K key) throws InvalidKeyException {
        //Este metodo nos devuelve un padre libre para insertar
        TreeNode<K,T> parent = null;
        if (this.compareTo(key) > 0) { //Busca en que parte del arbol tiene que estar el padre con respecto a la raiz
            if (this.leftChild == null) {
                parent = this;
            } else {
                parent = this.leftChild.findFreeParent(key);
            }
        } else if (this.compareTo(key) <= 0) {
            if (this.rightChild == null) {
                parent = this;
            } else {
                parent = this.rightChild.findFreeParent(key);
            }
        } else {
            throw new InvalidKeyException();
        }

        return parent;
    }

    public void getChildList(LinkedList<TreeNode<K,T>> list) {
        list.addLast(this);
        if (this.leftChild != null) {
            this.leftChild.getChildList(list);
        }
        if (this.rightChild != null) {
            this.rightChild.getChildList(list);
        }
    }

    public TreeNode<K,T> getParent(K key) throws InvalidKeyException {
        TreeNode<K,T> parent = null;
        if (this.leftChild != null && this.leftChild.key == key) {
            parent = this;
        }
        if (this.rightChild != null && this.rightChild.key == key) {
            parent = this;
        }

        if (parent == null) {
            if (this.compareTo(key) > 0) {
                if (this.leftChild != null) {
                    parent = this.leftChild.getParent(key);
                }
            } else {
                if (this.rightChild != null) {
                    parent = this.rightChild.getParent(key);
                }
            }
        }
        if (parent == null) {
            throw new InvalidKeyException();
        }
        return parent;
    }

    public void inOrder(LinkedList<T> list) {
        if (this.leftChild != null) {
            this.leftChild.inOrder(list);
        }
        list.addLast(this.data);
        if (this.rightChild != null) {
            this.rightChild.inOrder(list);
        }
    }

    public void preOrder(LinkedList<T> list) {
        list.addLast(this.data);
        if (this.leftChild != null) {
            this.leftChild.preOrder(list);
        }
        if (this.rightChild != null) {
            this.rightChild.preOrder(list);
        }
    }

    public void postOrder(LinkedList<T> list) {
        if (this.leftChild != null) {
            this.leftChild.postOrder(list);
        }
        if (this.rightChild != null) {
            this.rightChild.postOrder(list);
        }
        list.addLast(this.data);
    }
}
