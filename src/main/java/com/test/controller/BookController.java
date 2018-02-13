package com.test.controller;



import com.test.model.Book;
import com.test.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.List;


@Controller
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private static int id = 0;
    private BookService bookService;


    @Autowired(required = true)
    @Qualifier(value ="bookService")
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }


    @RequestMapping(value = "books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book){
    if(book.getId() == 0 || book.getTitle() != null) this.bookService.addBook(book);
    else this.bookService.updateBook(book);
    return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id){
        this.bookService.deleteBook(id);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id")int id, Model model){
        logger.debug("BookController", "editBook");
        BookController.id = this.bookService.getBookById(id).getId();
        model.addAttribute("book",this.bookService.getBookById(id));
        model.addAttribute("bookList", this.bookService.list());
        return"redirect: /";
    }

    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model) {
        model.addAttribute("bookData", this.bookService.getBookById(id));
        return "redirect:/";
    }


    @RequestMapping(value = "/")
    public ModelAndView listOfBooks(@RequestParam(required = false)Integer page, @RequestParam(required = false) String bookName){
        ModelAndView modelAndView = new ModelAndView(   "index");
        if(id != 0){
            modelAndView.addObject("book",this.bookService.getBookById(id));
            id = 0;
        }else{
            modelAndView.addObject("book", new Book());
        }

        List<Book> books = null;
        if(bookName == null || bookName.length() < 2){
            books = this.bookService.list();
        }else{
            List<Book> booksTemp = this.bookService.list();
            books = new ArrayList<Book>();
            for (Book bookTmp : booksTemp
                 ) {
                if(bookTmp.getTitle().toLowerCase().contains(bookName.toLowerCase())){
                    System.out.println("filter: " + bookTmp);
                    books.add(bookTmp);
                }
            }
        }
        PagedListHolder<Book> pagedListHolder = new PagedListHolder<Book>(books);
        pagedListHolder.setPage(10);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
        if(page == null || page < 1 ||page > pagedListHolder.getPageCount()){
            page = 1;
        }
        modelAndView.addObject("page", page);
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            modelAndView.addObject("booksList", pagedListHolder.getPageList());
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            modelAndView.addObject("booksList", pagedListHolder.getPageList());
        }
        return modelAndView;
    }

}
