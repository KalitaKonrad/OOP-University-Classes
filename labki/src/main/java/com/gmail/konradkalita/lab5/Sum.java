package com.gmail.konradkalita.lab5;

import java.util.ArrayList;
import java.util.List;

public class Sum extends Node
{
    private List<Node> args = new ArrayList<>();

    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }

    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    public double evaluate() {
        double result = 0;
        args.forEach(Node::evaluate);
        // oblicz sumę wartości zwróconych przez wywołanie evaluate skłądników sumy
        return getSign() * result;
    }

    @Override
    public int getArgumentsCount()
    {
        return args.size();
    }

    public String toString(){
        StringBuilder builder =  new StringBuilder();
        if(getSign() < 0 )
        {
            builder.append("-(");
        }

        //zaimplementuj

        if(getSign() < 0 )
        {
            builder.append(")");
        }

        return builder.toString();
    }
}
