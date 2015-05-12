package nl.ordina.academy.calculator.application;

import nl.ordina.academy.calculator.Calculator;
import nl.ordina.academy.calculator.RPNConverter;
import nl.ordina.academy.calculator.RPNInterpreter;
import nl.ordina.academy.calculator.Tokenizer;
import nl.ordina.academy.calculator.exception.CalculatorException;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Logger;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class CalculatorApp {
    private static final Logger logger = Logger.getLogger( CalculatorApp.class.getName() );
    private Calculator calculator;

    public CalculatorApp(Calculator calculator) {
        this.calculator = calculator;
    }

    public String run(String input) {
        StringBuilder output = new StringBuilder();

        try {
            input = input.trim();
            double result = calculator.calculate(input);

            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.HALF_UP);
            output.append(String.format("Het resultaat van %s is: %s", input, df.format(result)));
        } catch (CalculatorException e) {
            logger.info(e.getMessage());
            output.append("Het ging mis: ").append(e.getMessage());
        }

        return output.toString();
    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg);
        }

//        Calculator calculator = new Calculator(new Tokenizer(), new RPNConverter(), new RPNInterpreter());
        Calculator calculator = null;
        CalculatorApp app = new CalculatorApp(calculator);
        String output = app.run(builder.toString());

        System.out.println(output);
    }
}
