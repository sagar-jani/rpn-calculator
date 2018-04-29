package com.airwallex.calculator.rest.controller;

import com.airwallex.calculator.Calculator;
import com.airwallex.calculator.exception.RPNCalculatorException;
import com.airwallex.calculator.utils.StackUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Rest Interface Controller to handle rest request for expressions.
 * <p>
 * Created by sagarjani .
 */
@RestController
public class RPNRestController {

    @Autowired
    Calculator<Double> rpnCalculator;

    /**
     * Expose "/calculate" endpoint which takes query string as expression.
     *
     * @param input
     * @return
     */
    @GetMapping("/calculate")
    @ResponseBody
    public String calculate(@RequestParam("expr") String input) {
        String[] userInputArray = input.trim().split("\\s+");
        List<String> inputList = Arrays.asList(userInputArray);

        /**
         * Call the compute method to calculate the results.
         */
        try {
            rpnCalculator.compute(inputList);
        } catch (RPNCalculatorException e) {
            return e.getMessage();
        }

        Stack<Double> calculatorStack = rpnCalculator.getStack();
        return StackUtils.printStack(calculatorStack);

    }


}


