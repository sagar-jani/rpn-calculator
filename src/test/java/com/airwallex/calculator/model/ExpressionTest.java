package com.airwallex.calculator.model;

import com.airwallex.calculator.exception.RPNCalculatorException;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@link Expression} class.
 * <p>
 * Created by sagarjani.
 */
public class ExpressionTest {

    /**
     * Tests whether {@link Expression#evaluate()} method return correct results.
     *
     * @throws RPNCalculatorException
     */
    @Test
    public void testEvaluate() throws RPNCalculatorException {
        Expression expression = new Expression(5.0, 2.0, Operator.ADD);
        assertEquals("Evaluate method doesn't work as expected.", Optional.of(7.0), Optional.of(expression.evaluate()));
    }
}
