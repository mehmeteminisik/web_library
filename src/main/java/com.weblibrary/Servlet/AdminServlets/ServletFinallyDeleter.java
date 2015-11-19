package com.weblibrary.Servlet.AdminServlets;

import com.weblibrary.dao.BookDAO;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class ServletFinallyDeleter extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");

        BookDAO bookDao=(BookDAO)getServletContext().getAttribute("bookDao");
        try{
            bookDao.delete(Integer.parseInt(isbn));
        } catch (HibernateException e){
            e.printStackTrace();
            String error = "Error deleting book!";
            request.setAttribute("error", error);
            request.setAttribute("forwardTo", "/admin/admin.html");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/admin.html");
        requestDispatcher.forward(request, response);
    }
}
