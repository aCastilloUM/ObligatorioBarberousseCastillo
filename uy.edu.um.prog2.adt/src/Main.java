import linkedlist.MyLinkedListImpl;
import linkedlist.MyList;
import queue.MyQueue;
import stack.EmptyStackException;
import stack.MyStack;

class Persona {
    private String name;

    public Persona(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
public class Main {
    public static void main(String[] args) {
        MyList<Persona> listaDePersonas = new MyLinkedListImpl<>();
        listaDePersonas.add(new Persona("Pedro"));
        String s = new String("hola");

        MyList<String> listaDeNombres = new MyLinkedListImpl<>();
        listaDeNombres.add(s);

        Persona a = listaDePersonas.get(0);


        MyStack<String> pila = new MyLinkedListImpl();

        MyQueue<Persona> cola = new MyLinkedListImpl<>();

        pila.push("hola");
        pila.push("mundo");
        try {
            System.out.println(pila.size());
            String p = pila.pop();
            System.out.println(p);
            System.out.println(pila.size());
        } catch (EmptyStackException e) {
            throw new RuntimeException(e);
        }

    }
}