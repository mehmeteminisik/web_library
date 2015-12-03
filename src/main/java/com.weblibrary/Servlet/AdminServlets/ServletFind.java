package com.weblibrary.Servlet.AdminServlets;

import com.weblibrary.dao.BookDAO;
import com.weblibrary.entity.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vlad on 17.08.15.
 */

@WebServlet("/finder")
public class ServletFind extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String ISBN = request.getParameter("isbn");
        long isbn = Integer.parseInt(ISBN);

        BookDAO bookDao=(BookDAO)getServletContext().getAttribute("bookDao");

        try{
            Book book = bookDao.findByIsbn(isbn);
            request.setAttribute("book", book);
            request.setAttribute("msg", "Book found!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/view.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
            String error = "Error finding book!";
            request.setAttribute("error", error);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}