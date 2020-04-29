package com.bridgelabz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"}
)

public class LoginServlet extends HttpServlet {

    //regex pattern
    static String VALID_USERNAME_PATTERN = "^[A-Z]{1}[a-z]{2,}$";
    static String VALID_PASSWORD_PATTERN = "^(?=.{8})(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]*[^A-Za-z0-9][A-Za-z0-9]*$";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get request parameters for userId and password
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        boolean validName = (userName != null) && userName.matches(VALID_USERNAME_PATTERN);
        boolean ValidPassword = (password != null) && password.matches(VALID_PASSWORD_PATTERN);

        if (validName && ValidPassword) {
            request.setAttribute("userName", userName);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red> Either UserName or Password is Wrong .</font>");
            rd.include(request, response);
        }
    }
}