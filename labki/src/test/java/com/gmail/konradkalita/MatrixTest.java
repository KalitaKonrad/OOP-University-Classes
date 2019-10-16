package com.gmail.konradkalita;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest
{

    private Matrix matrix;

    @BeforeEach
    void setUp()
    {
        double[][] data = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        matrix = new Matrix(data);
    }

    @Test
    void asArray()
    {
        double[][] expected = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        double[][] actual = matrix.asArray();
        for (int i = 0; i < expected.length; i++)
        {
            assertArrayEquals(expected[i], actual[i]);
        }
    }

    @Test
    void Matrix()
    {
        int rows = 3, cols = 3;
        assertEquals(matrix.getRows(), rows);
        assertEquals(matrix.getCols(), cols);
    }

    @Test
    void getValue()
    {
        assertEquals(matrix.getValue(1, 1), 1);
        assertEquals(matrix.getValue(2, 2), 5);
        assertEquals(matrix.getValue(3, 3), 9);
    }

    @Test
    void setValue()
    {
        double newValue = 5.0;
        matrix.setValue(1, 1, newValue);
        assertEquals(matrix.getValue(1, 1), newValue);
    }

    @Test
    void setData()
    {
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        matrix.setData(expected);
        assertArrayEquals(expected, matrix.getData());
    }

    @Test
    void testToString()
    {
        String expected = "[1.0, 2.0, 3.0], [4.0, 5.0, 6.0], [7.0, 8.0, 9.0]";
        assertEquals(expected, matrix.toString());
    }

    @Test
    void reshapeShouldThrowRuntimeException()
    {
        try
        {
            matrix.reshape(5, 5);
        } catch (RuntimeException e)
        {
            assertEquals("Wrong provided dimensions!", e.getMessage());
        }
    }

    @Test
    void shape()
    {
        int[] expected = {3, 3};
        assertArrayEquals(expected, matrix.shape());
    }

    @Test
    void addMatrixToMatrix()
    {
        Matrix expected = new Matrix(new double[][]{{2, 4, 6}, {8, 10, 12}, {14, 16, 18}});
        matrix = matrix.add(matrix);

        double[] expectedData = expected.getData();

        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void addTypeDoubleToEachMatrixElement()
    {
        Matrix expected = new Matrix(new double[][]{{6, 7, 8}, {9, 10, 11}, {12, 13, 14}});
        matrix.add(5);
        double[] expectedData = expected.getData();
        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void subTypeDoubleToEachMatrixElement()
    {
        matrix.sub(5);
        double[] expectedData = new double[] {-4, -3, -2, -1, 0, 1, 2, 3, 4};
        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void subMatrixFromMatrix()
    {
        matrix = matrix.sub(matrix);
        double[] expectedData = new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void mulTypeDoubleToEachMatrixElement()
    {
        matrix.mul(2);
        double[] expectedData = new double[] {2, 4, 6, 8, 10, 12, 14, 16, 18};
        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void mulEachMatrixElementByCorrespondingMatrixElement()
    {
        double[] expectedData = new double[] {1, 4, 9, 16, 25, 36, 49, 64, 81};
        matrix = matrix.mul(matrix);
        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void divEachMatrixElementByTypeDouble()
    {
        double[] expected = matrix.getData();
        for (int i = 0; i < expected.length; i++)
        {
            expected[i] /= 2;
        }
        matrix.div(2);
        assertArrayEquals(matrix.getData(), expected);
    }

    @Test
    void divisionByZeroDoubleInMatrixShouldThrowIllegalArgumentException()
    {
        try
        {
            matrix.div(0);
        } catch (IllegalArgumentException e)
        {
            assertEquals("Division by ZERO!", e.getMessage());
        }


    }
    @Test
    void divisionByZeroElementInMatrixShouldThrowIllegalArgumentException()
    {
        Matrix test = new Matrix(new double[][] {{1, 1, 1}, {0, 0, 0}, {1, 1, 1}});
        try {
            test.div(test);
        } catch (IllegalArgumentException e)
        {
            assertEquals("Division by ZERO!", e.getMessage());
        }
    }

    @Test
    void dotMatrixByMatrixMultiplication()
    {
        double[] expected = new double[] {30, 36, 42, 66, 81, 96, 102, 126, 150};
        matrix = matrix.dot(matrix);
        assertArrayEquals(expected, matrix.getData());
    }

    @Test
    void frobeniusEveryElementIsSquaredAndAdded() // should I even write this test? It's the same as the function itself.
    {
        double expectedValue = 0;
        double[] matrixData = matrix.getData();
        for (int i = 0; i < matrixData.length; i++)
        {
            expectedValue += Math.pow(matrixData[i], 2);
        }
        assertEquals(expectedValue, matrix.frobenius());
    }
}