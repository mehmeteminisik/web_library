package com.weblibrary.Servlet.AdminServlets;

import com.weblibrary.dao.BookDAO;
import com.weblibrary.entity.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class ServletFinallyUpdater extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String ISBN = request.getParameter("isbn");
        String year = request.getParameter("year");
        String genre1 = request.getParameter("genre1");
        String genre2 = request.getParameter("genre2");
        String genre3 = request.getParameter("genre3");

        long isbn = Integer.parseInt(ISBN);

        BookDAO bookDao=(BookDAO)getServletContext().getAttribute("bookDao");
        Book book = bookDao.update(isbn, author, title, year, genre1, genre2, genre3);

        request.setAttribute("book", book);
        request.setAttribute("msg", "Updated book!");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/view.jsp");
        requestDispatcher.forward(request, response);
    }
}
