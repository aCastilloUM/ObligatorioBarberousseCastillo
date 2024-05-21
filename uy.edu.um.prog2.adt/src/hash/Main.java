package hash;

public class Main {
    public static void main(String[] args) throws InvalidKeyException, EmptyHashException {
        Hash hola = new Hash<>(10);
        hola.add(5, 3);
        hola.add(3, 2);
        hola.add(5, 3);
        hola.add(3, 2);
        hola.add(5, 3);
        hola.add(3, 2);
        hola.add(5, 3);
        hola.add(3, 2);
        hola.add(5,1);
        System.out.println(hola.getCapacity());
    }
}
