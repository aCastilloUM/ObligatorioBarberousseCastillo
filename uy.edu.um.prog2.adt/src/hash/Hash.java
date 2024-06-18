package hash;

import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;

public class Hash<K, V> implements MyHash<K, V> {

    private int size;
    private int capacity;
    private HashNode<K, V>[] table;
    private String firstHash;
    private String replacement = "no";

    public Hash(int initialCapacity) {
        this.size = 0;
        this.capacity = initialCapacity;
        this.table = new HashNode[capacity];
    }

    public String getFirstHash() {
        return firstHash;
    }

    public String getReplacement() {
        return replacement;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashNode<K, V>[] getTable() {
        return table;
    }

    private int hashFunction(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    private int nextPrimeAfter(int n) {
        if (n % 2 == 0) {
            n++;
        }
        while (!isPrime(n)) {
            n += 2;
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

    public void reHash() {
        int newCapacity = nextPrimeAfter(capacity * 2);
        HashNode<K, V>[] newTable = new HashNode[newCapacity];

        for (HashNode<K, V> node : table) {
            if (node != null) {
                int newIndex = Math.abs(node.getKey().hashCode()) % newCapacity;
                while (newTable[newIndex] != null) {
                    newIndex = (newIndex + 1) % newCapacity;
                }
                newTable[newIndex] = node;
            }
        }

        table = newTable;
        capacity = newCapacity;
    }

    @Override
    public void add(K key, V value) {
        if (size >= capacity * 0.75) {
            reHash();
        }

        String keyStr = key.toString().trim();
        int index = hashFunction((K) keyStr);

        if (size == 0) {
            firstHash = keyStr;
        }

        while (table[index] != null && !table[index].getKey().equals(keyStr)) {
            index = (index + 1) % capacity;
        }

        if (table[index] == null) {
            table[index] = new HashNode<>(key, value);
            size++;
        } else {
            table[index].setValue(value);
            replacement = "si";
        }
    }

    @Override
    public boolean contains(K key) {
        String keyStr = key.toString().trim();
        int index = hashFunction((K) keyStr);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].getKey().equals(keyStr)) {
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
        String keyStr = key.toString().trim();
        int index = hashFunction((K) keyStr);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].getKey().equals(keyStr)) {
                table[index] = null;
                size--;
                reinsertFollowingNodes(index);
                return;
            }
            index = (index + 1) % capacity;
            if (index == startIndex) {
                break;
            }
        }
        throw new InvalidKeyException();
    }

    private void reinsertFollowingNodes(int startIndex) {
        int index = (startIndex + 1) % capacity;
        while (table[index] != null) {
            HashNode<K, V> nodeToRehash = table[index];
            table[index] = null;
            size--;
            add(nodeToRehash.getKey(), nodeToRehash.getValue());
            index = (index + 1) % capacity;
        }
    }

    @Override
    public V get(K key) throws InvalidKeyException, EmptyHashException {
        if (size == 0) {
            throw new EmptyHashException();
        }

        String keyStr = key.toString().trim();
        int index = hashFunction((K) keyStr);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].getKey().equals(keyStr)) {
                return table[index].getValue();
            }
            index = (index + 1) % capacity;
            if (index == startIndex) {
                break;
            }
        }
        throw new InvalidKeyException();
    }
}
