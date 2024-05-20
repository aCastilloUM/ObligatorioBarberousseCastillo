package linkedList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    public void addFirstTest() {
        LinkedList<String> lista = new LinkedList<>();
        lista.addFirst("Hola");
        lista.addFirst("Mundo");
        lista.addFirst("Chau");
        assertEquals(3, lista.size);
        assertEquals("Chau", lista.getValueNode(0));
    }

    @Test
    public void addLastTest() {
        LinkedList<String> lista = new LinkedList<>();
        lista.addLast("Hola");
        lista.addLast("Mundo");
        lista.addLast("Chau");
        assertEquals(3, lista.size);
        assertEquals("Hola", lista.getValueNode(0));
    }

    @Test
    public void removeTest() throws EmptyListException {
        LinkedList<String> lista = new LinkedList<>();
        lista.addFirst("Hola");
        lista.addFirst("Mundo");
        lista.addFirst("Chau");

        lista.remove(0);

        assertEquals(2, lista.size);
        assertEquals("Mundo", lista.getValueNode(0));

        lista.remove(0);
        lista.remove(0);
        assertThrows(EmptyListException.class, () -> {
            lista.remove(0);
        });
    }

    @Test
    public void containsTest (){
        LinkedList<String> lista = new LinkedList<>();
        lista.addFirst("Hola");
        lista.addFirst("Mundo");
        lista.addLast("Chau");
        lista.addFirst("Perro");
        lista.addLast("Gato");
        assertTrue(lista.contains("Perro"));
    }


}