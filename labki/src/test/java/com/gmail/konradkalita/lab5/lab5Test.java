package com.gmail.konradkalita.lab5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class lab5Test
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
}
