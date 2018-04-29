package com.airwallex.calculator;

import com.airwallex.calculator.exception.RPNCalculatorException;
import com.airwallex.calculator.model.Expression;
import com.airwallex.calculator.model.LastInstruction;
import com.airwallex.calculator.model.Operator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

/**
 * This class is responsible for handling all the operations of RPN Calculator.
 * <p>
 * Created by sagarjani.
 */
@Component
public class RPNCalculator<T extends Number> implements Calculator<Double> {

    private static Log logger = LogFactory.getLog(RPNCalculator.class);

    /**
     * Regex pattern to identify the decimal number.
     */
    private static final String decimalPattern = "^\\d+\\.?\\d*$";

    /**
     * RPNCalculator Stack to maintain the LIFO-order of decimal numbers.
     */
    Stack<Double> calculatorStack = new Stack<>();

    /**
     * Stack to keep track of last instructions so that undo operation can refer.
     */
    Stack<LastInstruction> lastInstructions = new Stack();

    /**
     * Handles the computation for the input list.
     *
     * @param inputList
     */
    public void compute(List<String> inputList) throws RPNCalculatorException {

        //for each item in the list apply the function.
        int pos = 0;
        for (String input : inputList) {
            evaluateToken(input, (++pos * 2 - 1));
        }

    }

    /**
     * Evaluate the token : if a token is a digit push it to the stack otherwise if it is an operator,
     * pop only the required operands from stack and evaluate the expression.
     * It also adds the last instruction to another array to cater for undo operation.
     *
     * @param token
     * @throws RPNCalculatorException
     */
    private void evaluateToken(String token, int position) throws RPNCalculatorException {

        if (token.matches(decimalPattern)) {
            handleOperand(token);
        } else {
            handleOperator(calculatorStack, token, position);
        }
    }

    /**
     * Handles the digits by pushing it to Stack for further processing.
     * @param token
     * @throws RPNCalculatorException
     */
    private void handleOperand(String token) throws RPNCalculatorException {
        Double decimalNumber;

        try {
            decimalNumber = Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            throw new RPNCalculatorException("An exception occurred while parsing the " + token + " to Double");
        }

        calculatorStack.push(decimalNumber);
        lastInstructions.push(new LastInstruction(null, decimalNumber, null));
    }

    /**
     * Responsible for handling the operators. If expression contains operator, it performs:
     * i)  Pop the last two elements from calculator stack
     * ii) Performs the specified operation by using {@link Expression} class.
     *
     * @param calculatorStack
     * @param token
     * @throws RPNCalculatorException - If there are insufficient elements in the stack.
     */
    private void handleOperator(Stack<Double> calculatorStack, String token, int position) throws RPNCalculatorException {
        Operator operator = getOperator(token);

        validateOperands(calculatorStack, position, operator);

        if (handleClearAndUndo(operator)) return;

        Double firstOperand = popElementFromStack(calculatorStack);
        Double secondOperand = (operator.getNumOfOperands() > 1) ? calculatorStack.pop() : null;

        Expression expression = new Expression(firstOperand, secondOperand, operator);
        Double result = expression.evaluate();

        lastInstructions.push(new LastInstruction(operator, firstOperand, secondOperand));

        if (result != null) {
            calculatorStack.push(result);
        }
    }

    /**
     * Validate if there are sufficient parameters to perform the operation.
     *
     * @param calculatorStack
     * @param position
     * @param operator
     * @throws RPNCalculatorException
     */
    private void validateOperands(Stack<Double> calculatorStack, int position, Operator operator) throws RPNCalculatorException {
        if (calculatorStack.size() < operator.getNumOfOperands()) {

            throw new RPNCalculatorException("operator " + operator.getOperator() +
                    " (position: " + position + "): insufficient parameters");
        }
    }

    /**
     * Returns an {{@link Operator}} if it matches the symbol otherwise throws an Exception.
     *
     * @param token
     * @return
     * @throws RPNCalculatorException
     */
    private Operator getOperator(String token) throws RPNCalculatorException {
        Operator operator = Operator.getOperator(token);
        if (operator == null) {
            throw new RPNCalculatorException("No Operators found with symbol:" + token);
        }
        return operator;
    }

    /**
     * Handles Clear and Undo operations.
     *
     * @param operator
     * @return - true if the operation is {{@link Operator#CLEAR}} or {{@link Operator#UNDO}}
     * otherwise returns false.
     */
    private boolean handleClearAndUndo(Operator operator) {
        if (operator == Operator.CLEAR) {
            clearStack();
            return true;
        }

        if (operator == Operator.UNDO) {
            undo();
            return true;
        }
        return false;
    }

    /**
     * Performs the undo operation with below steps :
     * i) Pop from the calculatorStack.
     * ii) Pop the element form lastInstruction stack.
     * iii) Push the last instruction to calculatorStack.
     */
    private void undo() {
        popElementFromStack(calculatorStack);

        LastInstruction lastInstruction = lastInstructions.pop();
        if (lastInstruction.getOperator() == null) {
            return;
        }

        if (lastInstruction.getSecondOperand() != null) {
            calculatorStack.push(lastInstruction.getSecondOperand());
        }

        if (lastInstruction.getFirstOperand() != null) {
            calculatorStack.push(lastInstruction.getFirstOperand());
        }
    }

    /**
     * Clears the stack.
     */
    private void clearStack() {
        this.calculatorStack.clear();
    }

    /**
     * Returns calculator stack.
     *
     * @return
     */
    public Stack<Double> getStack() {
        return calculatorStack;
    }

    private Double popElementFromStack(Stack<Double> stack) {
        Double element = null;
        if (!stack.empty()) {
            element = stack.pop();
        }
        return element;
    }

}
