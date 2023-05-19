package com.example.booklibrary;

public class RegularBook extends Book {

    private Double bookValue = 20.00;

    public RegularBook(String code, String title, String author, Integer noOfDays, Boolean isBorrowed) {
        super(code, title, author, noOfDays, isBorrowed);
    }

    public RegularBook(){

    }

    @Override
    public Double calculateBook(Integer noOfDays) {
        Double totalPayment = noOfDays * bookValue;
        return totalPayment;
    }
}
