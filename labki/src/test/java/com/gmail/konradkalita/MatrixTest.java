package com.gmail.konradkalita;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @BeforeEach
    void setUp() {
        double[][] data = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(data);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void asArray() {
    }

    @Test
    void getValue() {
    }

    @Test
    void setValue() {
    }

    @Test
    void setData() {
    }

    @Test
    void toString1() {
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