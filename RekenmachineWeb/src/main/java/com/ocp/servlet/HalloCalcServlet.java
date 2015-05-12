package com.ocp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.ordina.academy.calculator.Calculator;
import nl.ordina.academy.calculator.exception.CalculatorException;

@WebServlet("/hallo-calculator")
public class HalloCalcServlet extends HttpServlet {

    @Inject
    private Calculator calc;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String input = request.getParameter("calcinput");
        String result = "wuuuut";
        try {
            result = String.valueOf(calc.calculate(input));
        } catch (CalculatorException ce) {
            result = ce.getMessage();
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.print("Output Awesome Calculator " + input + ":\n" + result);
        out.println("<br/><br/><a href=\"./\">back to input</a>");
        out.flush();
        out.close();

    }
}
