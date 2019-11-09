package com.gmail.konradkalita.lab5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class lab5Test
{
    @ParameterizedTest
    @ValueSource(doubles = {-1, 1, 2})
    void evaluate_ShouldReturnTrueForArgumentsWhichEvaluateExpressionToZero(double number) //f(-1) == f(1) == f(2) == 0
    {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);

        x.setValue(number);
        double evaluated = exp.evaluate();
        assertEquals(0.0, evaluated, 1e-6);
    }

    @Test
    @DisplayName("toString_ShouldReturn: 2.1*x^3 + x^2 + (-2)*x + 7")
    void toString_Test()
    {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1, new Power(x, 3))
                .add(new Power(x, 2))
                .add(-2, x)
                .add(7);
        assertEquals(exp.toString(), "2.1*x^3 + x^2 + (-2)*x + 7");
    }

    @Test
    @DisplayName("diff_ShouldReturn: `0*x^3 + 2*3*x^2*1 + 2*x^1*1 + 0*x + (-2)*1 + 0` WhenInput: `exp=2*x^3 + x^2 + (-2)*x + 7`")
    void diff_Test()
    {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);

        Node d = exp.diff(x);
        assertEquals("2*x^3 + x^2 + (-2)*x + 7", exp.toString());
        assertEquals("0*x^3 + 2*(3*x^2*1) + 2*x^1*1 + 0*x + (-2)*1 + 0", d.toString());
    }

    @Test
    @DisplayName("diff_ShouldReturn: `2*x^1*1 + 2*y^1*0 + 0*x + 8*1 + 0*y + 4*0 + 0` WhenInput: x^2 + y^2 + 8*x + 4*y + 16")
    void diff_Test2()
    {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        assertEquals("2*x^1*1 + 2*y^1*0 + 0*x + 8*1 + 0*y + 4*0 + 0", circle.diff(x).toString()); // derivative on var X
        assertEquals("2*x^1*0 + 2*y^1*1 + 0*x + 8*0 + 0*y + 4*1 + 0", circle.diff(y).toString()); // derivative on var Y
    }
}
