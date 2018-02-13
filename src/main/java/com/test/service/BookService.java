package com.test.service;

import com.test.model.Book;

import java.util.List;

public interface BookService {

    Book getBookById(int id);
    List<Book> list();
    void updateBook(Book book);
    void deleteBook(int id);
    void addBook(Book book);
}
