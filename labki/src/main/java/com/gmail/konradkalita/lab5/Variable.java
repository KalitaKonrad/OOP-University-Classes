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

    String getName() {
        return this.name;
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

    @Override
    Node diff(Variable var) {
        if(var.name.equals(name)) return new Constant(1);
        else return new Constant(0);
    }

    @Override
    boolean isZero(Variable var){
        return !var.getName().equals(name);
    }
}
