package com.weblibrary.Servlet.AdminServlets;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class ServletAdmin extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/login.jsp");
        requestDispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        ServletContext servletContext = getServletContext();
        String adminLogin = servletContext.getInitParameter("adminLogin");
        String adminPassword = servletContext.getInitParameter("adminPassword");

        RequestDispatcher requestDispatcher;

        if (login.equals(adminLogin) && password.equals(adminPassword)) {
            requestDispatcher = request.getRequestDispatcher("/admin/admin.html");
        } else {
            request.setAttribute("error", "Incorrect data! Try again!");
            requestDispatcher = request.getRequestDispatcher("/admin/login.jsp");
        }

        requestDispatcher.forward(request, response);
    }
}
