package com.weblibrary.service;

import com.weblibrary.entity.Book;

import java.util.List;
import java.util.Random;

public class BookFull {
    private List<Book> books;

    @Override
    public String toString() {
        return "BookFull{" +
                "books=" + books +
                '}';
    }

    public BookFull(List<Book> books){
        this.books=books;
    }

    public Book getRandom(){

        Random random = new Random();
        Book[] asArray = (Book[]) books.toArray();
        Book book = asArray[random.nextInt(asArray.length)];
        books.remove(book);
        return book;
    }

    public List<Book> getAll(){
        return books;
    }
}