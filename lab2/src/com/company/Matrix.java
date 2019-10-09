package com.company;

public class Matrix {
    private double[] data;
    private int rows;
    private int cols;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double[] getData() {
        return data;
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

    public void setData(double[] data) {
        this.data = data;
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
    public int[] shape() {
        int[] result = new int[2];
        result[0] = rows;
        result[1] = cols;
        return result;
    }
    //2.7
    public Matrix add(Matrix m) throws Exception {
        try {
            if (this.rows != m.getRows() || this.cols != m.getCols())  {
                throw new Exception();
            }
        }
       catch(Exception e) {
            e.printStackTrace();
       }
        Matrix newMatrix = new Matrix(rows, cols);

        int matrixLength = rows * cols;
        double[] newMatrixData = newMatrix.getData();
        double[] thisMatrixData = this.getData();
        double[] mMatrixData = m.getData();

        for (int i = 0; i < matrixLength; i++) {
            newMatrixData[i] = thisMatrixData[i] + mMatrixData[i];
        }

        newMatrix.setData(newMatrixData);
        return newMatrix;
    }
}
