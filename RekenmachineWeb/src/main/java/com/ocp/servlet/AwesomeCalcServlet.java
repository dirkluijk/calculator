package com.ocp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ocp.rekenmachine.Calculator;
import com.ocp.rekenmachine.ICalculator;

@SuppressWarnings("serial")
public class AwesomeCalcServlet extends HttpServlet {

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("calcinput");
		String result = doCalculation(input);
		
        request.setAttribute("message", "<div class=\"alert alert-success\" role=\"alert\">" + input + " = " + result + "</div>");
        doGet(request, response);
    }
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "");
		}

		request.getRequestDispatcher("/awesomeness.jsp").forward(request, response);
    }
	
	private String doCalculation(String input) {
        try {
        	ICalculator calc = new Calculator();
        	return calc.calculate(input.split(" "));
        } catch (Exception e) {
        	e.printStackTrace();
        	return e.getMessage();
        }
	}
}
