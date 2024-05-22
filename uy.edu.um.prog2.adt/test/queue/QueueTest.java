package queue;

import exceptions.EmptyQueueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    Queue<String> queue;

    @BeforeEach
    public void iniQueue() throws EmptyQueueException {
        queue = new Queue<>();
        queue.enqueue("Hola");
        queue.enqueue("Mundo");
        queue.enqueue("Chau");
        assertTrue(queue.contains("Hola"));
    }

    @Test
    public void enqueueTest() throws EmptyQueueException {
        queue.enqueue("Perro");
        assertTrue(queue.contains("Perro"));

        assertEquals(4, queue.size);
    }

    @Test
    public void dequeueTest() throws EmptyQueueException {
        assertTrue(queue.contains("Hola"));
        assertTrue(queue.contains("Chau"));
        assertFalse(queue.contains("Pepe"));

        queue.dequeue();
        assertEquals(2, queue.size);

        queue.dequeue();
        assertEquals(1, queue.size);

        queue.dequeue();
        assertTrue(queue.isEmpty());

        assertThrows(EmptyQueueException.class, () -> {
            queue.dequeue();
        });
    }
}