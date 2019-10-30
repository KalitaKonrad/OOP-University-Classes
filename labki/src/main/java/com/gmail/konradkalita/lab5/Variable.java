package com.gmail.konradkalita.lab5;

public class Variable extends Node
{
    private String name;
    private double value;

    public Variable(String name)
    {
        this.name = name;
    }

    Variable(String name, double value)
    {
        this.name = name;
        this.value = value;
    }

    void setValue(double d)
    {
        value = d;
    }

    @Override
    public double evaluate()
    {
        return sign * value;
    }

    @Override
    public String toString() {
        String sgn = sign < 0 ? "-" : "";
        return sgn + name;
    }
}
