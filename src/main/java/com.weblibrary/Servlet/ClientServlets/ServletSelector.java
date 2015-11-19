package com.weblibrary.Servlet.ClientServlets;/*package com.weblibrary.Servlet.ClientServlets;

import com.weblibrary.entity.Book;
import com.weblibrary.service.BookFull;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/select")
public class ServletSelector extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute("type");
        BookFull bookFull = (BookFull)session.getAttribute("bookFull");

        RequestDispatcher requestDispatcher = null;

        try{
            if (type.equals("random")){
                Book book = bookFull.getRandom();
                request.setAttribute("book", book);
                requestDispatcher = request.getRequestDispatcher("/showBook.jsp");
            } else {
                request.setAttribute("books", bookFull.getAll());
                List<Book> list = (List<Book>) bookFull.getAll();
                requestDispatcher= request.getRequestDispatcher("index.html");
            }
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("error", "No books find!");
            request.setAttribute("forwardTo", "index.html");
            requestDispatcher = request.getRequestDispatcher("/error.jsp");
        }

        requestDispatcher.forward(request, response);
    }
}*/
