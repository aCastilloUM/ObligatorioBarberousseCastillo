package binaryTree;

import static org.junit.jupiter.api.Assertions.*;

import linkedList.EmptyListException;
import linkedList.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BinaryTreeTest {
    BinaryTree<Integer, String> tree;

    @BeforeEach
    public void initTree() throws InvalidKeyException {
        tree = new BinaryTree<>();
        tree.add(10, "A");
        tree.add(5, "B");
        tree.add(15, "C");
        tree.add(3, "D");
        tree.add(7, "E");
        tree.add(12, "F");
        tree.add(20, "G");
    }

    @Test
    public void searchTest() throws EmptyTreeException, InvalidKeyException {
        assertEquals("C", tree.search(15));
        assertEquals("D", tree.search(3));
        assertThrows(InvalidKeyException.class, () -> {
            tree.search(100);
        });
    }

    @Test
    public void addTest() throws InvalidKeyException {
        BinaryTree<Integer, String> emptyTree = new BinaryTree<>();
        emptyTree.add(10, "X");
        assertEquals("X", emptyTree.getRoot().getData());
        assertEquals(10, emptyTree.getRoot().getKey());

        emptyTree.add(5, "Y");
        assertEquals("Y", emptyTree.getRoot().getLeftChild().getData());
        assertEquals(5, emptyTree.getRoot().getLeftChild().getKey());

        emptyTree.add(15, "Y");
        assertEquals("Y", emptyTree.getRoot().getRightChild().getData());
        assertEquals(15, emptyTree.getRoot().getRightChild().getKey());
    }

    @Test
    public void deleteTest() throws InvalidKeyException, EmptyTreeException, EmptyListException {
        tree.delete(15);
        assertThrows(InvalidKeyException.class, () -> {
            tree.search(15);
        });

        BinaryTree<Integer, String> emptyTree = new BinaryTree<>();
        assertThrows(EmptyTreeException.class, () -> {
            emptyTree.delete(10);
        });
    }

    @Test
    public void inOrderTest() throws EmptyTreeException {
        LinkedList<String> expected = new LinkedList<>();

        //Agrego la data de los nodos del binary tree en el orden en que deberian aparecer segun el inOrder
        expected.addLast("D");
        expected.addLast("B");
        expected.addLast("E");
        expected.addLast("A");
        expected.addLast("F");
        expected.addLast("C");
        expected.addLast("G");

        LinkedList<String> actual = tree.inOrder();

        for (int i = 0; i < actual.getSize(); i++){
            assertEquals(expected.getValueNode(i),actual.getValueNode(i));
        }
    }

}