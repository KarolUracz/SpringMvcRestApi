package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.interfaces.BookService;
import pl.coderslab.model.Book;
import pl.coderslab.service.MemoryBookService;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book("9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping("")
    public List<Book> getBookList() {
        return bookService.getList();
    }

    @PostMapping("")
    public Book getBookById(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }

    @PutMapping("{id}")
    public void updateBook(@RequestBody Book book, @PathVariable long id) {
        book.setId(id);
        bookService.updateBook(book);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.removeBook(id);
    }
}
