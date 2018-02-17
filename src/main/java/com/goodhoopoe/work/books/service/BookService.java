package com.goodhoopoe.work.books.service;

import com.goodhoopoe.work.books.model.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);
    void replaceBook(Book book);
    void readBook(int id);
    Book viewBook(int id);
    void removeBook(int id);
    List<Book> showBooks(int page);
    List<Book> searchBooks(String query);
    int getBooksCount();
}
