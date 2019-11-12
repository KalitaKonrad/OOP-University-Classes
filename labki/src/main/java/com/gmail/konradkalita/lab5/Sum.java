package com.gmail.konradkalita.lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sum extends Node
{
    private List<Node> args = new ArrayList<>();

    public Sum() {}

    public Sum(Node ... nodes) {
        args.addAll(Arrays.asList(nodes));
    }

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
        return sign * args.stream().mapToDouble(Node::evaluate).sum();
    }

    @Override
    public int getArgumentsCount()
    {
        return args.size();
    }

    public String toString(){
        if (args.size() == 0) {
            return new Constant(0).toString();
        }

        StringBuilder builder =  new StringBuilder();

        if(sign < 0 )
        {
            builder.append("-(");
        }

        StringJoiner joiner = new StringJoiner(" + ");
        args.stream()
            .filter(node -> !node.toString().equals("0") && !node.toString().isEmpty())
            .forEach(node -> joiner.add(node.toString()));

        builder.append(joiner.toString());

        if(sign < 0 )
        {
            builder.append(")");
        }
        return builder.toString();
    }

    @Override
    Node diff(Variable var) {
        return new Sum(
                args.stream()
                    .filter(node -> !node.isZero(var))
                    .map(node -> node.diff(var))
                    .toArray(Node[]::new));
    }

    @Override
    boolean isZero(Variable var) {
        return args.stream()
                .allMatch(node -> node.isZero(var));
    }
}
