package com.ocp.servlet.bean;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import nl.ordina.academy.calculator.Calculator;

/**
 * Bean which holds an requestscoped instance of Calculator.
 */
@RequestScoped
public class CalculatorBean implements Serializable {
    private Calculator calculator = new Calculator();

    public Calculator getCalculator() {
        return calculator;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }
}