package com.ocp.servlet.bean;

import nl.ordina.academy.calculator.Calculator;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

/**
 * Bean which holds an requestscoped instance of Calculator.
 */
@ApplicationScoped
public class CalculatorBean implements Serializable {
    private Calculator calculator = new Calculator();

    public Calculator getCalculator() {
        return calculator;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }
}