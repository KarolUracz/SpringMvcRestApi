package pl.coderslab.model;

import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.interfaces.BookService;
import pl.coderslab.service.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements BookService {

    private static final String LOAD_ALL_QUERY = "SELECT * FROM book";
    private static final String LOAD_BY_ID_QUERY = "SELECT * FROM book WHERE id=?";
    private static final String UPDATE_BY_ID_QUERY = "UPDATE book SET isbn=?, title=?, author=?, publisher=?, type=? WHERE id=?";
    private static final String ADD_QUERY = "INSERT INTO book(isbn, title, author, publisher, type) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM book WHERE id=?";

    @Override
    public List<Book> getList() {
        try (Connection connection = DbUtil.getConnection()) {
            List<Book> books = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setType(resultSet.getString("type"));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setList(List<Book> list) {

    }

    @Override
    public Book getBookById(long id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setType(resultSet.getString("type"));
                return book;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book updateBook(Book book, long id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_QUERY);
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getPublisher());
            preparedStatement.setString(5, book.getType());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getBookById(id);
    }

    @Override
    public Book addBook(Book book) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublisher());
            statement.setString(5, book.getType());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                book.setId(resultSet.getInt(1));
            }
            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeBook(long bookId) {
        try(Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY);
            statement.setLong(1, bookId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
