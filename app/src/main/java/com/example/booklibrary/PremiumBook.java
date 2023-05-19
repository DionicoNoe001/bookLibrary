package com.example.booklibrary;

public class PremiumBook extends Book{

    private Double bookValue = 50.00;

    public PremiumBook(String code, String title, String author, Integer noOfDays, Boolean isBorrowed) {
        super(code, title, author, noOfDays, isBorrowed);

    }

    public PremiumBook() {
    }

    @Override
    public Double calculateBook(Integer noOfDays) {

        Double totalPayment = noOfDays * bookValue;
        return totalPayment;

    }
}
