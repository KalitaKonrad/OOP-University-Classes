package com.gmail.konradkalita.lab2_and_3;

import com.gmail.konradkalita.lab4.Document;
import com.gmail.konradkalita.lab4.ParagraphWithList;

public class Main
{
    public static void main(String[] args)
    {

        Matrix m = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}});
        Matrix col = m.getColumn(0);
        Matrix matrix = new Matrix(new double[][]{{1}, {4}, {7}});
        System.out.println(matrix.getCols() + matrix.getRows());
    }
}

