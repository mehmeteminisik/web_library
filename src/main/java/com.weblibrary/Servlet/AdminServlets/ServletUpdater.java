package com.weblibrary.Servlet.AdminServlets;

import com.weblibrary.dao.BookDAO;
import com.weblibrary.entity.Book;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findforupdate")
public class ServletUpdater extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ISBN = request.getParameter("isbn");
        long isbn = Integer.parseInt(ISBN);


        BookDAO bookDao=(BookDAO)getServletContext().getAttribute("bookDao");

        try{
            Book book = bookDao.findByIsbn(isbn);
            request.setAttribute("book", book);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/update.jsp");
            requestDispatcher.forward(request, response);
        } catch (HibernateException e){
            e.printStackTrace();
            String error = "Error updating book!";
            request.setAttribute("error", error);
            request.setAttribute("forwardTo", "admin/admin.html");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
