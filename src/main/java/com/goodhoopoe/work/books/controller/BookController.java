package com.goodhoopoe.work.books.controller;

import com.goodhoopoe.work.books.model.Book;
import com.goodhoopoe.work.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = {"/books","/books/{page}"}, method = RequestMethod.GET)
    public String listBooks(@PathVariable("page") Optional<Integer> page , Model model) {
        model.addAttribute("book", new Book());
        List<Book> listBooks = this.bookService.showBooks(page.isPresent() ? page.get() : 1);
        model.addAttribute("listBooks", listBooks);
        model.addAttribute("type", "view");
        int count = this.bookService.getBooksCount();
        model.addAttribute("pages",
                (count % 10 == 0 ? count / 10 : count / 10 + 1));
        return "books";
    }

    @RequestMapping(value = "add")
    public String addBookForm(Model model) {
        model.addAttribute("method","add");
        model.addAttribute("book", new Book());
        return "bookdata";
    }

    @RequestMapping(value = "books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        System.out.println(book);
        if (book.getId() == 0) {
            this.bookService.addBook(book);
        } else {
            this.bookService.replaceBook(book);
        }

        return "redirect:/books";
    }

    @RequestMapping("/books/remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        this.bookService.removeBook(id);
        return "redirect:/books";
    }

    @RequestMapping("/view/{id}")
    public String viewBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.viewBook(id));
        model.addAttribute("method","replace");
        return "bookdata";
    }
    @RequestMapping("/replace/{id}")
    public String replaceBookForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.viewBook(id));
        model.addAttribute("listBooks", this.bookService.showBooks(1));
        model.addAttribute("method","replace");
        return "bookdata";
    }

    @RequestMapping(value = "/books/replace", method = RequestMethod.POST)
    public String replaceBook(@ModelAttribute("book") Book book) {
        book.setReadAlready(false);
        System.out.println(book);
         this.bookService.replaceBook(book);
        return "redirect:/books";
    }

    @RequestMapping("/books/read/{id}")
    public String readBook(@PathVariable("id") int id, Model model) {
        this.bookService.readBook(id);
        model.addAttribute("book", this.bookService.viewBook(id));
        model.addAttribute("method","replace");
        return "bookdata";
    }

    @RequestMapping("/search")
    public String searchBooks(@RequestParam("keywords") String query, Model model) {
        model.addAttribute("listBooks", this.bookService.searchBooks(query));
        model.addAttribute("keywords", query);
        model.addAttribute("method", "search");
        return "books";
    }
}


























