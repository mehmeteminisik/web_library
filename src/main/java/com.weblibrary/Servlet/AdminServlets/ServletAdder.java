package com.weblibrary.Servlet.AdminServlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.weblibrary.dao.BookDAO;
import com.weblibrary.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/adder")
public class ServletAdder extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String inputData=request.getParameter("bookData");
        Gson gson = new Gson();
        JsonObject input = gson.fromJson(inputData, JsonElement.class).getAsJsonObject();

        String  title = input.get("title").getAsString(),
                author = input.get("author").getAsString(),
                year = input.get("year").getAsString(),
                genre1 = input.get("genre1").getAsString(),
                genre2 = input.get("genre2").getAsString(),
                genre3 = input.get("genre3").getAsString();

        System.out.println("Our Book:"+title + ", " + author + ", " + year + ", " + genre1+ ", " + genre2+ ", " + genre3);
        BookDAO bookDAO=(BookDAO)getServletContext().getAttribute("bookDao");

        long isbn = bookDAO.addBook(title, author, year, genre1, genre2, genre3);
        Book book = bookDAO.findByIsbn(isbn);

        try{
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(book));
            response.getOutputStream().flush();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}