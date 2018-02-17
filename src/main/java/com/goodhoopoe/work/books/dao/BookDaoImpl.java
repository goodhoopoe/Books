package com.goodhoopoe.work.books.dao;

import com.goodhoopoe.work.books.model.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory sessionFactory;

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);
        logger.info("Book saved. \n" + book);
    }

    @Override
    public void replaceBook(Book book) {
        Session session = this  .sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book replaced. \n" + book);
    }

    @Override
    public void readBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.get(Book.class, id);
        if (book != null) {
            book.setReadAlready(true);
            session.update(book);
        }
        logger.info("Book with id %d has been read", id);
    }

    @Override
    public Book viewBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.get(Book.class, id);
        logger.info("Checking book. \n", book);
        return book;
    }

    @Override
    public void removeBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.get(Book.class, id);
        if (book != null) {
            session.delete(book);
        }
        logger.info("Book with id %d deleted", id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> showBooks(int page) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Book");
        if (page > 0) {
            query.setFirstResult((page - 1) * 10);
        } else {
            query.setFirstResult(0);
        }
        query.setMaxResults(10);
        List<Book> bookList = query.list();
        logger.info("We have %d books. ", bookList.size());
        return bookList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> searchBooks(String keywords) {
        System.out.println(keywords);
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Book where title like :keywords \n" +
                "or description like :keywords or author like :keywords");
        query.setParameter("keywords", "%" + keywords + "%");
        List<Book> bookList = query.list();
        logger.info("We have %d books. ", bookList.size());
        return bookList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int getBooksCount() {
        Session session = this.sessionFactory.getCurrentSession();

        return session.createQuery("from Book").list().size();
    }
}
