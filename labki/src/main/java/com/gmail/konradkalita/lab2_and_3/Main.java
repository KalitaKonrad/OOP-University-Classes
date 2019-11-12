package com.gmail.konradkalita.lab2_and_3;

import com.gmail.konradkalita.lab4.Document;
import com.gmail.konradkalita.lab4.ParagraphWithList;

public class Main
{
    public static void main(String[] args)
    {
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix row = m.mean(1);
        System.out.println(row.toString());
    }
}

