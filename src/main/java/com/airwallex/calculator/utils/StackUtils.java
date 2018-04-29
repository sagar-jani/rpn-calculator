package com.airwallex.calculator.utils;

import java.text.DecimalFormat;
import java.util.Stack;

/**
 *
 * Utility class to print the stack.
 *
 * Created by sagarjani .
 */
public class StackUtils {
    /**
     * Formats the output in 10 decimal places and Prints the calculator stack..
     *
     * @param calculatorStack
     * @return
     */
    public static String printStack(Stack<Double> calculatorStack) {
        DecimalFormat fmt = new DecimalFormat("0.##########");
        System.out.print("Stack:");
        StringBuffer sb = new StringBuffer();

        calculatorStack.forEach((element) -> {
            sb.append(fmt.format(element));
            sb.append(" ");
        });

        return sb.toString();
    }
}
