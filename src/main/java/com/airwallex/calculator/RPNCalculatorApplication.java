package com.airwallex.calculator;

import com.airwallex.calculator.exception.RPNCalculatorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * SpringBootApplication Main class
 *
 * Created by sagarjani
 */

@SpringBootApplication
public class RPNCalculatorApplication implements CommandLineRunner {

    private static Log logger = LogFactory.getLog(RPNCalculatorApplication.class);

    @Autowired
    RPNCalculator rpnCalculator;

    public static void main(String args[]) {
        SpringApplication app = new SpringApplication();
        app.setBannerMode(Banner.Mode.OFF);
        app.run(RPNCalculatorApplication.class, args);
    }


    /**
     * Formats the output in 10 decimal places and print.
     *
     * @param calculatorStack
     */
    private static void printStack(Stack<Double> calculatorStack) {
        DecimalFormat fmt = new DecimalFormat("0.##########");
        System.out.print("Stack:");
        calculatorStack.forEach((element) -> {
            System.out.print(fmt.format(element));
            System.out.print(" ");
        });
        System.out.println("");
    }

    @Override
    public void run(String... strings) throws Exception {

        System.out.print("Please enter RPN expression :");

        while (true) {
            Scanner reader = new Scanner(System.in);
            String[] userInputArray = reader.nextLine().trim().split("\\s+");
            List<String> inputList = Arrays.asList(userInputArray);

            try {
                rpnCalculator.compute(inputList);
            } catch (RPNCalculatorException e) {
                logger.error("An occurred while processing the request :" + e.getMessage());
            }

            Stack<Double> calculatorStack = rpnCalculator.getStack();
            printStack(calculatorStack);
        }
    }
}
