package com.gmail.konradkalita.lab5;


abstract public class Node
{
    private int sign;
    public abstract double evaluate();
    public abstract String toString();
    public int getSign(){ return sign; }
    public int getArgumentsCount()
    {
        return 0;
    }
}
