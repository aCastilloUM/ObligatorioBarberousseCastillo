package hash;


public interface MyHash<K,V> {

    public void add(K key, V value);

    public boolean contains(K key);

    public void remove(K key) throws InvalidKeyException;

    public V get(K key) throws InvalidKeyException, EmptyHashException;

    public void resize();

}
