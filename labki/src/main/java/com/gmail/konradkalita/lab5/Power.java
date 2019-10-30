package com.gmail.konradkalita.lab5;


public class Power extends Node
{
    private double p;
    private Node arg;

    Power(Node arg, double p)
    {
        this.arg = arg;
        this.p = p;
    }

    @Override
    public double evaluate()
    {
        double argVal = arg.evaluate();
        return Math.pow(argVal,p);
    }

    @Override
    public int getArgumentsCount()
    {
        return 1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (getSign() < 0) builder.append("-");
        int argSign = arg.getSign();
        int count = getArgumentsCount();
        boolean useBracket = false;
        if(argSign < 0 || count > 1) useBracket = true;
        String argString = arg.toString();
        if(useBracket)
        {
            builder.append("(");
        }
        builder.append(argString);
        if(useBracket)
        {
            builder.append(")");
        }
        builder.append("^");
        builder.append(p);
        return builder.toString();
    }
}
