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
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        if (sign < 0) builder.append("-");
        int argSign = arg.getSign();
        int count = arg.getArgumentsCount();
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
        int isInteger = 0;
        if(power % 1 == 0) { // check if power is an integer
            isInteger = (int)power;
            builder.append(isInteger);
        }
        else {
            builder.append(power);
        }
        return builder.toString();
    }

    @Override
    Node diff(Variable var)
    {
        Prod r = new Prod(sign * power, new Power(arg, power - 1));
        r.mul(arg.diff(var));
        return r;
    }

    @Override
    boolean isZero()
    {
        return (this.evaluate() > -0.0000001 && this.evaluate() < 0.0000001);
    }
}
