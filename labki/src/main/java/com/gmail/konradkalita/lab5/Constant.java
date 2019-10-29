package com.gmail.konradkalita.lab5;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Constant extends Node
{
    private double value;

    @Override
    public double evaluate()
    {
        return getSign() *  value;
    }

    @Override
    public String toString()
    {
        String sgn = getSign() < 0 ? "-" : "";
        DecimalFormat format = new DecimalFormat("0.####", new DecimalFormatSymbols(Locale.US));
        return sgn + format.format(value);
    }
}
