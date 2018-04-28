package com.airwallex.calculator;

import com.airwallex.calculator.exception.RPNCalculatorException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Tests all the operations calculated.
 *
 * Created by sagarjani.
 */
public class RPNCalculatorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {

    }

    /**
     * Tests 'Insufficient parameter' error scenario.
     * Input : 1 2 3 * 5 + * * 6 5
     * Output : operator * (position: 15): insufficient parameters
     *
     * @throws RPNCalculatorException
     */
    @Test
    public void testInsufficientParameters() throws RPNCalculatorException {
        expectedEx.expect(RPNCalculatorException.class);
        expectedEx.expectMessage("operator * (position: 15): insufficient parameters");

        Stack<Double> expectedStack = new Stack<>();
        expectedStack.push((double) 11);

        Calculator calculator = new RPNCalculator();
        calculator.compute(Arrays.asList("1", "2", "3", "*", "5", "+", "*", "*", "6", "5"));
        Stack<Double> actualStack = calculator.getStack();
        assertEquals("Insufficient Parameter error scenario doesn't work.", expectedStack, actualStack);
    }

    /**
     * Tests 'Invalid' operator.
     *
     * @throws RPNCalculatorException
     */
    @Test
    public void testInvalidOperator() throws RPNCalculatorException {
        expectedEx.expect(RPNCalculatorException.class);
        expectedEx.expectMessage("No Operators found with symbol:#");
        Stack<Double> expectedStack = new Stack<>();

        Calculator calculator = new RPNCalculator();
        calculator.compute(Arrays.asList("2", "#"));
        Stack<Double> actualStack = calculator.getStack();
        assertEquals("The Invalid Operator error scenario doesn't work as expected.", expectedStack, actualStack);
    }

    /**
     * Tests 'Multiplication' operation.
     *
     * @throws RPNCalculatorException
     */
    @Test
    public void testComputeMultiplication() throws RPNCalculatorException {
        Stack<Double> expectedStack = new Stack<>();
        expectedStack.push((double) 15);

        Calculator calculator = new RPNCalculator();
        calculator.compute(Arrays.asList("5", "3", "*"));
        Stack<Double> actualStack = calculator.getStack();
        assertEquals("The Multiplication operation doesn't work as expected.", expectedStack, actualStack);
    }

    /**
     * Tests whether expression :- 3 5 6 * 10 / * works.
     *
     * @throws RPNCalculatorException
     */
    @Test
    public void testMultiOperator() throws RPNCalculatorException {
        Stack<Double> expectedStack = new Stack<>();
        expectedStack.push((double) 9);

        Calculator calculator = new RPNCalculator();
        calculator.compute(Arrays.asList("3", "5", "6", "*", "10", "/", "*"));
        Stack<Double> actualStack = calculator.getStack();
        assertEquals("The Multiplication operation doesn't work as expected.", expectedStack, actualStack);

    }

    /**
     * Tests 'CLEAR' operation.
     *
     * @throws RPNCalculatorException
     */
    @Test
    public void testClear() throws RPNCalculatorException {
        Stack<Double> expectedStack = new Stack<>();

        Calculator calculator = new RPNCalculator();
        calculator.compute(Arrays.asList("5", "3", "*", "clear"));
        Stack<Double> actualStack = calculator.getStack();
        assertEquals("The Clear operation doesn't work as expected.", expectedStack, actualStack);
    }

    /**
     * Tests 'UNDO' operation.
     *
     * @throws RPNCalculatorException
     */
    @Test
    public void testUndo() throws RPNCalculatorException {
        Stack<Double> expectedStack = new Stack<>();
        expectedStack.push((double) 5);
        expectedStack.push((double) 4);

        Calculator calculator = new RPNCalculator();
        calculator.compute(Arrays.asList("5", "4", "3", "undo"));
        Stack<Double> actualStack = calculator.getStack();
        assertEquals("The Undo operation doesn't work as expected.", expectedStack, actualStack);
    }

    /**
     * Tests 'UNDO UNDO' operation.
     *
     * @throws RPNCalculatorException
     */
    @Test
    public void testUndoUndo() throws RPNCalculatorException {
        Stack<Double> expectedStack = new Stack<>();
        expectedStack.push((double) 5);

        Calculator calculator = new RPNCalculator();
        calculator.compute(Arrays.asList("5", "4", "3", "undo", "undo"));
        Stack<Double> actualStack = calculator.getStack();
        assertEquals("The Undo Undo operation doesn't work as expected.", expectedStack, actualStack);
    }

    /**
     * Tests 'Sqrt' operation.
     *
     * @throws RPNCalculatorException
     */
    @Test
    public void testSqrt() throws RPNCalculatorException {
        Stack<Double> expectedStack = new Stack<>();
        expectedStack.push(1.4142135623730951);

        Calculator calculator = new RPNCalculator();
        calculator.compute(Arrays.asList("2", "sqrt"));
        Stack<Double> actualStack = calculator.getStack();
        assertEquals("The Sqrt operation doesn't work as expected.", expectedStack, actualStack);
    }

    /**
     * Tests 'Subtract' operation.
     *
     * @throws RPNCalculatorException
     */
    @Test
    public void testSubtract() throws RPNCalculatorException {
        Stack<Double> expectedStack = new Stack<>();
        expectedStack.push((double) 3);

        Calculator calculator = new RPNCalculator();
        calculator.compute(Arrays.asList("5", "2", "-"));
        Stack<Double> actualStack = calculator.getStack();
        assertEquals("The Subtract operation doesn't work as expected.", expectedStack, actualStack);
    }

    /**
     * Tests 'Division' operation.
     *
     * @throws RPNCalculatorException
     */
    @Test
    public void testDivision() throws RPNCalculatorException {
        Stack<Double> expectedStack = new Stack<>();
        expectedStack.push((double) 3);

        Calculator calculator = new RPNCalculator();
        calculator.compute(Arrays.asList("6", "2", "/"));
        Stack<Double> actualStack = calculator.getStack();
        assertEquals("The Division operation doesn't work as expected.", expectedStack, actualStack);
    }




}
