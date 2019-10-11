package com.company;

public class Main {

    public static void main(String[] args) {
        Matrix m = new Matrix(new double[][]{{1,2,3,4}, {5,6}, {7,8}});

        try {
            System.out.println(m.add(m).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        print2dArray(m.asArray());

        System.out.println(m.getValue(3,1));
        m.setValue(3,1,10.0);
        System.out.println(m.getValue(3,1));

        System.out.println(m.toString());
        //System.out.println(m.div(m));
//        m.reshape(4,5);

        Matrix A = new Matrix(new double[][]{{2,3,1}, {2,-7,4}});
        Matrix B = new Matrix(new double[][]{{3,4,5}, {1,1,4}, {2,1,4}});
        System.out.println(A.dot(B).toString());
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

