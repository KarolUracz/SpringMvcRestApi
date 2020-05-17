package pl.coderslab.interfaces;

import pl.coderslab.model.Book;

import java.util.List;

public interface BookService {
    public List<Book> getList();
    public void setList(List<Book> list);
    public Book getBookById(long id);
    public Book updateBook(Book book, long id);
    public Book addBook(Book book);
    public void removeBook(long bookId);
}
