package com.gmail.konradkalita.lab5;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
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

        int countPointsInsideCircle = 0;
        ArrayList<Double> xInCircle = new ArrayList<>();
        ArrayList<Double> yInCircle = new ArrayList<>();

        while(countPointsInsideCircle < 100)
        {
            double xv = 100*(Math.random()-.5);
            double yv = 100*(Math.random()-.5);
            x.setValue(xv);
            y.setValue(yv);
            double fv = circle.evaluate();
            if (fv < 0)
            {
                xInCircle.add(xv);
                yInCircle.add(yv);
                countPointsInsideCircle++;
            }
        }
        for (int i = 0; i < 100; i++)
        {
            System.out.println("("+xInCircle.get(i)+";"+yInCircle.get(i)+")");
        }
    }
}
