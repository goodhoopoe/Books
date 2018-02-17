package com.goodhoopoe.work.books.dao;

import com.goodhoopoe.work.books.model.Book;

import java.util.List;

public interface BookDao {

    void addBook(Book book);
    void replaceBook(Book book);
    void readBook(int id);
    Book viewBook(int id);
    void removeBook(int id);
    List<Book> showBooks(int page);
    List<Book> searchBooks(String query);
    int getBooksCount();

}
