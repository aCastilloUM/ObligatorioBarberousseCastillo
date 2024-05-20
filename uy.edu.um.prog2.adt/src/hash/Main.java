package hash;

public class Main {
    public static void main(String[] args) throws InvalidKey, EmptyHashException {
        Hash hola = new Hash<>(40);
        hola.add("4",3);
        hola.add(3,2);
        hola.add("2",5);
        hola.add("1",6);
        hola.add(15,878);

        System.out.println(hola.contains(3));
        hola.remove(3);
        hola.remove("2");
        System.out.println(hola.contains(3));

    }
}
