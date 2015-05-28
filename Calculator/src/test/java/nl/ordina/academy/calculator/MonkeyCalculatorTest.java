package nl.ordina.academy.calculator;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */

import nl.ordina.academy.calculator.exception.CalculatorException;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.DecimalFormat;

public class MonkeyCalculatorTest {
    public static void main(String[] args) {
        new MonkeyCalculatorTest().runTest();
    }

    private ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("js");

    @Test
    public void runTest() {
        Calculator c = new Calculator();
        for (int i = 0; i < 1000; i++) {
            String expression = getExpression();
            //System.out.println(expression + " = " + calculate(expression));
            expression = expression.replace(',', '.');
            try {
                System.out.println(expression + " = " + c.calculate(expression));
            } catch (CalculatorException e) {
                System.out.println("Er ging iets mis tijdens uitvoeren van " + expression + ": " + e.getMessage());
            }
        }
    }

    public String getExpression() {
        switch ((int) (Math.random() * 10)) {
            case 0:
                return "(" + getExpression() + ")";
            case 1:
                return getExpression() + " + " + getExpression();
            case 2:
                return getExpression() + " - " + getExpression();
            case 3:
                return getExpression() + " / " + getExpression();
            case 4:
                return getExpression() + " * " + getExpression();
            default:
                return ((Math.random() < 0.1 ? "-" : "") + (((int) (Math.random() * 100000)) / 1000d)).replace(".", ",");
        }
    }

    /*public String calculate(String expression) {
        try {
            String jsExpression = expression.replace(",", ".");
            Double jsResult = Double.parseDouble(scriptEngine.eval(jsExpression).toString());
            return new DecimalFormat("###.###").format(jsResult).replace(".", ",");
        } catch (ScriptException se) {
            throw new RuntimeException("Fout in expressie " + expression, se);
        }
    }*/
}
