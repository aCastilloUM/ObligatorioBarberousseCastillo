package hash;


public interface MyHash<K,V> {
    public void add(K key, V value);

    public boolean contains(K key);

    public void remove(K key) throws InvalidKey;

    public V get(K key) throws InvalidKey, EmptyHashException;

    public void resize();

}
