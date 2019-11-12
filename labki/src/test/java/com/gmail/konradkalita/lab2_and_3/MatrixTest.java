package com.gmail.konradkalita.lab2_and_3;

import com.gmail.konradkalita.lab2_and_3.Matrix;
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
    void Should_ReturnMatrixAsArray()
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
    void Should_CreateMatrixWithCertainGivenDimensions()
    {
        int rows = 3, cols = 3;
        assertEquals(matrix.getRows(), rows);
        assertEquals(matrix.getCols(), cols);
    }

    @Test
    void Should_GetMatrixValueFromCertainGivenPosition()
    {
        assertEquals(matrix.getValue(0, 0), 1);
        assertEquals(matrix.getValue(1, 1), 5);
        assertEquals(matrix.getValue(2, 2), 9);
    }

    @Test
    void Should_SetMatrixValueAtCertainGivenPosition()
    {
        double newValue = 5.0;
        matrix.setValue(1, 1, newValue);
        assertEquals(matrix.getValue(1, 1), newValue);
    }

    @Test
    void Should_SetMatrixData()
    {
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        matrix.setData(expected);
        assertArrayEquals(expected, matrix.getData());
    }

    @Test
    void Should_ReturnMatrixConvertedToString()
    {
        String expected = "[1.0, 2.0, 3.0], [4.0, 5.0, 6.0], [7.0, 8.0, 9.0]";
        assertEquals(expected, matrix.toString());
    }

    @Test
    void Should_ThrowRuntimeException_When_WrongDimensionsAreProvided()
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
    void Should_ReturnArrayWithTwoElements_Where_OneElementIsRowCountAndSecondOneIsColumnCount()
    {
        int[] expected = {3, 3};
        assertArrayEquals(expected, matrix.shape());
    }

    @Test
    void Should_ReturnMatrixAddedToItself()
    {
        Matrix expected = new Matrix(new double[][]{{2, 4, 6}, {8, 10, 12}, {14, 16, 18}});
        matrix = matrix.add(matrix);

        double[] expectedData = expected.getData();

        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void Should_AddFiveToEachMatrixElement()
    {
        Matrix expected = new Matrix(new double[][]{{6, 7, 8}, {9, 10, 11}, {12, 13, 14}});
        matrix.add(5);
        double[] expectedData = expected.getData();
        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void Should_SubtractFiveFromEachMatrixElement()
    {
        matrix.sub(5);
        double[] expectedData = new double[] {-4, -3, -2, -1, 0, 1, 2, 3, 4};
        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void Should_ReturnMatrixWithEveryElementSetToZero_When_SubtractingMatrixFromItself()
    {
        matrix = matrix.sub(matrix);
        double[] expectedData = new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void Should_MultiplyEachElementInMatrixByTwo()
    {
        matrix.mul(2);
        double[] expectedData = new double[] {2, 4, 6, 8, 10, 12, 14, 16, 18};
        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void Should_ReturnEachMatrixElementMultipliedByItself()
    {
        double[] expectedData = new double[] {1, 4, 9, 16, 25, 36, 49, 64, 81};
        matrix = matrix.mul(matrix);
        assertArrayEquals(expectedData, matrix.getData());
    }

    @Test
    void Should_ReturnMatrixWhereEachElementDividedByTwodiv()
    {
        double[] expected = matrix.getData();
        for (int i = 0; i < expected.length; i++)
        {
            expected[i] /= 2;
        }
        matrix.div(2);
        assertArrayEquals(matrix.getData(), expected, 1e-7);
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_DividedByZeroInMatrix()
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
    void Should_ThrowIllegalArgumentException_When_DivisionByZeroInMatrixDivision()
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
    void Should_ReturnMatrixMultiplyByItself()
    {
        double[] expected = new double[] {30, 36, 42, 66, 81, 96, 102, 126, 150};
        matrix = matrix.dot(matrix);
        assertArrayEquals(expected, matrix.getData(), 1e-7);
    }

    @Test
    void Should_HaveZeroFrobeniusNorm_When_MatrixSubtractedFromItself()
    {
        assertEquals(0, matrix.sub(matrix).frobenius());
    }

    @Test
    void Should_ReturnMultiplyRowsTimesColumns_When_MatrixDividedByItselfAndFrobeniusNormCounted()
    {
        assertEquals(3*3, matrix.div(matrix).frobenius());
    }

    @Test
    void Should_ReturnSumOfEachColumn_WhenAxisValueIsZero()
    {
        assertEquals("[4.0, 5.0, 6.0]", matrix.mean(0).toString());
    }

    @Test
    void Should_ReturnSumOfEachRow_WhenAxisValueIsOne()
    {
        assertEquals("[2.0], [5.0], [8.0]", matrix.mean(1).toString());
    }

    @Test
    void Should_ThrowIllegalArgumentException_WhenAxisValueIsOtherThanOneOrTwo()
    {
        try {
            matrix.mean(343);
        }
        catch(IllegalArgumentException e) {
            assertEquals("Wrong axis provided", e.getMessage());
        }
    }
}