package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.coderslab.interfaces.BookService;
import pl.coderslab.model.Book;
import pl.coderslab.model.BookDao;

import java.util.List;

@Component
@Primary
public class DbBookService implements BookService {
    private BookDao bookDao;

    @Autowired
    public DbBookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> getList() {
        return bookDao.getList();
    }

    @Override
    public void setList(List<Book> list) {

    }

    @Override
    public Book getBookById(long id) {
        return bookDao.getBookById(id);
    }

    @Override
    public Book updateBook(Book book, long id) {
        return bookDao.updateBook(book, id);
    }

    @Override
    public Book addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public void removeBook(long bookId) {
        bookDao.removeBook(bookId);
    }
}
