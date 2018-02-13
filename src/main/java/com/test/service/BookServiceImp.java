package com.test.service;

import com.test.dao.BookDao;
import com.test.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class BookServiceImp implements BookService{
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    @Transactional
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }
    @Transactional
    public List<Book> list() {
        return bookDao.list();
    }
    @Transactional
    public void updateBook( Book book) {
        bookDao.updateBook(book);
    }
    @Transactional
    public void deleteBook(int id) {
        bookDao.deleteBook(id);
    }
    @Transactional
    public void addBook(Book book) {
         bookDao.addBook(book);
    }
}
