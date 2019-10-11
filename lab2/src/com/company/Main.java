package com.company;

public class Main {

    public static void main(String[] args) {
        // Creating Matrix m
        // 1 2 3 4
        // 5 6 0 0
        // 7 8 0 0
        Matrix m = new Matrix(new double[][]{ {1, 2, 3, 4}, {5, 6}, {7, 8} });
        Matrix a = new Matrix(new double[][]{ {2, 3, 1}, {2, -7, 4}} );
        Matrix b = new Matrix(new double[][]{{3, 4, 5}, {1, 1, 4}, {2, 1, 4} });

        // Matrices elemenet by element addition check
        System.out.println(m.add(m).toString());

        // Printing array conversion check
        print2dArray(m.asArray());

        // get/set value check
        System.out.println(m.getValue(3,1));
        m.setValue(3,1,10.0);
        System.out.println(m.getValue(3,1));

        // matrix convert to string check
        System.out.println(m.toString());

        // reshape check
        a.reshape(3, 2);
        System.out.println(a.toString());
        a.reshape(2, 3); // reverting to original size

        // matrices multiplication check
        System.out.println(a.dot(b).toString());

        // forbenius norm check
        System.out.println(a.frobenius());
        System.out.println(a.sub(a).frobenius());
        System.out.println(a.div(a).frobenius());
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

