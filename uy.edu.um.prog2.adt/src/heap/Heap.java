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
    @Override
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
    @Override
    public T get() throws exceptions.InvalidKeyException.EmptyHeapException {
        if (table.isEmpty()) {
            throw new exceptions.InvalidKeyException.EmptyHeapException();
        }

        HeapNode<K, T> rootNode = table.get(0);
        return rootNode.getData();
    }



    //Este metodo delete, quiero que no reciba Key por parametro sino que borrre la root
    @Override
    public void delete ()throws exceptions.InvalidKeyException.EmptyHeapException {
        if (table.isEmpty()) {
            throw new exceptions.InvalidKeyException.EmptyHeapException();
        }

        // Reemplazo la raíz con el último nodo
        table.set(0, table.get(table.size() - 1));
        table.remove(table.size() - 1);

        // Heapify hacia abajo
        heapifyDown(0);
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
    @Override
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

