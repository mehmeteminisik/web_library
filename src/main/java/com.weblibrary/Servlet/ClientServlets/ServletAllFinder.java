package com.weblibrary.Servlet.ClientServlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.weblibrary.dao.BookDAO;
import com.weblibrary.entity.Book;
import com.weblibrary.service.BookFull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findAll")
public class ServletAllFinder extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json=request.getParameter("bookData");
        System.out.println("input data: " + json);

        Gson gson = new Gson();
        JsonObject input = gson.fromJson(json, JsonElement.class).getAsJsonObject();

        String  title = input.get("title").getAsString(), author = input.get("author").getAsString(),
                year = input.get("year").getAsString(), genre = input.get("genre").getAsString();
        System.out.println(title + ", " + author + ", " + year + ", " + genre);

        BookDAO bookDAO=(BookDAO)getServletContext().getAttribute("bookDao");
        BookFull bookFull = bookDAO.findAll(title, author, year, genre);
        List<Book> books = bookFull.getAll();
        System.out.println(books);

        try{
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(books)); //send json data to javascript
            System.out.println("Before sending JSON to JavaScript");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
