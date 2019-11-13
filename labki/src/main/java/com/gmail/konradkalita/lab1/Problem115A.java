package com.gmail.konradkalita.lab1;

import java.util.Scanner;

public class Problem115A
{
    public static void main(String[] args)
    {
        solution();
    }

    private static void solution() {
        Scanner scanner = new Scanner(System.in);
        int workersCount = scanner.nextInt();

        int answer = 0;
        int count = 0;

        int[] workers = new int[workersCount + 9];

        for (int i = 1; i < workersCount + 1; i++) {
            workers[i] = scanner.nextInt();
        }

        for (int i = 1; i < workersCount + 1; i++) {
            count = 0;
            int worker = workers[i];

            while (worker != -1) {
                worker = workers[worker];
                count++;
            }

            answer = Math.max(answer, count);
        }

        System.out.println(answer + 1);
    }
}
