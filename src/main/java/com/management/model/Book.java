package com.management.model;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Date publicationDate;
    private int stock_Quantity;
    private String category;
    private double price;

    // 默认构造函数
    public Book() {}

    // 带参数的构造函数
    public Book(int id, String title, String author, String isbn, String publisher, Date publicationDate, int stock_Quantity, String category, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.stock_Quantity = stock_Quantity;
        this.category = category;
        this.price = price;
    }
}
