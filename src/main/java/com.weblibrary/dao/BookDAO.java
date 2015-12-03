package com.weblibrary.dao;

import com.weblibrary.entity.Book;
import com.weblibrary.service.BookFull;
import org.hibernate.HibernateException;

public interface BookDAO {

    long addBook(String title, String author, String year, String type1, String type2, String type3);
    boolean delete(long isbn) throws HibernateException;
    boolean findBook(String title, String author, String year) ;
    BookFull findAll(String title, String author, String year, String genre);
    Book findByIsbn(long isbn);
    Book update(long isbn, String author, String title, String year, String genre1, String genre2, String genre3);
}