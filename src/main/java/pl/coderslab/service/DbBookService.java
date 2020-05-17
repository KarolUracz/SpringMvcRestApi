package pl.coderslab.service;

import pl.coderslab.interfaces.BookService;
import pl.coderslab.model.Book;

import java.util.List;

public class DbBookService implements BookService {
    @Override
    public List<Book> getList() {
        return null;
    }

    @Override
    public void setList(List<Book> list) {

    }

    @Override
    public Book getBookById(long id) {
        return null;
    }

    @Override
    public Book updateBook(Book book, long id) {
        return null;
    }

    @Override
    public Book addBook(Book book) {
        return null;
    }

    @Override
    public List<Book> removeBook(long bookId) {
        return null;
    }
}
