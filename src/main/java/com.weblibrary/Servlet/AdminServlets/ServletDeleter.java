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

@WebServlet("/deleter")
public class ServletDeleter extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputData = request.getParameter("bookData");
        Gson gson = new Gson();
        JsonObject input = gson.fromJson(inputData, JsonElement.class).getAsJsonObject();
        String  ISBN = input.get("isbn").getAsString();
        long isbn = Integer.parseInt(ISBN);

        BookDAO bookDAO=(BookDAO)getServletContext().getAttribute("bookDao");

        Book book=bookDAO.findByIsbn(isbn);
        String str;
        if(book!=null) {
            bookDAO.delete(isbn);
            str="Book with ISBN:"+ISBN+" was deleted";
        }else str="Error";

        try{
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(str));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}