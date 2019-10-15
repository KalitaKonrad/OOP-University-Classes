package com.gmail.konradkalita;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private Matrix matrix;

    @BeforeEach
    void setUp() {
        double[][] data = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        matrix = new Matrix(data);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void asArray() {
        double[][] expected = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        double[][] actual = matrix.asArray();
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i]);
        }
    }

    @Test
    void Matrix() {
        int rows = 3, cols = 3;
        assertEquals(matrix.getRows(), rows);
        assertEquals(matrix.getCols(), cols);
    }

    @Test
    void getValue() {
        assertEquals(matrix.getValue(1, 1), 1);
        assertEquals(matrix.getValue(2, 2), 5);
        assertEquals(matrix.getValue(3, 3), 9);
    }

    @Test
    void setValue() {
        double newValue = 5.0;
        matrix.setValue(1, 1, newValue);
        assertEquals(matrix.getValue(1, 1), newValue);
    }

    @Test
    void setData() {
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        matrix.setData(expected);
        assertArrayEquals(expected, matrix.getData());
    }

    @Test
    void testToString() {
        String expected = "[1.0, 2.0, 3.0], [4.0, 5.0, 6.0], [7.0, 8.0, 9.0]";
        assertEquals(expected, matrix.toString());
    }

    @Test
    void reshape() {

    }

    @Test
    void shape() {
    }

    @Test
    void add() {
    }

    @Test
    void add1() {
    }

    @Test
    void sub() {
    }

    @Test
    void sub1() {
    }

    @Test
    void mul() {
    }

    @Test
    void mul1() {
    }

    @Test
    void div() {
    }

    @Test
    void div1() {
    }

    @Test
    void dot() {
    }

    @Test
    void frobenius() {
    }
}