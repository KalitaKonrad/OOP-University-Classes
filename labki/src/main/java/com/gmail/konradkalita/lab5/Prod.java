package com.gmail.konradkalita.lab5;

import java.util.ArrayList;
import java.util.List;

public class Prod extends Node
{
    private List<Node> args = new ArrayList<>();

    Prod(Node n1){
        args.add(n1);
    }

    Prod(double c){
        this(new Constant(c));
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }

    Prod(double c, Node n){
        this(new Constant(c), n);
    }

    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }


    @Override
    public double evaluate() {
        double result = 1;
        args.forEach(Node::evaluate);
        // oblicz iloczyn czynników wołąjąc ich metodę evaluate
        return getSign() * result;
    }

    @Override
    public int getArgumentsCount()
    {
        return args.size();
    }


    public String toString()
    {
        StringBuilder builder =  new StringBuilder();
        if (getSign() < 0) builder.append("-");
        // ...
        return builder.toString();
    }


}
