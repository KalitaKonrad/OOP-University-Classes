package com.gmail.konradkalita.lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sum extends Node
{
    private List<Node> args = new ArrayList<>();

    public Sum() {}
    public Sum(Node n1, Node n2){
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
        result += args.stream()
                .mapToDouble(Node::evaluate)
                .sum();
        // oblicz sumę wartości zwróconych przez wywołanie evaluate skłądników sumy ???????
        return sign * result;
    }

    @Override
    public int getArgumentsCount()
    {
        return args.size();
    }

    public String toString(){
        StringBuilder builder =  new StringBuilder();
        if(sign < 0 )
        {
            builder.append("-(");
        }
        builder.append(
                args.stream()
                    .map(Node::toString)
                        .collect(Collectors.joining()));
        //zaimplementuj ???????????

        if(sign < 0 )
        {
            builder.append(")");
        }
        return builder.toString();
    }
}
