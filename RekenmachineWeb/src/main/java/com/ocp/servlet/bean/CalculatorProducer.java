package com.ocp.servlet.bean;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import nl.ordina.academy.calculator.Calculator;
import javax.enterprise.inject.Produces;

/**
 * CDI managed bean used to produce an requestscoped instance of Calculator.
 */
@RequestScoped
public class CalculatorProducer implements Serializable {

    /**
     * Produce method to create an bean instance of Calculator.
     * @return an instance of calculator
     * @see Calculator
     */
    @Produces
    public Calculator getCalculator() {
        return new Calculator();
    }
}
