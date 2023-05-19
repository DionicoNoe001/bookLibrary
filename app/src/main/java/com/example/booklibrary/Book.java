package com.example.booklibrary;

public class Book {

    private String code;
    private String title;
    private String author;
    private Integer noOfDays;
    private Boolean isBorrowed;

    private Double bookValue = 30.00;

    public Book(String code, String title, String author, Integer noOfDays, Boolean isBorrowed) {
        this.code = code;
        this.title = title;
        this.author = author;
        this.noOfDays = noOfDays;
        this.isBorrowed = isBorrowed;
    }

    public Book (){

    }

    public Double calculateBook(Integer noOfDays){

        Double totalPayment = noOfDays * this.bookValue;

        return totalPayment;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    public Boolean getBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        isBorrowed = borrowed;
    }
}
