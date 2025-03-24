package com.management.controller;

import com.management.dao.BookDAO;
import com.management.model.Book;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class BookController {
    private BookDAO bookDAO;

    public BookController() {
        Properties properties = new Properties();
        try {
            // 使用类加载器读取 application.properties 文件
            properties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl(properties.getProperty("db.url"));
            dataSource.setUsername(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setDriverClassName(properties.getProperty("db.driver"));

            Connection conn = dataSource.getConnection();
            bookDAO = new BookDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        try {
            bookDAO.addBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        try {
            bookDAO.updateBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        try {
            bookDAO.deleteBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book getBookById(int id) {
        try {
            return bookDAO.getBookById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getAllBooks() {
        try {
            return bookDAO.getAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}