package com.gmail.konradkalita.lab1;

public class Problem610A
{
    public static void main(String[] args)
    {
        System.out.println(solution(20));
    }

    private static int solution(int n) {
        return n < 4 ? -1 : (n - 1) / 4;
    }
}
