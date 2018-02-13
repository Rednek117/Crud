package com.test.dao;

import com.test.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class BookDaoImp  implements BookDao{
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImp.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public Book getBookById(int id) {
        Session session= this.sessionFactory.getCurrentSession();
        Book book = session.load(Book.class, id);
        logger.info("Book info: " + book);
        return book;
    }

    @SuppressWarnings("unchecked")
    public List<Book> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("from Book").list();
        for (Book book : bookList) {
            logger.info("Book list: " + book);
        }
        return bookList;
    }

    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        book.isReadAlready();
        session.update(book);
        logger.info("Book updated: " + book);
    }

    public void deleteBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = session.load(Book.class, id);
        if(book != null) session.delete(book);
        logger.info("Book Deleted: " + book);
    }

    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(book);
        logger.info("Book added: " + book);
    }
}
