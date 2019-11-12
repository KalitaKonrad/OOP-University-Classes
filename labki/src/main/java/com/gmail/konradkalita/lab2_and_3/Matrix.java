package com.gmail.konradkalita.lab2_and_3;

public class Matrix
{
    private double[] data;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    public int getRows()
    {
        return rows;
    }

    public int getCols()
    {
        return cols;
    }

    public double[] getData()
    {
        return data;
    }


    // 2.1
    public Matrix(double[][] d)
    {
        int longestRow = 0;
        for (double[] row : d)
        {
            longestRow = Math.max(longestRow, row.length);
        }
        this.cols = longestRow;
        this.rows = d.length;
        data = new double[rows * cols];

        int currentElementPlace = 0;
        for (double[] row : d)
        {
            for (int i = 0; i < longestRow; i++)
            {
                if (i < row.length)
                {
                    data[currentElementPlace] = row[i];
                } else
                {
                    data[currentElementPlace] = 0.0;
                }
                currentElementPlace++;
            }
        }
    }

    // 2.2
    public double[][] asArray()
    {
        double[][] result = new double[rows][cols];

        int currentRow = 0;
        for (int i = 0; i < data.length; i++)
        {
            if (i % cols == 0 && i != 0)
            {
                currentRow++;
                result[currentRow][i % cols] = data[i];
            } else
            {
                result[currentRow][i % cols] = data[i];
            }
        }

        return result;
    }

    //2.3
    public double getValue(int r, int c) throws ArrayIndexOutOfBoundsException
    {
        if (r >= rows || r < 0 || c >= cols || c < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Wrong row or/and column request provided!");
        }
        return data[r * cols + c];
    }

    public void setValue(int r, int c, double value) throws IndexOutOfBoundsException
    {
        if (r >= rows || r < 0 || c >= cols || c < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Wrong row or/and column request provided!");
        }
        data[r * cols + c] = value;
    }

    public void setData(double[] data)
    {
        this.data = data;
    }

    //2.4
    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[");
        for (int i = 0; i < data.length; i++)
        {
            if (i % cols == 0 && i != 0)
            {
                buffer.append("], ");
                buffer.append("[" + data[i]);
                if(this.cols > 1) {
                    buffer.append(", ");
                }
            } else if ((i + 1) % cols == 0)
            {
                buffer.append(data[i]); // no colon since it is the last element in a row
            } else
            {
                buffer.append(data[i] + ", ");
            }
        }
        buffer.append("]");

        return buffer.toString();
    }

    //2.5
    public void reshape(int newRows, int newCols) throws RuntimeException
    {
        if (rows * cols != newRows * newCols)
        {
            throw new RuntimeException(String.format("Wrong provided dimensions!"));
        }

        rows = newRows;
        cols = newCols;
    }

    //2.6
    public int[] shape()
    {
        int[] result = new int[2];
        result[0] = rows;
        result[1] = cols;
        return result;
    }

    //2.7 && 2.8
    public Matrix add(Matrix m)
    {
        return getMatrix(m, "add");
    }

    public Matrix add(double w)
    {
        return getMatrix(w, "add");
    }

    public Matrix sub(Matrix m)
    {
        return getMatrix(m, "sub");
    }

    public Matrix sub(double w)
    {
        return getMatrix(w, "sub");
    }

    public Matrix mul(Matrix m)
    {
        return getMatrix(m, "mul");
    }

    public Matrix mul(double w)
    {
        return getMatrix(w, "mul");
    }

    public Matrix div(Matrix m)
    {
        return getMatrix(m, "div");
    }

    public Matrix div(double w)
    {
        return getMatrix(w, "div");
    }

    private Matrix getMatrix(double w, String action) throws IllegalArgumentException
    {
        int matrixLength = cols * rows;
        Matrix newMatrix = new Matrix(rows, cols);
        double[] currentMatrixData = getData();

        for (int i = 0; i < matrixLength; i++)
        {
            switch (action)
            {
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
                    if (w == 0.0)
                    {
                        throw new IllegalArgumentException("Division by ZERO!");
                    }
                    currentMatrixData[i] /= w;
                    break;
            }
        }

        newMatrix.setData(currentMatrixData);
        return newMatrix;
    }

    private Matrix getMatrix(Matrix m, String action) throws IllegalArgumentException
    {
        if (this.rows != m.getRows() || this.cols != m.getCols())
        {
            throw new IllegalArgumentException("Wrong dimensions of matrix!");
        }

        Matrix newMatrix = new Matrix(rows, cols);
        int matrixLength = rows * cols;

        double[] newMatrixData = newMatrix.getData();
        double[] thisMatrixData = this.getData();
        double[] mMatrixData = m.getData();

        for (int i = 0; i < matrixLength; i++)
        {
            switch (action)
            {
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
                    if (mMatrixData[i] == 0)
                    {
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
    public Matrix dot(Matrix m) throws IllegalArgumentException
    {
        if (this.cols != m.getRows())
        {
            throw new IllegalArgumentException("Wrong Matrix Dimensions!");
        }

        int newMatrixRowsNumber = rows, newMatrixColsNumber = m.getCols();
        Matrix newMatrix = new Matrix(newMatrixRowsNumber, newMatrixColsNumber);

        double currentElement = 0;
        for (int thisMatrixRow = 0; thisMatrixRow < this.rows; thisMatrixRow++)
        {
            for (int mMatrixColumn = 0; mMatrixColumn < m.getCols(); mMatrixColumn++)
            {
                for (int j = 0; j < m.getCols(); j++)
                { // row * column multiplication (to produce single element)
                    currentElement += this.getValue(thisMatrixRow , j) * m.getValue(j, mMatrixColumn);
                }
                newMatrix.setValue(thisMatrixRow, mMatrixColumn, currentElement);
                currentElement = 0;
            }
        }

        return newMatrix;
    }

    //2.10
    public double frobenius()
    {
        double frobeniusNorm = 0;
        double[] matrixData = getData();
        for (int i = 0; i < matrixData.length; i++)
        {
            frobeniusNorm += Math.pow(matrixData[i], 2);
        }
        return frobeniusNorm;
    }

    public Matrix mean(int axis) {
        if (axis == 0) {
            Matrix result = new Matrix(1, this.cols);
            for (int col = 0; col < this.cols; col++) {
                double averagePerColumn = 0;
                for (int row = 0; row < this.rows; row++) {
                    averagePerColumn += this.getValue(row, col);
                }
                result.setValue(0, col, averagePerColumn / (double)this.rows);
            }
            return result;
        }
        else if (axis == 1) {
            Matrix result = new Matrix(this.rows, 1);
            for (int row = 0; row < this.rows; row++) {
                double averagePerRow = 0;
                for (int col = 0; col < this.cols; col++) {
                    averagePerRow += this.getValue(row, col);
                }
                result.setValue(row, 0, averagePerRow / (double)this.cols);
            }
            return result;
        }
        else
            throw new IllegalArgumentException("Wrong axis provided");
    }
}
