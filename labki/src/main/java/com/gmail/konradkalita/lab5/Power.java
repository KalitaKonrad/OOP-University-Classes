package com.gmail.konradkalita.lab5;


public class Power extends Node
{
    private double power;
    private Node arg;

    Power(Node arg, double power)
    {
        this.arg = arg;
        this.power = power;
    }

    @Override
    public double evaluate()
    {
        double argVal = arg.evaluate();
        return Math.pow(argVal,power);
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
        builder.append(power);
        return builder.toString();
    }
}
