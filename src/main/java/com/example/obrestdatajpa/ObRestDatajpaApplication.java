package com.example.obrestdatajpa;

import com.example.obrestdatajpa.models.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
        BookRepository repository = context.getBean(BookRepository.class);

        Book book = new Book(null, "Pequeños Ricos", "Alexander Calderon", 60, 60.00, LocalDate.of(2022, 11, 25), true);
        Book book1 = new Book(null, "Pequeños Ricos 2", "Alexander Calderon", 60, 70.00, LocalDate.of(2023, 11, 25), false);
        Book book2 = new Book(null, "Pequeños Ricos 3", "Alexander Calderon", 60, 80.00, LocalDate.of(2024, 11, 25), false);
        Book book3 = new Book(null, "Pequeños Ricos 4", "Alexander Calderon", 60, 90.00, LocalDate.of(2025, 11, 25), false);

        repository.save(book);
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);

        System.out.println("Libros completos: " + repository.findAll().size());

        repository.deleteById(2L);
        System.out.println("Libros: " + repository.findAll().size());
    }

}
