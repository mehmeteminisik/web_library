package com.weblibrary.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")
public class Book {
    long isbn;
    String title;
    String author;
    String year;
    List<Genre> genres=new ArrayList<>();

    public Book(String title, String author, String year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "book_genre",joinColumns = {@JoinColumn(name = "book_isbn")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    public List<Genre> getGenres() {return genres; }
    public void setGenres(List<Genre> genres) {this.genres=genres; }

    @Id
    @GeneratedValue
    public long getIsbn(){return isbn;}
    public void setIsbn(long isbn){this.isbn=isbn;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title; }

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author=author; }

    public String getYear(){return year; }
    public void setYear(String year){this.year=year;}

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year='" + year + '\'' +
                ", genres=" + genres +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (getIsbn() != book.getIsbn()) return false;
        if (getTitle() != null ? !getTitle().equals(book.getTitle()) : book.getTitle() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(book.getAuthor()) : book.getAuthor() != null) return false;
        if (getYear() != null ? !getYear().equals(book.getYear()) : book.getYear() != null) return false;
        return !(getGenres() != null ? !getGenres().equals(book.getGenres()) : book.getGenres() != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getIsbn() ^ (getIsbn() >>> 32));
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getGenres() != null ? getGenres().hashCode() : 0);
        return result;
    }
}

