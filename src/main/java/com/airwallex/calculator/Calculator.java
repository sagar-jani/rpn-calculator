package com.airwallex.calculator;

import com.airwallex.calculator.exception.RPNCalculatorException;

import java.util.List;
import java.util.Stack;

/**
 * Generic Calculator class that defines a contract to implement any type of Calculator like RPN, Infix etc.
 * <p>
 * Created by sagarjani.
 */
public interface Calculator<T> {

    /**
     * Performs postfix notation calculation which supports following operations:
     * <p>
     * <ul>
     * <li>ADD</li>
     * <li>SUBTRACT</li>
     * <li>MULTIPLY</li>
     * <li>DIVISION</li>
     * <li>SQRT</li>
     * <li>CLEAR</li>
     * <li>UNDO</li>
     * <li>UNDO UNDO</li>
     * </ul>
     *
     * @param inputList
     * @throws RPNCalculatorException
     */
    void compute(List<String> inputList) throws RPNCalculatorException;

    /**
     * Returns the stack.
     *
     * @return
     */
    Stack<T> getStack();

}
