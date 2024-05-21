package linkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    LinkedList<String> lista;

    @BeforeEach
    public void iniList() {
        lista = new LinkedList<>();
        lista.addFirst("Hola");
        lista.addFirst("Mundo");
        lista.addFirst("Chau");
    }
    @Test
    public void addFirstTest() {
        assertEquals(3, lista.size);
        assertEquals("Chau", lista.getValueNode(0));
    }

    @Test
    public void addLastTest() {
        lista = new LinkedList<>();
        lista.addLast("Hola");
        lista.addLast("Mundo");
        lista.addLast("Chau");
        assertEquals(3, lista.size);
        assertEquals("Hola", lista.getValueNode(0));
    }

    @Test
    public void removeTest() throws EmptyListException {
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
        lista.addFirst("Perro");
        lista.addLast("Gato");
        assertTrue(lista.contains("Perro"));
    }

    @Test
    public void cutTest() {
        lista.addFirst("Animales");
        lista.addFirst("Objetos");
        lista.addFirst("Frutas");

        assertEquals(6,lista.size);
        lista.cut(4);
        assertEquals(4, lista.size);
    }
}