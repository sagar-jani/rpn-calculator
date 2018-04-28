package com.airwallex.calculator.model;

import com.airwallex.calculator.exception.RPNCalculatorException;

import java.util.HashMap;

import static java.lang.Math.sqrt;

/**
 * Enumeration to represent the operators and their functions.
 * <p>
 * Created by sagarjani.
 */
public enum Operator {

    /**
     * Addition operation.
     */
    ADD("+", 2) {
        @Override
        public Double operate(Double firstOperand, Double secondOperand) {
            return secondOperand + firstOperand;
        }
    },

    /**
     * Subtraction operation.
     */
    SUBTRACT("-", 2) {
        @Override
        public Double operate(Double firstOperand, Double secondOperand) {
            return secondOperand - firstOperand;
        }
    },

    /**
     * Multiplication operation.
     */
    MUL("*", 2) {
        @Override
        public Double operate(Double firstOperand, Double secondOperand) {
            return secondOperand * firstOperand;
        }
    },

    /**
     * Division operation.
     */
    DIV("/", 2) {
        @Override
        public Double operate(Double firstOperand, Double secondOperand) {
            return secondOperand / firstOperand;
        }
    },

    /**
     * Clear operation.
     */
    CLEAR("clear", 0) {
        @Override
        public Double operate(Double firstOperand, Double secondOperand) throws RPNCalculatorException {
            throw new RPNCalculatorException("Invalid Operation");
        }
    },

    /**
     * Square root operation.
     */
    SQRT("sqrt", 1) {
        @Override
        public Double operate(Double firstOperand, Double secondOperand) throws RPNCalculatorException {
            return sqrt(firstOperand);
        }
    },

    /**
     * Undo operation.
     */
    UNDO("undo", 0) {
        @Override
        public Double operate(Double firstOperand, Double secondOperand) throws RPNCalculatorException {
            throw new RPNCalculatorException("Invalid Operation");
        }
    };

    /**
     * Abstract method to be implemented by enum constants.
     *
     * @param firstOperand
     * @param secondOperand
     * @return
     * @throws RPNCalculatorException
     */
    public abstract Double operate(Double firstOperand, Double secondOperand) throws RPNCalculatorException;

    private String operator;
    private int numOfOperands;

    public String getOperator() {
        return operator;
    }

    public int getNumOfOperands() {
        return numOfOperands;
    }

    Operator(final String operator, final int numOfOperands) {
        this.operator = operator;
        this.numOfOperands = numOfOperands;
    }

    private static final HashMap<String, Operator> operatorMap = new HashMap<>();

    /**
     * Static block to initialize the map with key as operator symbol and value as Operator enum.
     */
    static {
        for (Operator o : values()) {
            operatorMap.put(o.getOperator(), o);
        }
    }

    /**
     * Returns an Operator instance based on symbol.
     *
     * @param symbol
     * @return
     */
    public static Operator getOperator(String symbol) {
        return operatorMap.get(symbol);
    }


}


