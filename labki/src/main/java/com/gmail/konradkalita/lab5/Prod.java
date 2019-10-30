package com.gmail.konradkalita.lab5;

import java.util.ArrayList;
import java.util.List;

public class Prod extends Node
{
    private List<Node> args = new ArrayList<>();

    public Prod() {}

    public Prod(Node n1){
        args.add(n1);
    }

    public Prod(double c){
        this(new Constant(c));
    }

    public Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }

    public Prod(double c, Node n){
        this(new Constant(c), n);
    }

    public Prod mul(Node n){
        args.add(n);
        return this;
    }

    public Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }

    @Override
    public double evaluate() {
        double result = 1;
        result *= args.stream()
                .mapToDouble(Node::evaluate)
                .sum();
        // oblicz iloczyn czynników wołąjąc ich metodę evaluate
        return sign * result;
    }

    @Override
    public int getArgumentsCount()
    {
        return args.size();
    }


    public String toString()
    {
        StringBuilder builder =  new StringBuilder();
        if (sign < 0) builder.append("-");
        args.forEach(Node::toString);
        // ...
        return builder.toString();
    }
}
