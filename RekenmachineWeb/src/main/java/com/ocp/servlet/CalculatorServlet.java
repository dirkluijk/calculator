package com.ocp.servlet;

import nl.ordina.academy.calculator.Calculator;
import nl.ordina.academy.calculator.exception.CalculatorException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Servlet Calculator handles HTTP requests to communicate with 3th-party lib calculator
 */
@SuppressWarnings("serial")
@WebServlet("/calculator")

public class CalculatorServlet extends HttpServlet {
    @Inject Calculator calculator;

    /**
     * Handles the Post method of the request of this servlet.
     * @param request {@link HttpServletRequest} the HTTP Post request object
     * @param response {@link HttpServletResponse} the HTTP Post response object 
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String input = request.getParameter("input");
        String message = doCalculation(input);

        request.setAttribute("message", message);
        request.setAttribute("input", input);

        doGet(request, response);
    }

    /**
     * Handles the Get method of the request of this servlet.
     * @param request {@link HttpServletRequest} the HTTP Get request object
     * @param response {@link HttpServletResponse} the HTTP Get response object 
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private String doCalculation(String input) {
        try {
            String result = calculator.calculate(input).toString();
            return String.format("<div class=\"alert alert-success\" role=\"alert\">%s = %s</div>", input, result);
        } catch (CalculatorException e) {
            return String.format("<div class=\"alert alert-danger\" role=\"alert\">%s</div>", e.getMessage());
        }
    }
}
