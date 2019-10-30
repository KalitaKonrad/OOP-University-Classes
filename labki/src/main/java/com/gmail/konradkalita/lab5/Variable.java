package com.gmail.konradkalita.lab5;

public class Variable extends Node
{
    private String name;
    private Double value;
    Variable(String name)
    {
        this.name = name;
    }
    void setValue(double d)
    {
        value = d;
    }

    @Override
    public double evaluate() {
        return getSign() * value;
    }

    @Override
    public String toString() {
        String sgn=getSign() < 0 ? "-" : "";
        return sgn+name;
    }
}
