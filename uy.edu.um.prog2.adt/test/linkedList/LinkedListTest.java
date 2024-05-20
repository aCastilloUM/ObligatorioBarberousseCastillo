package linkedList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    public void addTest() throws EmptyListException {
        LinkedList<String> lista = new LinkedList<>();
        lista.addFirst("Hola");
        lista.addFirst("Mundo");
        lista.addFirst("Chau");

        assertEquals(3, lista.size);

        lista.remove(0);

        assertEquals(2, lista.size);
        assertEquals("Mundo", lista.getValueNode(0));

    }

}