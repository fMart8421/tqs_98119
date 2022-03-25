package tqs.lab01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewTqsStackTest {

    private TqsStack<Integer> newStack = new TqsStack();

    @DisplayName("When created, a stack should be empty")
    @Test
    void isEmpty() {
        assertTrue(newStack.isEmpty());
    }

    @DisplayName("When created, the stack's size should be 0")
    @Test
    void sizeAtConstruction() {
        assertEquals(newStack.size(), 0);
    }

    @Test
    void push() {

    }

    @Test
    void peek() {
    }

    @Test
    void pop() {
    }
}
