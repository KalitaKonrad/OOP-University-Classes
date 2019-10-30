package com.gmail.konradkalita.lab5;


abstract public class Node
{
    protected int sign = -1;

    public Node minus() {
        sign = -1;
        return this;
    }

    public Node plus() {
        sign = 1;
        return this;
    }

    public int getArgumentsCount()
    {
        return 0;
    }

    public abstract double evaluate();

    @Override
    public String toString()
    {
        return "";
    }


}
