package com.company;

public class Main {

    public static void main(String[] args) {
        Matrix m = new Matrix(new double[][]{{1,2,3,4}, {5,6}, {7,8}, {9}});

        print2dArray(m.asArray());

        System.out.println(m.getValue(4,1));
        m.setValue(4,1,10.0);
        System.out.println(m.getValue(4,1));

        System.out.println(m.toString());

//        m.reshape(4,5);
        m.add(m);
    }


    private static void print2dArray(double[][] array) {
        for (double[] row : array) {
            for (double element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

}

