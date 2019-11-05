package com.gmail.konradkalita.lab5;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Constant extends Node
{
    private double value;

    Constant(double value)
    {
        this.sign = value < 0 ? -1 : 1;
        this.value = value < 0 ? -value : value;
    }

    @Override
    public double evaluate()
    {
        return sign * value;
    }

    @Override
    public String toString()
    {
        String sgn = sign < 0 ? "-" : "";
        DecimalFormat format = new DecimalFormat("0.####", new DecimalFormatSymbols(Locale.US));
        return sgn + format.format(value);
    }
}
