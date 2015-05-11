package com.ocp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.ordina.academy.calculator.Calculator;
import nl.ordina.academy.calculator.RPNConverter;
import nl.ordina.academy.calculator.RPNInterpreter;
import nl.ordina.academy.calculator.Tokenizer;
import nl.ordina.academy.calculator.exception.CalculatorException;

@SuppressWarnings("serial")
public class HalloCalcServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String input = request.getParameter("calcinput");
        String result = "wuuuut";
        try {
            Calculator calc = new Calculator(new Tokenizer(), new RPNConverter(), new RPNInterpreter());
            result = String.valueOf(calc.calculate(input));
        } catch (CalculatorException ce) {
            result = ce.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.print("Output Awesome Calculator " + input + ":\n" + result);
        out.println("<br/><br/><a href=\"./\">back to input</a>");
        out.flush();
        out.close();

    }
}
