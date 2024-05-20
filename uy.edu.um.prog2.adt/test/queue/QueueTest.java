package queue;

import linkedList.EmptyListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    Queue<String> queue;

    @BeforeEach
    public void iniQueue(){
        queue = new Queue<>();
        queue.enqueue("Hola");
        queue.enqueue("Mundo");
        queue.enqueue("Chau");
    }

    @Test
    public void enqueueTest(){
        queue.enqueue("Perro");

        assertEquals(4, queue.size);
    }

    @Test
    public void dequeueTest() throws EmptyQueueException {
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