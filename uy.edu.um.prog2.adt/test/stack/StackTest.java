package stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queue.EmptyQueueException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    Stack<String> stack;

    @BeforeEach
    public void iniStack(){
        stack = new Stack<>();
        stack.push("Hola");
        stack.push("Mundo");
        stack.push("UM");
        stack.push("Java");
    }

    @Test
    public void pushTest() throws EmptyStackException {
        assertEquals(4, stack.size);
        assertEquals("Java", stack.peek());
    }

    @Test
    public void popTest() throws EmptyStackException {
        stack.pop();
        assertEquals(3, stack.size);
        assertEquals("UM", stack.peek());

        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());

        assertThrows(EmptyStackException.class, () -> {
            stack.pop();
        });
    }

    @Test
    public void containsTest() throws EmptyStackException {
        stack.push("Perro");
        stack.push("Gato");
        stack.push("Azul");

        assertEquals(7, stack.size);

        assertTrue(stack.containsStack("UM"));
        assertTrue(stack.containsStack("Gato"));

        assertTrue(stack.containsStack("Azul"));
        stack.pop();
        assertFalse(stack.containsStack("Azul"));
    }

}