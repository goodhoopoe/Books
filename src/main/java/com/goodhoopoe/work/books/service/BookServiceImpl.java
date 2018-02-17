package com.goodhoopoe.work.books.service;

import com.goodhoopoe.work.books.dao.BookDao;
import com.goodhoopoe.work.books.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;
    @Transactional
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        this.bookDao.addBook(book);
    }

    @Override
    @Transactional
    public void replaceBook(Book book) {
        this.bookDao.replaceBook(book);
    }

    @Override
    @Transactional
    public void readBook(int id) {
        this.bookDao.readBook(id);
    }

    @Override
    @Transactional
    public void removeBook(int id) {
        this.bookDao.removeBook(id);
    }

    @Override
    @Transactional
    public Book viewBook(int id) {
        return this.bookDao.viewBook(id);
    }

    @Override
    @Transactional
    public List<Book> showBooks(int page) {
        return this.bookDao.showBooks(page);
    }

    @Override
    @Transactional
    public List<Book> searchBooks(String query) {
        return this.bookDao.searchBooks(query);
    }

    @Override
    @Transactional
    public int getBooksCount() {
        return this.bookDao.getBooksCount();
    }
}
