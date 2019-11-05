package com.gmail.konradkalita.lab5;

public class Main
{
    public static void main(String[] args)
    {
        Variable x = new Variable("x", 1);
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.println(exp.evaluate());
        System.out.println(exp.toString());
    }
}
