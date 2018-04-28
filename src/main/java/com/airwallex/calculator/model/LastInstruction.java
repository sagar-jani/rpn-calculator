package com.airwallex.calculator.model;

/**
 * Represents the last instruction that was executed.
 * <p>
 * Created by sagarjani.
 */
public class LastInstruction {
    private Operator operator;
    private Double firstOperand;
    private Double secondOperand;

    /**
     * @param operator
     * @param firstOperand
     * @param secondOperand
     */
    public LastInstruction(Operator operator, Double firstOperand, Double secondOperand) {
        this.operator = operator;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public Operator getOperator() {
        return operator;
    }

    public Double getFirstOperand() {
        return firstOperand;
    }

    public Double getSecondOperand() {
        return secondOperand;
    }
}
