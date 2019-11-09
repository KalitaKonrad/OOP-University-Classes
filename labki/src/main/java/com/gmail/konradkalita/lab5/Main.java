package com.gmail.konradkalita.lab5;

import java.util.Locale;

public class Main
{
    public static void main(String[] args)
    {
        //buildAndPrint();
        //buildAndEvaluate();
        //defineCircle();
    }

    static void defineCircle(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.println(circle.toString());

        double xv = 100*(Math.random()-.5);
        double yv = 100*(Math.random()-.5);
        x.setValue(xv);
        y.setValue(yv);
        double fv = circle.evaluate();
        System.out.print(String.format("Punkt (%f,%f) leży %s koła %s",xv,yv,(fv<0?"wewnątrz":"na zewnątrz"),circle.toString()));
    }
}
