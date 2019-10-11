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
            if (i % cols == 0 && i != 0) {
                currentRow++;
                result[currentRow][i % cols] = data[i];
            }
            else {
                result[currentRow][i % cols] = data[i];
            }
        }

        return result;
    }

    //2.3
    public double getValue(int r, int c) throws ArrayIndexOutOfBoundsException {
        if (r > rows || r < 1 || c > cols || c < 1) {
            throw new ArrayIndexOutOfBoundsException("Wrong row or/and column request provided!");
        }
        r--;
        c--;
        return data[r * cols + c];
    }

    public void setValue(int r, int c, double value) throws IndexOutOfBoundsException {
        if (r > rows || r < 1 || c > cols || c < 1) {
            throw new ArrayIndexOutOfBoundsException("Wrong row or/and column request provided!");
        }
        r--;
        c--;
        data[r * cols + c] = value;
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
            if (i % cols == 0 && i != 0) {
                buffer.append("], ");
                buffer.append("[" + data[i] + ", ");
            }
            else if ( (i + 1) % cols == 0) {
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

    //2.7 && 2.8
    public Matrix add(Matrix m) {
        return getMatrix(m, "add");
    }

    public Matrix add(double w) {
        return getMatrix(w, "add");
    }

    public Matrix sub(Matrix m) {
        return getMatrix(m, "sub");
    }

    public Matrix sub(double w) {
        return getMatrix(w, "sub");
    }

    public Matrix mul(Matrix m) {
        return getMatrix(m, "mul");
    }

    public Matrix mul(double w) {
        return getMatrix(w, "mul");
    }

    public Matrix div(Matrix m) {
        return getMatrix(m, "div");
    }

    public Matrix div(double w) {
        return getMatrix(w, "div");
    }

    private Matrix getMatrix(double w, String action) throws IllegalArgumentException {
        int matrixLength = cols * rows;
        Matrix newMatrix = new Matrix(rows, cols);
        double[] currentMatrixData = getData();

        for (int i = 0; i < matrixLength; i++) {
            switch (action) {
                case "add":
                    currentMatrixData[i] += w;
                    break;
                case "sub":
                    currentMatrixData[i] -= w;
                    break;
                case "mul":
                    currentMatrixData[i] *= w;
                    break;
                case "div":
                    if (w == 0.0) {
                        throw new IllegalArgumentException("Division by ZERO!");
                    }
                    currentMatrixData[i] /= w;
                    break;
             }
        }

        newMatrix.setData(currentMatrixData);
        return newMatrix;
    }

    private Matrix getMatrix(Matrix m, String action) throws IllegalArgumentException {
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
            switch (action) {
                case "add":
                    newMatrixData[i] = thisMatrixData[i] + mMatrixData[i];
                    break;
                case "sub":
                    newMatrixData[i] = thisMatrixData[i] - mMatrixData[i];
                    break;
                case "mul":
                    newMatrixData[i] = thisMatrixData[i] * mMatrixData[i];
                    break;
                case "div":
                    if (mMatrixData[i] == 0) {
                        throw new IllegalArgumentException("Division by ZERO!");
                    }
                    newMatrixData[i] = thisMatrixData[i] / mMatrixData[i];
                    break;
            }
        }

        newMatrix.setData(newMatrixData);
        return newMatrix;
    }

    // 2.9
    public Matrix dot(Matrix m) throws IllegalArgumentException {
        try {
            if (this.cols != m.getRows()) {
                throw new IllegalArgumentException("Wrong Matrix Dimensions!");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        int newMatrixRowsNumber = rows, newMatrixColsNumber = m.getCols();
        Matrix newMatrix = new Matrix(newMatrixRowsNumber, newMatrixColsNumber);

        System.out.println(newMatrixRowsNumber);
        System.out.println(newMatrixColsNumber);
        double currentElement = 0;
        for (int thisMatrixRow = 0; thisMatrixRow < this.rows; thisMatrixRow++) {
            for (int mMatrixColumn = 0; mMatrixColumn < m.getCols(); mMatrixColumn++) {
                for (int j = 0; j < m.getCols(); j++) { // row * column multiplication (to produce single element)
                    currentElement += this.getValue(thisMatrixRow + 1, j + 1) * m.getValue(j + 1, mMatrixColumn + 1);
                }
                newMatrix.setValue(thisMatrixRow + 1, mMatrixColumn + 1, currentElement);
                currentElement = 0;
            }
        }

        return newMatrix;
    }
}
