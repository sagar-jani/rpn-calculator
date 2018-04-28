package com.airwallex.calculator.model;

import com.airwallex.calculator.exception.RPNCalculatorException;

/**
 * Represents Expression of Reverse Polish Notation.
 * <p>
 * Created by sagarjani
 */
public class Expression {
    private Double firstOperand;
    private Double secondOperand;
    private Operator operator;

    public Expression(Double firstOperand, Double secondOperand, Operator operator) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
    }

    /**
     * Evaluates the operation based on operator.
     *
     * @return
     * @throws RPNCalculatorException
     */
    public Double evaluate() throws RPNCalculatorException {
        Double result = null;
        if (this.firstOperand != null) {
            result = operator.operate(firstOperand, secondOperand);
        }
        return result;
    }
}
