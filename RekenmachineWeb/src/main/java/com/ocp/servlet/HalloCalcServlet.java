package com.ocp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ocp.rekenmachine.Calculator;
import com.ocp.rekenmachine.ICalculator;

@SuppressWarnings("serial")
public class HalloCalcServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String input = request.getParameter("calcinput");
        String result = "wuuuut";
        try {
        	ICalculator calc = new Calculator();
        	result = calc.calculate(input.split(" "));
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        out.print("Output Awesome Calculator:\n" + result);
        out.println("<br/><br/><a href=\"./\">back to input</a>");
        out.flush();
        out.close();
    }
}
