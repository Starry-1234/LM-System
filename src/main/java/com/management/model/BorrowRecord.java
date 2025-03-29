package com.management.model;

import java.util.Date;

public class BorrowRecord {
    private int id;
    private String ISBN;
    private String bookName;
    private String borrower;
    private Date borrowingTime;
    private Date returnTime;

    // 构造函数
    public BorrowRecord(int id, String ISBN, String bookName, String borrower, Date borrowingTime, Date returnTime) {
        this.id = id;
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.borrower = borrower;
        this.borrowingTime = borrowingTime;
        this.returnTime = returnTime;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public Date getBorrowingTime() {
        return borrowingTime;
    }

    public void setBorrowingTime(Date borrowingTime) {
        this.borrowingTime = borrowingTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }
}
