package com.test.dao;

import com.test.model.Book;

import java.util.List;

public interface BookDao {

    Book getBookById(int id);
    List<Book> list();
    void updateBook(Book book);
    void deleteBook(int id);
    void addBook(Book book);
}

