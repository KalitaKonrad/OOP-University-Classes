package com.company;

public class Matrix {
    double[] data;
    int rows;
    int cols;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    // 2.1
    Matrix(double[][] d) {
        int longestRow = 0;
        for (double[] row : d) {
            longestRow = Math.max(longestRow, row.length);
        }
        this.cols = longestRow;
        this.rows = d.length;
        data = new double[rows * cols];

        int currentElementPlace = 0;
        for (double[] row : d) {
            for (int i = 0; i < longestRow; i++) {
                if (i < row.length) {
                    data[currentElementPlace] = row[i];
                }
                else {
                    data[currentElementPlace] = 0.0;
                }
                currentElementPlace++;
            }
        }
    }

    // 2.2
    public double[][] asArray() {
        double[][] result = new double[rows][cols];

        int currentRow = 0;
        for (int i = 0; i < data.length; i++) {
            if (i % rows == 0 && i != 0) {
                currentRow++;
                result[currentRow][i % rows] = data[i];
            }
            else {
                result[currentRow][i % rows] = data[i];
            }
        }

        return result;
    }

    //2.3
    public double getValue(int r, int c) {
        r--;
        c--;
        return data[r * rows + c];
    }

    public void setValue(int r, int c, double value) {
        r--;
        c--;
        data[r * rows + c] = value;
    }

    //2.4
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[");
        for (int i = 0; i < data.length; i++) {
            if (i % rows == 0 && i != 0) {
                buffer.append("], ");
                buffer.append("[" + data[i] + ", ");
            }
            else if ( (i + 1) % rows == 0) {
                buffer.append(data[i]); // no colon since it is the last element in a row
            }
            else {
                buffer.append(data[i] + ", ");
            }
        }
        buffer.append("]");

        return buffer.toString();
    }

    //2.5
    public void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        }
    }

    //2.6

    //2.7
    public void add(Matrix m) {
        System.out.println(getValue(1,1) + ", " + this.getValue(1,1)); // SAAAY WHAAAT?
    }
}
