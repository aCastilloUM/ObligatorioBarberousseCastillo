package heap;

import exceptions.EmptyStackException;

import java.util.ArrayList;

public class Heap<K,T> implements MyHeap<K,T>{

    public HeapNode<K,T> root;

    public ArrayList<HeapNode<K,T>> table;


    public Heap(){
        this.root = null;
        this.table = new ArrayList<>();
    }

    public HeapNode<K, T> getRoot() {
        return root;
    }

    public int getSize(){
        return this.table.size();
    }

    public ArrayList<HeapNode<K, T>> getTable() {
        return table;
    }

    public void add(K key, T data){
        HeapNode<K, T> newNode = new HeapNode<>(key, data);
        table.add(newNode);
        heapifyUp(table.size() - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            HeapNode<K, T> node = table.get(index);
            HeapNode<K, T> parent = table.get(parentIndex);

            if (node.compareTo(parent.getKey()) > 0) {
                // Cambio el padre con el hijo
                table.set(index, parent);
                table.set(parentIndex, node);

                //Me muevo al indice del padre
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public T get() throws exceptions.InvalidKeyException.EmptyHeapException {
        if (table.isEmpty()) {
            throw new exceptions.InvalidKeyException.EmptyHeapException();
        }

        HeapNode<K, T> rootNode = table.get(0);
        return rootNode.getData();
    }


    public void delete (K key) throws exceptions.InvalidKeyException.EmptyHeapException, EmptyStackException.InvalidKeyException {
        if (table.isEmpty()) {
            throw new exceptions.InvalidKeyException.EmptyHeapException();
        }

        // Buscamos el elemento en el heap
        int indexToDelete = -1;
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).getKey().equals(key)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            throw new EmptyStackException.InvalidKeyException();
        }

        // Cambiamos el elemento a eliminar con el ultimo del heap
        int lastIndex = table.size() - 1;
        table.set(indexToDelete, table.get(lastIndex));
        table.remove(lastIndex);

        // Acomodamos el heap
        heapifyDown(indexToDelete);
    }

    private void heapifyDown(int index) {
        int size = table.size();
        while (index < size) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int largest = index;


            //Aca comparo a ver si los hijos son mas grandes que el padre
            if (leftChildIndex < size && table.get(leftChildIndex).compareTo(table.get(largest).getKey()) > 0) {
                largest = leftChildIndex;
            }
            if (rightChildIndex < size && table.get(rightChildIndex).compareTo(table.get(largest).getKey()) > 0) {
                largest = rightChildIndex;
            }

            // Intercambio el nodo actual con el más grande de sus hijos
            if (largest != index) {
                HeapNode<K, T> temp = table.get(index);
                table.set(index, table.get(largest));
                table.set(largest, temp);

                // Muevo el inidice para seguir acomodando el nodo original
                index = largest;
            } else {
                break;
            }
        }
    }

    public boolean contains (K key){
        boolean exist = false;
        for (int i =0 ; i < this.table.size(); i++){
            if (table.get(i).getKey() == key){
                exist = true;
                return exist;
            }
        }
        return exist;
    }
}

