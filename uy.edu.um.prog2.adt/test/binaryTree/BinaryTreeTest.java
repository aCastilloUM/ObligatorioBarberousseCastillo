package binaryTree;

import static org.junit.jupiter.api.Assertions.*;

import linkedList.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BinaryTreeTest {
    BinaryTree<Integer, String> tree;

    @BeforeEach
    public void initTree() throws InvalidKey {
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
    public void searchTest() throws EmptyTree, InvalidKey {
        assertEquals("C", tree.serch(15));
        assertEquals("D", tree.serch(3));
        assertThrows(InvalidKey.class, () -> {
            tree.serch(100);
        });
    }

    @Test
    public void addTest() throws InvalidKey {
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
    public void deleteTest() throws InvalidKey, EmptyTree {
        tree.delete(15);
        assertThrows(InvalidKey.class, () -> {
            tree.serch(15);
        });

        BinaryTree<Integer, String> emptyTree = new BinaryTree<>();
        assertThrows(EmptyTree.class, () -> {
            emptyTree.delete(10);
        });
    }

    /*
    @Test
    public void inOrderTest() throws EmptyTree {
        LinkedList<Integer> expected = new LinkedList<>(Arrays.asList(3, 5, 7, 10, 12, 15, 20));
        assertEquals(expected, tree.inOrder());
    }

      */

}