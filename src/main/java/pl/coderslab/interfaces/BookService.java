package pl.coderslab.interfaces;

import pl.coderslab.model.Book;

import java.util.List;

public interface BookService {
    public List<Book> getList();
    public void setList(List<Book> list);
    public Book getBookById(long id);
    public void updateBook(Book book);
    public Book addBook(Book book);
    public List<Book> removeBook(long bookId);
}
