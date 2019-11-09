package com.gmail.konradkalita.lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return sign * args.stream()
                .mapToDouble(Node::evaluate)
                .reduce(1, (acc, current) -> acc * current);
    }

    @Override
    public int getArgumentsCount()
    {
        return args.size();
    }


    public String toString()
    {
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-");
        for(Node arg: args) {
            int argSign = arg.getSign();
            int cnt = arg.getArgumentsCount();
            boolean useBracket = false;
            if (argSign < 0 || cnt > 1) useBracket = true;
            String argString = arg.toString();
            if(useBracket)b.append("(");
            b.append(argString);
            if(useBracket)b.append(")");
            if(args.indexOf(arg)!=args.size()-1){
                b.append("*");
            }
        }
        return b.toString();
    }

    @Override
    Node diff(Variable var) {
        Sum r = new Sum();
        for(int i = 0; i < args.size(); i++){
            Prod m= new Prod();
            for(int j = 0; j < args.size(); j++){
                Node f = args.get(j);
                if(j == i) {
                    m.mul(f.diff(var));
                }
                else {
                    m.mul(f);
                }
            }
            r.add(m);
        }
        return r;
    }

    @Override
    boolean isZero(){
        return (this.evaluate() > -0.0000001 && this.evaluate() < 0.0000001);
    }
}
