package com.example.obrestdatajpa.services;

import com.example.obrestdatajpa.models.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {

        BookPriceCalculator calculator = new BookPriceCalculator();
        double price = calculator.calculatePrice(new Book(null,
                "Libro de pruebas",
                "AlexanderCode",
                500,
                450.00,
                null,
                true));

        System.out.println(price);
        assertTrue(price > 0);
        assertEquals(457.99,price); //valida si es igual el precio al valor dado
    }
}