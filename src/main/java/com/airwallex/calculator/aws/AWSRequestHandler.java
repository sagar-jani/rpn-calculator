package com.airwallex.calculator.aws;

import com.airwallex.calculator.Calculator;
import com.airwallex.calculator.RPNCalculator;
import com.airwallex.calculator.exception.RPNCalculatorException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Request Handler for AWS Lambda, this class would receive a POST request generated through AWS API Gateway
 * and returns the result.
 *
 * To pass the input please provide the expression as part of raw method body (no key) .
 *
 * <p>
 * Below is a sample logs generated fpr lambda :
 * START RequestId: e9f0571e-4aad-11e8-9dea-4fc43429485f Version: $LATEST
 * Function name: RPNCalculator
 * Max mem allocated: 512
 * Time remaining in milliseconds: 14964
 * CloudWatch log stream name: 2018/04/28/[$LATEST]3c7c15af25e242bd9f83fc35d3916ac1
 * CloudWatch log group name: /aws/lambda/RPNCalculator
 * Stack:END RequestId: e9f0571e-4aad-11e8-9dea-4fc43429485f
 * REPORT RequestId: e9f0571e-4aad-11e8-9dea-4fc43429485f	Duration: 58.82 ms	Billed Duration: 100 ms 	Memory Size: 512 MB	Max Memory Used: 34 MB
 *
 * <p>
 * Created by sagarjani.
 */
public class AWSRequestHandler implements RequestHandler<String, String> {


    @Override
    public String handleRequest(String input, Context context) {

        //Get function info using the context object.
        System.out.println("Function name: " + context.getFunctionName());
        System.out.println("Max mem allocated: " + context.getMemoryLimitInMB());
        System.out.println("Time remaining in milliseconds: " + context.getRemainingTimeInMillis());
        System.out.println("CloudWatch log stream name: " + context.getLogStreamName());
        System.out.println("CloudWatch log group name: " + context.getLogGroupName());

        String[] userInputArray = input.trim().split("\\s+");
        List<String> inputList = Arrays.asList(userInputArray);

        /**
         * Call the compute method to calculate the results.
         */
        Calculator<Double> rpnCalculator = new RPNCalculator<>();
        try {
            rpnCalculator.compute(inputList);
        } catch (RPNCalculatorException e) {
            return e.getMessage();
        }

        Stack<Double> calculatorStack = rpnCalculator.getStack();
        return printStack(calculatorStack);
    }

    /**
     * Prints the calculator stack.
     *
     * @param calculatorStack
     * @return
     */
    private static String printStack(Stack<Double> calculatorStack) {
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
