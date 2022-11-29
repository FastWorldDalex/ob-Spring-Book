package com.example.obrestdatajpa.services;

import com.example.obrestdatajpa.models.Book;

public class BookPriceCalculator {

    public double calculatePrice(Book book){
        double prices = book.getPrecio();

        if(book.getPaginas()> 300){
            prices +=  5;
        }
        prices+= 2.99;
        return prices;
    }
}
