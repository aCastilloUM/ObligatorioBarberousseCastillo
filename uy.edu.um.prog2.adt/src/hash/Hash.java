package hash;

import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;

public class Hash<K,V> implements MyHash<K,V> {

    private int size;
    private int capacity;
    private HashNode<K, V> [] table;
    public String firstHash;

    public String remplazo = "no";


    public Hash(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.table = (HashNode<K, V> []) new HashNode[capacity];
    }

    public String getFirstHash() {return firstHash;}
    public String getRemplazo() {return remplazo;}
    public int getSize() {return size;}
    public int getCapacity() {
        return capacity;
    }

    private int hashFunction(K key) {
        // Calcula la suma de los valores ASCII de los caracteres en la clave
        int sum = 0;
        String strKey = key.toString(); //Convertimos la clave a un string
        for (int i = 0; i < strKey.length(); i++) {
            sum += strKey.charAt(i);
        }
        // Toma el módulo del tamaño de la tabla para obtener el índice
        return sum % capacity;
    }

    private int nextPrimeAfter(int n) {
        n = (n % 2 == 0) ? n + 1 : n + 2; // Asegura que el número sea impar
        while (!isPrime(n)) {
            n += 2; // Solo comprueba números impares para mejorar la eficiencia
        }
        return n;
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public void reHash(){
        int newCapacity = nextPrimeAfter(capacity*2);
        HashNode<K,V> [] newTable = new HashNode[newCapacity]; // Crear un nuevo Array con la nueva capacidad

        // Colocar los valores de la tabla anterior en la nueva
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                K key = table[i].getKey();
                V value = table[i].getValue();
                int newIndex = hashFunction(key) % newCapacity; // Recalcula el índice con la nueva capacidad
                while (newTable[newIndex] != null) {
                    newIndex = (newIndex + 1) % newCapacity; // Encuentra el siguiente índice disponible
                }
                newTable[newIndex] = new HashNode<>(key, value);
            }
        }
        // Actualizar los valores de table y capacidad
        table = newTable;
        capacity = newCapacity;
    }

    public void add(K key, V value) {
        // Si la cantidad de elementos de la tabla es mayor al 75% de la capacidad, agrandamos el Hash
        if (size >= capacity * 0.75) {
            reHash();
        }
        key = (K) key.toString().trim(); // Recorta la clave aquí
        int index = hashFunction(key);
        if (size == 0) {
            firstHash = (String) key;
        }
        // Busca un lugar óptimo
        // Si el lugar ya está ocupado por un objeto con la misma clave, reemplaza el objeto
        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + 1) % capacity;

        }

        if (table[index] == null) {
            table[index] = new HashNode<>(key, value);
            size++;
        } else {
            table[index].setValue(value); // Actualiza el valor si la clave ya existe
            remplazo = "si";
        }
    }

    @Override
    public boolean contains(K key) {
        key = (K) key.toString().trim();
        int index = hashFunction(key);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                return true;
            }
            index = (index + 1) % capacity;
            if (index == startIndex) {
                break;
            }
        }
        return false;
    }

    @Override
    public void remove(K key) throws InvalidKeyException {
        int index = hashFunction(key);
        int startIndex =index;
        boolean found = false;

        //Busco el nodo a eliminar
        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                found = true;
                break;
            }
            index = (index + 1) % capacity;
            if (index == startIndex) {
                break;
            }
        }

        // Si el nodo no fue encontrado, lanzar excepción
        if (!found) {
            throw new InvalidKeyException();
        }

        table[index] = null;
        size--;

        // Reinsertar los elementos siguientes para evitar problemas de búsqueda
        index = (index +1 ) % capacity;
        while (table[index] != null) {
            HashNode<K, V> nodeToRehash = table[index];
            table[index] = null;
            size--;  // Se decrementa temporalmente ya que el método add incrementa el tamaño
            add(nodeToRehash.getKey(), nodeToRehash.getValue());
            index = (index + 1) % capacity;
        }

    }

    @Override
    public V get(K key) throws InvalidKeyException, EmptyHashException {
        if (size == 0) {throw new EmptyHashException();}

        int index = hashFunction(key); //Buscamos el valor de la key en HashFunction
        int startIndex = index;

        if (table[index] == null) {
            throw new InvalidKeyException();
        } else {
            while (table[index] != null) {
                if (table[index].getKey().equals(key)) { //Comparamos la key otorgada con la de la tabla
                    return table[index].getValue();
                }
                index = (index + 1) % capacity; //nos movemos 1
                if (index == startIndex) { //Si recorrimos toda la tabla, salimos del bucle
                    break;
                }
            }

        }
        throw new InvalidKeyException();
    }
}
