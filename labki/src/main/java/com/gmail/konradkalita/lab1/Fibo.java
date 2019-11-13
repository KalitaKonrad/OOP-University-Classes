package com.gmail.konradkalita.lab1;

import java.util.Scanner;

public class Fibo
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        if (n < 1 || n > 45) {
            return;
        }

        int[] array = new int[n];
        array[0] = 1;
        array[1] = 1;

        for (int i = 2; i < n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }

        for (int x : array) {
            System.out.println(x);
        }
    }
}
