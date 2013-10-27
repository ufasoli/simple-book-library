package com.ufasoli.vaadin.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 24.10.13
 * Time: 10:21
 */
@Document
public class Book implements Serializable {

    @Id
    private String bookId;
    private String bookTitle;
    private String bookCategory;
    private String userBorrowed;
    private Date borrowedOn;


    public Book() {
    }

    public Book(String bookId, String bookTitle, String bookCategory, String userBorrowed, Date borrowedOn) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookCategory = bookCategory;
        this.userBorrowed = userBorrowed;
        this.borrowedOn = borrowedOn;
    }

    public Book(String bookId, String bookTitle, String bookCategory) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookCategory = bookCategory;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getUserBorrowed() {
        return userBorrowed;
    }

    public void setUserBorrowed(String userBorrowed) {
        this.userBorrowed = userBorrowed;
    }

    public Date getBorrowedOn() {
        return borrowedOn;
    }

    public void setBorrowedOn(Date borrowedOn) {
        this.borrowedOn = borrowedOn;
    }
}
